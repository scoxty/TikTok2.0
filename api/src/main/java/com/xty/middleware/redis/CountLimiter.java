package com.xty.middleware.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class CountLimiter {
    @Autowired
    private RedisTemplate redisTemplate;

    // 限流时间设置
    private static final long LIMITER_TIME = 2 * 60 * 60 * 1000; // 2 小时，转换为毫秒

    // 限流类型
    private static final String LOGIN_LIMIT = "loginLimit";
    private static final String REGISTER_LIMIT = "registerLimit";
    private static final String UPLOAD_LIMIT = "uploadLimit";

    // TODO: 开发完成待修改相应值
    // 各类型的最大尝试次数
    private static final int LOGIN_MAX_COUNT = 100;
    private static final int REGISTER_MAX_COUNT = 100;
    private static final int UPLOAD_MAX_COUNT = 100;

    public boolean incrementLoginLimiterCount(String ip) {
        String key = LOGIN_LIMIT + Keys.DELIMITER + ip;
        redisTemplate.opsForValue().setIfAbsent(key, 0, LIMITER_TIME, TimeUnit.MILLISECONDS);
        Integer cnt = (Integer) redisTemplate.opsForValue().get(key);

        if (cnt < LOGIN_MAX_COUNT) {
            redisTemplate.opsForValue().increment(key);
            return true;
        }

        return false;
    }


    public boolean incrementRegisterLimiterCount(String ip) {
        String key = REGISTER_LIMIT + Keys.DELIMITER + ip;
        redisTemplate.opsForValue().setIfAbsent(key, 0, LIMITER_TIME, TimeUnit.MILLISECONDS);
        Integer cnt = (Integer) redisTemplate.opsForValue().get(key);

        if (cnt < REGISTER_MAX_COUNT) {
            redisTemplate.opsForValue().increment(key);
            return true;
        }

        return false;
    }

    public boolean incrementPublishLimiterCount(String ip) {
        String key = UPLOAD_LIMIT + Keys.DELIMITER + ip;
        redisTemplate.opsForValue().setIfAbsent(key, 0, LIMITER_TIME, TimeUnit.MILLISECONDS);
        Integer cnt = (Integer) redisTemplate.opsForValue().get(key);

        if (cnt < UPLOAD_MAX_COUNT) {
            redisTemplate.opsForValue().increment(key);
            return true;
        }

        return false;
    }

}
