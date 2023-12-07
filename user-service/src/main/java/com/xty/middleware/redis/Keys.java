package com.xty.middleware.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class Keys {
    @Autowired
    private RedisTemplate redisTemplate;

    public static final String DELIMITER = ":";

    // User 相关常量
    public static final String USER_KEY = "user";
    public static final String NAME_FIELD = "name";

    // List 类型相关常量
    public static final String TOKEN_KEY = "token";

    // Lock 和行为相关常量
    public static final String LOCK = "lock";

    public String getUserLockName(Long userId) {
        return NAME_FIELD + DELIMITER + LOCK + DELIMITER + userId;
    }
}
