package com.xty.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.xty.BloomFilterUtils;
import com.xty.ErrorCode;
import com.xty.business.VideoConstant;
import com.xty.dal.mysql.mapper.VideoMapper;
import com.xty.dal.mysql.pojo.VideoPojo;
import com.xty.dubbo_gen.favorite.FavoriteService;
import com.xty.dubbo_gen.favorite.GetUserFavoriteCountRequest;
import com.xty.dubbo_gen.favorite.GetVideoFavoriteCountRequest;
import com.xty.dubbo_gen.favorite.IsUserFavoriteRequest;
import com.xty.dubbo_gen.user.User;
import com.xty.dubbo_gen.user.UserInfoRequest;
import com.xty.dubbo_gen.user.UserInfoResponse;
import com.xty.dubbo_gen.user.UserService;
import com.xty.dubbo_gen.video.*;
import com.xty.middleware.redis.Keys;
import com.xty.middleware.redis.VideoRedis;
import com.xty.middleware.rocketmq.video.VideoMsg;
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
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

@Service
@DubboService
public class VideoServiceImpl implements VideoService {
    @DubboReference(check = false)
    private UserService userService;
    @DubboReference(check = false)
    private FavoriteService favoriteService;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private VideoRedis videoRedis;
    @Autowired
    private Keys keyRedis;
    @Autowired
    private Redisson redisson;
    private Cache<Long, Long> localCache = Caffeine.newBuilder()
            .maximumSize(100)
            .expireAfterAccess(100L, TimeUnit.SECONDS)
            .build();
    private static final Logger log = LoggerFactory.getLogger(VideoServiceImpl.class);

    //TODO: 换成流式查询
    @PostConstruct
    public void initBloom() {
        LambdaQueryWrapper<VideoPojo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(VideoPojo::getAuthorId);
        List<VideoPojo> videoList = videoMapper.selectList(queryWrapper);
        for (VideoPojo video : videoList) {
            BloomFilterUtils.insertAuthorId(video.getAuthorId());
        }
    }

    @Override
    public FeedResponse getVideoFeed(FeedRequest request) {
        log.info("feed rpc 服务被调用");

        FeedResponse response = null;
        Long latestTime = request.getLatestTime();
        Long userId = request.getUserId();

        List<VideoPojo> videoPojoList = videoRedis.getVideos(latestTime);
        // 若无视频刷则更新时间戳再次尝试获取feed
        if (videoPojoList == null || videoPojoList.isEmpty()) {
            Date now = new Date();
            latestTime = now.getTime();
            videoPojoList = videoRedis.getVideos(latestTime);
        }

        if (videoPojoList.isEmpty()) {
            response = FeedResponse.newBuilder()
                    .setStatusCode(ErrorCode.CODE_SUCCESS.getCode())
                    .setStatusMsg(ErrorCode.CODE_SUCCESS.getMessage())
                    .build();
            return response;
        }

        // 封装响应
        List<Video> videoList = new ArrayList<>();
        for (int i = 0; i < videoPojoList.size(); i ++) {
            VideoPojo videoPojo = videoPojoList.get(i);
            if (i == 0) {
                latestTime = videoPojo.getCreatedTime().getTime();
            }

            Long favoriteCount = 0L, commentCount = 0L;
            boolean isFavorite = false;
            User author = null;

            // 异步获取相关信息
            ExecutorService executor = Executors.newFixedThreadPool(4);
            Future<User> userInfoFuture = executor.submit(() -> {
                UserInfoRequest userInfoRequest = UserInfoRequest.newBuilder()
                        .setFromUserId(userId)
                        .setToUserId(videoPojo.getAuthorId())
                        .build();
                return userService.getUserInfo(userInfoRequest).getUser();
            });
            Future<Long> favoriteCountFuture = executor.submit(() -> {
                GetVideoFavoriteCountRequest videoFavoriteCountRequest = GetVideoFavoriteCountRequest.newBuilder()
                        .setVideoId(videoPojo.getId())
                        .build();
                return favoriteService.getVideoFavoriteCount(videoFavoriteCountRequest).getFavoriteCount();
            });
            Future<Boolean> isFavoriteFuture = executor.submit(() -> {
                IsUserFavoriteRequest isUserFavoriteRequest = IsUserFavoriteRequest.newBuilder()
                        .setUserId(userId)
                        .setVideoId(videoPojo.getId())
                        .build();
                return favoriteService.isUserFavorite(isUserFavoriteRequest).getIsFavorite();
            });

            //TODO: rpc调用获取commentCount
            executor.shutdown();
            try {
                if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                    log.error("异步获取video信息超时!");
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }

            try {
                author = userInfoFuture.get();
                favoriteCount = favoriteCountFuture.get();
                isFavorite = isFavoriteFuture.get();
            } catch (Exception e) {
                log.error("获取视频信息异常: " + e.getMessage());
            }


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

        response = FeedResponse.newBuilder()
                .setStatusCode(ErrorCode.CODE_SUCCESS.getCode())
                .setStatusMsg(ErrorCode.CODE_SUCCESS.getMessage())
                .addAllVideoList(videoList)
                .setNextTime(latestTime)
                .build();

        return response;
    }

    @Override
    public PublishActionResponse publishAction(PublishActionRequest request) {
        log.info("publish rpc 服务被调用");

        PublishActionResponse response = null;

        // 保存视频副本到本地
        String localVideoPath = VideoConstant.localFilePath + UUID.randomUUID() + ".mp4";
        try (FileOutputStream fos = new FileOutputStream(localVideoPath)) {
            fos.write(request.getData().toByteArray());
        } catch (IOException e) {
            response = PublishActionResponse.newBuilder()
                    .setStatusCode(ErrorCode.CODE_SERVER_BUSY.getCode())
                    .setStatusMsg(ErrorCode.CODE_SERVER_BUSY.getMessage())
                    .build();
            return response;
        }

        // 通过mq异步的处理视频上传任务
        VideoMsg videoMsg = new VideoMsg(request.getUserId(), request.getTitle(), localVideoPath);
        rocketMQTemplate.syncSend("videoTopic", videoMsg);

        response = PublishActionResponse.newBuilder()
                .setStatusCode(ErrorCode.CODE_SUCCESS.getCode())
                .setStatusMsg(ErrorCode.CODE_SUCCESS.getMessage())
                .build();

        return response;
    }

    @Override
    public PublishListResponse getPublishList(PublishListRequest request) {
        PublishListResponse response = null;

        log.info("publishlist rpc 服务被调用");

        Long from_user_id = request.getFromUserId();
        Long to_user_id = request.getToUserId();

        // 获取发布列表元数据
        LambdaQueryWrapper<VideoPojo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(VideoPojo::getAuthorId, to_user_id);
        List<VideoPojo> videoPojoList = videoMapper.selectList(queryWrapper);

        if (videoPojoList.isEmpty()) {
            response = PublishListResponse.newBuilder()
                    .setStatusCode(ErrorCode.CODE_SUCCESS.getCode())
                    .setStatusMsg(ErrorCode.CODE_SUCCESS.getMessage())
                    .build();
            return response;
        }

        // 获取作者信息
        UserInfoRequest userInfoRequest = UserInfoRequest.newBuilder()
                .setFromUserId(from_user_id)
                .setToUserId(to_user_id)
                .build();
        UserInfoResponse userInfoResponse = userService.getUserInfo(userInfoRequest);

        // 封装响应
        List<Video> videoList = new ArrayList<>();
        for (int i = 0; i < videoPojoList.size(); i ++) {
            VideoPojo videoPojo = videoPojoList.get(i);

            Long favoriteCount = 0L, commentCount = 0L;
            boolean isFavorite = false;

            // 异步获取相关信息
            ExecutorService executor = Executors.newFixedThreadPool(2);
            Future<Long> favoriteCountFuture = executor.submit(() -> {
                GetVideoFavoriteCountRequest videoFavoriteCountRequest = GetVideoFavoriteCountRequest.newBuilder()
                        .setVideoId(videoPojo.getId())
                        .build();
                return favoriteService.getVideoFavoriteCount(videoFavoriteCountRequest).getFavoriteCount();
            });
            Future<Boolean> isFavoriteFuture = executor.submit(() -> {
                IsUserFavoriteRequest isUserFavoriteRequest = IsUserFavoriteRequest.newBuilder()
                        .setUserId(from_user_id)
                        .setVideoId(videoPojo.getId())
                        .build();
                return favoriteService.isUserFavorite(isUserFavoriteRequest).getIsFavorite();
            });
            //TODO: rpc调用获取commentCount
            executor.shutdown();
            try {
                if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                    log.error("异步获取video信息超时!");
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }

            try {
                favoriteCount = favoriteCountFuture.get();
                isFavorite = isFavoriteFuture.get();
            } catch (Exception e) {
                log.error("获取视频信息失败: " + e.getMessage());
            }

            Video video = Video.newBuilder()
                    .setId(videoPojo.getId())
                    .setAuthor(userInfoResponse.getUser())
                    .setPlayUrl(VideoConstant.Oss + VideoConstant.videoDir + videoPojo.getPlayUrl())
                    .setCoverUrl(VideoConstant.Oss + VideoConstant.pictureDir + videoPojo.getCoverUrl())
                    .setFavoriteCount(favoriteCount)
                    .setCommentCount(commentCount)
                    .setIsFavorite(isFavorite)
                    .setTitle(videoPojo.getTitle())
                    .build();

            videoList.add(video);
        }

        response = PublishListResponse.newBuilder()
                .setStatusCode(ErrorCode.CODE_SUCCESS.getCode())
                .setStatusMsg(ErrorCode.CODE_SUCCESS.getMessage())
                .addAllVideoList(videoList)
                .build();

        return response;
    }

    @Override
    public GetWorkCountResponse getWorkCount(GetWorkCountRequest request) {
        log.info("workcount rpc 服务被调用");

        GetWorkCountResponse response = null;

        Long userId = request.getUserId();

        // 先查本地缓存
        Long cnt = localCache.getIfPresent(userId);
        if (cnt != null) {
            response = GetWorkCountResponse.newBuilder()
                    .setWorkCount(cnt)
                    .build();
            return response;
        }

        // 本地缓存没有先查bloom过滤器判断是否存在
        boolean check = BloomFilterUtils.hasAuthorId(userId);
        if (!check) {
            System.out.println("Bloom判断失败");
            response = GetWorkCountResponse.newBuilder()
                    .setWorkCount(0)
                    .build();
            return response;
        }

        // bloom判断存在先查redis
        Long workCount = videoRedis.getWorkCount(userId);
        if (workCount != null) {
            Long finalWorkCount = workCount;
            Executors.newSingleThreadExecutor().submit(()->{
                localCache.put(userId, finalWorkCount);
            });
            response = GetWorkCountResponse.newBuilder()
                    .setWorkCount(workCount)
                    .build();
            return response;
        }

        // redis没有再查mysql
        String videoLockName = keyRedis.getVideoLockName(userId);

        RLock redissonLock = redisson.getLock(videoLockName);
        redissonLock.lock();
        try {
            // double check
            workCount = videoRedis.getWorkCount(userId);
            if (workCount != null) {
                Long finalWorkCount = workCount;
                Executors.newSingleThreadExecutor().submit(()->{
                    localCache.put(userId, finalWorkCount);
                });
                response = GetWorkCountResponse.newBuilder()
                        .setWorkCount(workCount)
                        .build();
                return response;
            }


            LambdaQueryWrapper<VideoPojo> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(VideoPojo::getAuthorId, userId);
            workCount = videoMapper.selectCount(queryWrapper);

            // 更新redis
            videoRedis.setWorkCount(userId, workCount);
            // 异步更新localCache和bloom
            Long finalWorkCount1 = workCount;
            Executors.newSingleThreadExecutor().submit(()->{
               localCache.put(userId, finalWorkCount1);
               BloomFilterUtils.insertAuthorId(userId);
            });
        } finally {
            redissonLock.unlock();
        }

        response = GetWorkCountResponse.newBuilder()
                .setWorkCount(workCount)
                .build();

        return response;
    }

    public void delLocalCache() {
        localCache.cleanUp();
    }
}
