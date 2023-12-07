package com.xty.middleware.redis;

import io.netty.util.internal.ThreadLocalRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class UserInfo {
    @Autowired
    private RedisTemplate redisTemplate;

    public void setUserNameById(Long userId, String username) {
        String key = Keys.USER_KEY + Keys.DELIMITER + userId;
        redisTemplate.opsForHash().put(key, Keys.NAME_FIELD, username);
        int expireTime = 600 + ThreadLocalRandom.current().nextInt(31); // 随机600-630，防止雪崩
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    public String getUserNameById(Long userId) {
        String key = Keys.USER_KEY + Keys.DELIMITER + userId;
        return (String)redisTemplate.opsForHash().get(key, Keys.NAME_FIELD);
    }
}
