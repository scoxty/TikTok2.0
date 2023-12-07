package com.xty.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.xty.BloomFilterUtils;
import com.xty.Encryption;
import com.xty.ErrorCode;
import com.xty.JwtUtil;
import com.xty.business.UserConstant;
import com.xty.dal.mysql.mapper.UserMapper;
import com.xty.dal.mysql.pojo.UserPojo;
import com.xty.dubbo_gen.favorite.FavoriteService;
import com.xty.dubbo_gen.favorite.GetUserFavoriteCountRequest;
import com.xty.dubbo_gen.favorite.GetUserTotalFavoriteCountRequest;
import com.xty.dubbo_gen.favorite.IsUserFavoriteRequest;
import com.xty.dubbo_gen.user.*;
import com.xty.dubbo_gen.video.GetWorkCountRequest;
import com.xty.dubbo_gen.video.GetWorkCountResponse;
import com.xty.dubbo_gen.video.VideoService;
import com.xty.middleware.redis.Keys;
import com.xty.middleware.redis.TokenManager;
import com.xty.middleware.redis.UserInfo;
import jakarta.annotation.PostConstruct;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@DubboService
@Service
public class UserServiceImpl implements UserService {
    @DubboReference(check = false)
    private VideoService videoService;
    @DubboReference(check = false)
    private FavoriteService favoriteService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private Keys keys;
    @Autowired
    private Redisson redisson;
    @Autowired
    private UserInfo userInfo;
    private Cache<Long, String> localCache = Caffeine.newBuilder()
            .maximumSize(100)
            .expireAfterAccess(100L, TimeUnit.SECONDS)
            .build();
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    //TODO: 换成流式查询或者用redis
    @PostConstruct
    public void initBloom() {
        LambdaQueryWrapper<UserPojo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(UserPojo::getName);
        List<UserPojo> userList = userMapper.selectList(queryWrapper);
        for (UserPojo user : userList) {
            BloomFilterUtils.insertUserName(user.getName());
        }
    }

    @Override
    public UserRegisterResponse register(UserRegisterRequest request) {
        log.info("register rpc 服务被调用");

        UserRegisterResponse response;

        LambdaQueryWrapper<UserPojo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserPojo::getName, request.getUsername());
        UserPojo user = userMapper.selectOne(queryWrapper);

        // 判断用户是否存在
        if (user != null) {
            response = UserRegisterResponse.newBuilder().
                    setStatusCode(ErrorCode.CODE_USER_ALREADY_EXISTS.getCode()).
                    setStatusMsg(ErrorCode.CODE_USER_ALREADY_EXISTS.getMessage()).
                    build();
            return response;
        }

        // 设置默认参数
        Long id = IdWorker.getId(); // 雪花算法
        String name = request.getUsername();
        String password = Encryption.encryptPassword(request.getPassword());
        String avatar = UserConstant.avatar;
        String signature = UserConstant.signature;
        String background_image = UserConstant.background_image;
        Date now = new Date();

        // 插入数据库
        UserPojo newUser = new UserPojo(id, name, password, avatar, signature, background_image, now, now);
        int num = userMapper.insert(newUser);
        if (num != 1) {
            response = UserRegisterResponse.newBuilder().
                    setStatusCode(ErrorCode.CODE_DB_ERROR.getCode()).
                    setStatusMsg(ErrorCode.CODE_DB_ERROR.getMessage()).
                    build();
            return response;
        }

        // 生成JWT
        String jwt = JwtUtil.createJWT(id.toString());
        // 添加到redis
        tokenManager.setToken(id, jwt);
        // 异步添加username到bloom过滤器
        Executors.newSingleThreadExecutor().submit(()->{
            BloomFilterUtils.insertUserName(name);
        });

        response = UserRegisterResponse.newBuilder().
                setStatusCode(ErrorCode.CODE_SUCCESS.getCode()).
                setStatusMsg(ErrorCode.CODE_SUCCESS.getMessage()).
                setUserId(id).
                setToken(jwt).
                build();

        return response;
    }

    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        log.info("login rpc 服务被调用");

        String username = request.getUsername();
        UserLoginResponse response;

        // 先查Bloom过滤器, 判断用户是否存在
        boolean hasUsername = BloomFilterUtils.hasUserName(username);
        if (!hasUsername) {
            response = UserLoginResponse.newBuilder().
                    setStatusCode(ErrorCode.CODE_USER_NOT_FOUND.getCode()).
                    setStatusMsg(ErrorCode.CODE_USER_NOT_FOUND.getMessage()).
                    build();
            return response;
        }

        // 复查mysql, 判断用户是否存在
        LambdaQueryWrapper<UserPojo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserPojo::getName, username);
        UserPojo user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            response = UserLoginResponse.newBuilder().
                    setStatusCode(ErrorCode.CODE_USER_NOT_FOUND.getCode()).
                    setStatusMsg(ErrorCode.CODE_USER_NOT_FOUND.getMessage()).
                    build();
            return response;
        }
        // 检查密码
        boolean check = Encryption.matchPassword(request.getPassword(), user.getPassword());
        if (!check) {
            response = UserLoginResponse.newBuilder().
                    setStatusCode(ErrorCode.CODE_WRONG_LOGIN_CREDENTIALS.getCode()).
                    setStatusMsg(ErrorCode.CODE_WRONG_LOGIN_CREDENTIALS.getMessage()).
                    build();
            return response;
        }

        String jwt = JwtUtil.createJWT(user.getId().toString());
        // 添加到redis
        tokenManager.setToken(user.getId(), jwt);

        response = UserLoginResponse.newBuilder().
                setStatusCode(ErrorCode.CODE_SUCCESS.getCode()).
                setStatusMsg(ErrorCode.CODE_SUCCESS.getMessage()).
                setUserId(user.getId()).
                setToken(jwt).
                build();

        return response;
    }

    @Override
    public UserInfoResponse getUserInfo(UserInfoRequest request) {
        log.info("getuserinfo rpc 服务被调用");

        Long from_user_id = request.getFromUserId();
        Long to_user_id = request.getToUserId();
        boolean isLogin = false;
        UserInfoResponse response;


        if (from_user_id != 0) {
            isLogin = true;
        }

        if (to_user_id == 0) {
            response = UserInfoResponse.newBuilder().
                    setStatusCode(ErrorCode.CODE_INVALID_PARAM.getCode()).
                    setStatusMsg(ErrorCode.CODE_INVALID_PARAM.getMessage()).
                    build();
            return response;
        }

        String username = getUsernameById(to_user_id);
        if (username == null) {
            response = UserInfoResponse.newBuilder().
                    setStatusCode(ErrorCode.CODE_USER_NOT_FOUND.getCode()).
                    setStatusMsg(ErrorCode.CODE_USER_NOT_FOUND.getMessage()).
                    build();
            return response;
        }

        Long followCount = 0L, followerCount = 0L, totalFavorited = 0L, workCount = 0L, favoriteCount = 0L;
        boolean isFollow = false;
        ExecutorService executor = Executors.newFixedThreadPool(4);
        // TODO: 异步获取关注相关信息
        Future<Long> favoriteCountFuture = executor.submit(() -> {
            GetUserFavoriteCountRequest userFavoriteCountRequest = GetUserFavoriteCountRequest.newBuilder()
                    .setUserId(to_user_id)
                    .build();
            return favoriteService.getUserFavoriteCount(userFavoriteCountRequest).getFavoriteCount();
        });
        Future<Long> totalFavoriteCountFuture = executor.submit(() -> {
            GetUserTotalFavoriteCountRequest totalFavoriteCountRequest = GetUserTotalFavoriteCountRequest.newBuilder()
                    .setUserId(to_user_id)
                    .build();
            return favoriteService.getUserTotalFavoriteCount(totalFavoriteCountRequest).getFavoriteCount();
        });
        Future<Long> workCountFuture = executor.submit(() -> {
            GetWorkCountRequest workCountRequest = GetWorkCountRequest.newBuilder()
                    .setUserId(to_user_id)
                    .build();
            return videoService.getWorkCount(workCountRequest).getWorkCount();
        });
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
            totalFavorited = totalFavoriteCountFuture.get();
            workCount = workCountFuture.get();
        } catch (Exception e) {
            log.error("获取用户信息异常: " + e.getMessage());
        }

        User userInfo = User.newBuilder()
                .setId(to_user_id)
                .setName(username)
                .setAvatar(UserConstant.avatar)
                .setBackgroundImage(UserConstant.background_image)
                .setSignature(UserConstant.signature)
                .setIsFollow(isFollow)
                .setFollowCount(followCount)
                .setFollowerCount(followerCount)
                .setFavoriteCount(favoriteCount)
                .setWorkCount(workCount)
                .setTotalFavorited(totalFavorited)
                .build();

        response = UserInfoResponse.newBuilder()
                .setStatusCode(ErrorCode.CODE_SUCCESS.getCode())
                .setStatusMsg(ErrorCode.CODE_SUCCESS.getMessage())
                .setUser(userInfo)
                .build();
        return response;
    }

    public String getUsernameById(Long userId) {
        // 先查本地缓存
        String username = localCache.getIfPresent(userId);
        if (username != null && !username.equals("")) {
            return username;
        }

        // 本地缓存没有, 再查redis
        username = userInfo.getUserNameById(userId);
        if (username != null && !username.equals("")) {
            return username;
        }

        // redis也没有, 则查mysql
        String userLockName = keys.getUserLockName(userId);

        RLock redissonLock = redisson.getLock(userLockName);
        redissonLock.lock();
        try {
            // double check
            username = userInfo.getUserNameById(userId);
            if (username != null && !username.equals("")) {
                return username;
            }

            UserPojo user = userMapper.selectById(userId);
            if (user == null) {
                return null;
            }
            username = user.getName();
            // 添加到redis
            userInfo.setUserNameById(userId, username);
            // 添加到本地缓存
            localCache.put(userId, username);
        } finally {
            redissonLock.unlock();
        }

        return username;
    }
}
