package com.xty.middleware.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class TokenManager {
    @Autowired
    private RedisTemplate redisTemplate;
    public static final long JWT_TTL = 60 * 60 * 1000L * 24 * 7;

    public void setToken(Long userId, String token) {
        String key = Keys.TOKEN_KEY + Keys.DELIMITER + userId;
        redisTemplate.opsForValue().set(key, token, JWT_TTL, TimeUnit.MILLISECONDS);
    }

    public boolean tokenIsExisted(Long userId) {
        String key = Keys.TOKEN_KEY + Keys.DELIMITER + userId.toString();
        return redisTemplate.hasKey(key);
    }
}
