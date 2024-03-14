package com.xty.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.xty.BloomFilterUtils;
import com.xty.ErrorCode;
import com.xty.business.VideoConstant;
import com.xty.dal.mysql.mapper.FavoriteMapper;
import com.xty.dal.mysql.mapper.UserMapper;
import com.xty.dal.mysql.mapper.VideoMapper;
import com.xty.dal.mysql.pojo.FavoritePojo;
import com.xty.dal.mysql.pojo.UserPojo;
import com.xty.dal.mysql.pojo.VideoPojo;
import com.xty.dubbo_gen.favorite.*;
import com.xty.dubbo_gen.user.UserInfoRequest;
import com.xty.dubbo_gen.user.UserInfoResponse;
import com.xty.dubbo_gen.user.UserService;
import com.xty.dubbo_gen.video.VideoService;
import com.xty.middleware.redis.FavoriteRedis;
import com.xty.middleware.redis.Keys;
import com.xty.middleware.rocketmq.FavoriteMsg;
import jakarta.annotation.PostConstruct;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

import static org.apache.rocketmq.client.producer.SendStatus.SEND_OK;

@Service
@DubboService
public class FavoriteServiceImpl implements FavoriteService {
    @DubboReference(check = false)
    private UserService userService;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    @Autowired
    private FavoriteMapper favoriteMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private FavoriteRedis favoriteRedis;
    @Autowired
    private Redisson redisson;
    @Autowired
    private ThreadPoolExecutor favoriteExecutor;

    private static final Logger log = LoggerFactory.getLogger(FavoriteServiceImpl.class);

    public Map<Long, Map<Long, Integer>> map = new HashMap<>();
    public final ReentrantLock mapLock = new ReentrantLock();
    private BlockingQueue<Map<Long, Map<Long, Integer>>> queue = new LinkedBlockingQueue<>();

    @PostConstruct
    public void init() {
        LambdaQueryWrapper<FavoritePojo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(FavoritePojo::getUserId, FavoritePojo::getVideoId);
        List<FavoritePojo> favoritePojos = favoriteMapper.selectList(queryWrapper);

        for (int i = 0; i < favoritePojos.size(); i ++) {
            Long userId = favoritePojos.get(i).getUserId();
            Long videoId = favoritePojos.get(i).getVideoId();

            BloomFilterUtils.insertIsFavorite(userId, videoId);
            BloomFilterUtils.insertFavoriteVideoId(videoId);
        }

        // 开启定时任务
        Executors.newSingleThreadExecutor().submit(this::startTimer);
        Executors.newSingleThreadExecutor().submit(this::favoriteMapConsumer);
    }

    @Override
    public FavoriteActionResponse favoriteAction(FavoriteActionRequest request) {
        log.info("favoriteAction rpc服务被调用");

        FavoriteActionResponse response = null;

        Long userId = request.getUserId();
        Long videoId = request.getVideoId();
        Integer actionType = request.getActionType();

        // 利用mq削峰
        if (actionType == 1 || actionType == 2) {
            FavoriteMsg favoriteMsg = new FavoriteMsg(userId, videoId, actionType);
            SendResult sendResult = rocketMQTemplate.syncSend("favoriteTopic", favoriteMsg);

            if (!sendResult.getSendStatus().equals(SEND_OK)) {
                response = FavoriteActionResponse.newBuilder()
                        .setStatusCode(ErrorCode.CODE_SERVER_BUSY.getCode())
                        .setStatusMsg(ErrorCode.CODE_SERVER_BUSY.getMessage())
                        .build();
                return response;
            }

            response = FavoriteActionResponse.newBuilder()
                    .setStatusCode(ErrorCode.CODE_SUCCESS.getCode())
                    .setStatusMsg(ErrorCode.CODE_SUCCESS.getMessage())
                    .build();
            return response;
        }

        response = FavoriteActionResponse.newBuilder()
                .setStatusCode(ErrorCode.CODE_INVALID_PARAM.getCode())
                .setStatusMsg(ErrorCode.CODE_INVALID_PARAM.getMessage())
                .build();

        return response;
    }

    @Override
    public FavoriteListResponse getFavoriteList(FavoriteListRequest request) {
        log.info("FavoriteList rpc 服务被调用");

        FavoriteListResponse response = null;

        Long from_user_id = request.getFromUserId();
        Long to_user_id = request.getToUserId();

        // 获取点赞视频id
        LambdaQueryWrapper<FavoritePojo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(FavoritePojo::getVideoId).eq(FavoritePojo::getUserId, to_user_id);
        List<FavoritePojo> favoriteList = favoriteMapper.selectList(queryWrapper);

        // 查询点赞视频列表
        List<Video> videoList = new ArrayList<>();

        for (int i = 0; i < favoriteList.size(); i ++) {
            // 获取视频元信息
            Long videoId = favoriteList.get(i).getVideoId();
            LambdaQueryWrapper<VideoPojo> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(VideoPojo::getId, videoId);
            VideoPojo videoPojo = videoMapper.selectById(videoId);

            // 获取video其他相关信息: 视频作者、视频被点赞数、视频评论数和视频是否被点赞
            // 视频点赞数和该视频是否被点赞
            Long favoriteCount = 0L, commentCount = 0L;
            boolean isFavorite = false;

            favoriteCount = checkAndSetVideoFavoriteCount(videoId);
            isFavorite = isLiked(to_user_id, videoId);

            // 异步获取其他相关信息
            // 获取视频作者信息
            Future<UserInfoResponse> userInfoResponseFuture = favoriteExecutor.submit(() -> {
                UserInfoRequest userInfoRequest = UserInfoRequest.newBuilder()
                        .setFromUserId(to_user_id)
                        .setToUserId(videoPojo.getAuthorId())
                        .build();
                return userService.getUserInfo(userInfoRequest);
            });
            //TODO: rpc调用获取评论数

            User author = getAuthorFromUserInfoResponseFuture(userInfoResponseFuture);

            Video video = Video.newBuilder()
                    .setId(videoPojo.getId())
                    .setAuthor(author)
                    .setPlayUrl(VideoConstant.Oss + VideoConstant.videoDir + videoPojo.getPlayUrl())
                    .setCoverUrl(VideoConstant.Oss + VideoConstant.pictureDir + videoPojo.getCoverUrl())
                    .setFavoriteCount(favoriteCount)
                    .setCommentCount(commentCount)
                    .setIsFavorite(isFavorite)
                    .setTitle(videoPojo.getTitle())
                    .build();

            videoList.add(video);
        }

        // 封装响应
        response = FavoriteListResponse.newBuilder()
                .setStatusCode(ErrorCode.CODE_SUCCESS.getCode())
                .setStatusMsg(ErrorCode.CODE_SUCCESS.getMessage())
                .addAllVideoList(videoList)
                .build();

        return response;
    }

    @Override
    public GetFavoriteCountResponse getVideoFavoriteCount(GetVideoFavoriteCountRequest request) {
        Long videoId = request.getVideoId();

        Long count = checkAndSetVideoFavoriteCount(videoId);

        GetFavoriteCountResponse response = GetFavoriteCountResponse.newBuilder()
                .setFavoriteCount(count)
                .build();

        return response;
    }

    @Override
    public GetFavoriteCountResponse getUserFavoriteCount(GetUserFavoriteCountRequest request) {
        Long userId = request.getUserId();

        Long count = checkAndSetUserFavoriteCount(userId);

        GetFavoriteCountResponse response = GetFavoriteCountResponse.newBuilder()
                .setFavoriteCount(count)
                .build();

        return response;
    }

    @Override
    public GetFavoriteCountResponse getUserTotalFavoriteCount(GetUserTotalFavoriteCountRequest request) {
        Long userId = request.getUserId();

        Long count = checkAndSetTotalFavoriteCount(userId);

        GetFavoriteCountResponse response = GetFavoriteCountResponse.newBuilder()
                .setFavoriteCount(count)
                .build();

        return response;
    }

    @Override
    public IsUserFavoriteResponse isUserFavorite(IsUserFavoriteRequest request) {
        Long userId = request.getUserId();
        Long videoId = request.getVideoId();

        boolean check = isLiked(userId, videoId);

        IsUserFavoriteResponse response = IsUserFavoriteResponse.newBuilder()
                .setIsFavorite(check)
                .build();

        return response;
    }

    // 定时任务聚合请求
    @Scheduled(fixedRate = 1000)
    public void startTimer() {
        mapLock.lock();
        try {
            if (map != null && !map.isEmpty()) {
                queue.put(map);
                map = new HashMap<>();
            }
        } catch (InterruptedException e) {
            log.error("向阻塞队列发送消息异常: " + e.getMessage());
        } finally {
            mapLock.unlock();
        }
    }

    // 批量处理
    public void favoriteMapConsumer() {
       while (true) {
            Map<Long, Map<Long, Integer>> msg = null;
            try {
                msg = queue.take();
            } catch (InterruptedException e) {
                log.error("从阻塞队列获取消息异常: " + e.getMessage());
                continue;
            }

            List<FavoritePojo> addActionList = new ArrayList<>();
            List<Long> delActionIdList = new ArrayList<>();

            for (Map.Entry<Long, Map<Long, Integer>> outerEntry : msg.entrySet()) {
                Long userId = outerEntry.getKey();
                Map<Long, Integer> innerMap = outerEntry.getValue();

                for (Map.Entry<Long, Integer> innerEntry : innerMap.entrySet()) {
                    Long videoId = innerEntry.getKey();
                    Integer actionType = innerEntry.getValue();

                    // 判断请求是否合法即userId和videoId是否存在
                    if (!isFavoriteActionValid(userId, videoId)) {
                        continue;
                    }

                    // 获取视频作者id
                    Long authorId = videoMapper.selectById(videoId).getAuthorId();

                    // 获取上一次的状态，判断是否重复点赞和重复取消点赞
                    // status为true表示已点赞, false表示为未点赞
                    boolean status = checkRepeatAction(userId, videoId);

                    if (actionType == 1) {
                        if (status) {
                            continue;
                        }
                        FavoritePojo favoritePojo = new FavoritePojo(IdWorker.getId(), userId, videoId);
                        addActionList.add(favoritePojo);
                    } else {
                        if (!status) {
                            continue;
                        }
                        Long delId = getDelId(userId, videoId);
                        delActionIdList.add(delId);
                    }

                    // 将点赞or取消点赞信息添加到redis
                    addFavoriteActionToRedis(userId, authorId, videoId, actionType);
                }
            }

            // 批量添加点赞信息到mysql
            if (!addActionList.isEmpty()) {
                favoriteMapper.insertBatchSomeColumn(addActionList);
            }
            // 从mysql批量删除点赞信息
            if (!delActionIdList.isEmpty()) {
                favoriteMapper.deleteBatchIds(delActionIdList);
            }
        }

    }

    public Long getDelId(Long userId, Long videoId) {
        LambdaQueryWrapper<FavoritePojo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(FavoritePojo::getId)
                .eq(FavoritePojo::getUserId, userId)
                .eq(FavoritePojo::getVideoId, videoId);
        FavoritePojo favoritePojo = favoriteMapper.selectOne(queryWrapper);
        return favoritePojo.getId();
    }

    public boolean checkRepeatAction(Long userId, Long videoId) {
        LambdaQueryWrapper<FavoritePojo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FavoritePojo::getUserId, userId).eq(FavoritePojo::getVideoId, videoId);
        FavoritePojo favoritePojo = favoriteMapper.selectOne(queryWrapper);
        return favoritePojo != null;
    }

    public boolean isFavoriteActionValid(Long userId, Long videoId) {
        UserPojo userPojo = userMapper.selectById(userId);
        VideoPojo videoPojo = videoMapper.selectById(videoId);
        return userPojo != null && videoPojo != null;
    }

    public void addFavoriteActionToRedis(Long userId, Long authorId, Long videoId, Integer actionType) {
        checkAndSetVideoFavoriteCount(videoId);
        checkAndSetUserFavoriteCount(userId);
        checkAndSetTotalFavoriteCount(authorId);

        if (actionType == 1) {
            favoriteRedis.addFavoriteCount(userId, authorId, videoId);

            // 异步更新bloom过滤器
            favoriteExecutor.submit(()->{
                BloomFilterUtils.insertIsFavorite(userId, videoId);
                BloomFilterUtils.insertFavoriteVideoId(videoId);
            });
        } else {
            favoriteRedis.delFavoriteCount(userId, authorId, videoId);
        }
    }

    public Long checkAndSetVideoFavoriteCount(Long videoId) {
        if (!BloomFilterUtils.hasFavoriteVideoId(videoId)) {
            favoriteRedis.setVideoFavoriteCountByVideoId(videoId, 0L);
            return 0L;
        }

        Long videoFavoriteCount = favoriteRedis.getVideoFavoriteCountByVideoId(videoId);
        if (videoFavoriteCount != null) {
            return videoFavoriteCount;
        }

        Long count = null;

        String favoriteLockName = Keys.getFavoriteLockName(videoId, Keys.VIDEO_FAVORITED_COUNT_FIELD);
        RLock redissonLock = redisson.getLock(favoriteLockName);
        redissonLock.lock();
        try {
            // double check
            videoFavoriteCount = favoriteRedis.getVideoFavoriteCountByVideoId(videoId);
            if (videoFavoriteCount != null) {
                return videoFavoriteCount;
            }

            // mysql
            LambdaQueryWrapper<FavoritePojo> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(FavoritePojo::getVideoId, videoId);
            count = favoriteMapper.selectCount(queryWrapper);

            favoriteRedis.setVideoFavoriteCountByVideoId(videoId, count);
        } finally {
            redissonLock.unlock();
        }

        return count;
    }

    public Long checkAndSetUserFavoriteCount(Long userId) {
        Long userFavoriteCount = favoriteRedis.getUserFavoriteCountByUserId(userId);
        if (userFavoriteCount != null) {
            return userFavoriteCount;
        }

        Long count = null;

        String favoriteLockName = Keys.getFavoriteLockName(userId, Keys.FAVORITE_COUNT_FIELD);
        RLock redissonLock = redisson.getLock(favoriteLockName);
        redissonLock.lock();
        try {
            // double check
            userFavoriteCount = favoriteRedis.getUserFavoriteCountByUserId(userId);
            if (userFavoriteCount != null) {
                return userFavoriteCount;
            }

            // mysql
            LambdaQueryWrapper<FavoritePojo> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(FavoritePojo::getUserId, userId);
            count = favoriteMapper.selectCount(queryWrapper);

            favoriteRedis.setUserFavoriteCountByUserId(userId, count);
        } finally {
            redissonLock.unlock();
        }

        return count;
    }

    public Long checkAndSetTotalFavoriteCount(Long userId) {
        Long totalFavoriteCount = favoriteRedis.getTotalFavoriteCountByUserId(userId);
        if (totalFavoriteCount != null) {
            return totalFavoriteCount;
        }

        Long count = null;

        String favoriteLockName = Keys.getFavoriteLockName(userId, Keys.TOTAL_FAVORITE_FIELD);
        RLock redissonLock = redisson.getLock(favoriteLockName);
        redissonLock.lock();
        try {
            // double check
            totalFavoriteCount = favoriteRedis.getTotalFavoriteCountByUserId(userId);
            if (totalFavoriteCount != null) {
                return totalFavoriteCount;
            }

            // mysql
            List<VideoPojo> videoPojos = videoMapper.selectList(
                    new LambdaQueryWrapper<VideoPojo>()
                    .select(VideoPojo::getId)
                    .eq(VideoPojo::getAuthorId, userId));
            List<Long> videoIdList = new ArrayList<>();
            for (int i = 0; i < videoPojos.size(); i ++) {
                videoIdList.add(videoPojos.get(i).getId());
            }
            LambdaQueryWrapper<FavoritePojo> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(FavoritePojo::getVideoId, videoIdList);
            count = favoriteMapper.selectCount(queryWrapper);

            favoriteRedis.setTotalFavoriteCountByUserId(userId, count);
        } finally {
            redissonLock.unlock();
        }

        return count;
    }

    public boolean isLiked(Long userId, Long videoId) {
        if (!BloomFilterUtils.hasIsFavorite(userId, videoId)) {
            return false;
        }
        LambdaQueryWrapper<FavoritePojo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FavoritePojo::getUserId, userId).eq(FavoritePojo::getVideoId, videoId);
        FavoritePojo favoritePojo = favoriteMapper.selectOne(queryWrapper);
        return favoritePojo != null;
    }

    // 类型转换
    public User getAuthorFromUserInfoResponseFuture(Future<UserInfoResponse> userInfoResponseFuture) {
        com.xty.dubbo_gen.user.User user = null;
        try {
            user = userInfoResponseFuture.get(5, TimeUnit.SECONDS).getUser();
        } catch (Exception e) {
            log.error("异步获取视频作者信息异常: " + e.getMessage());
        }

        User author = User.newBuilder()
                .setId(user.getId())
                .setName(user.getName())
                .setAvatar(user.getAvatar())
                .setSignature(user.getSignature())
                .setBackgroundImage(user.getBackgroundImage())
                .setFollowCount(user.getFollowCount())
                .setFollowerCount(user.getFollowerCount())
                .setIsFollow(user.getIsFollow())
                .setFavoriteCount(user.getFavoriteCount())
                .setTotalFavorited(user.getTotalFavorited())
                .setWorkCount(user.getWorkCount())
                .build();
        return author;
    }
}
