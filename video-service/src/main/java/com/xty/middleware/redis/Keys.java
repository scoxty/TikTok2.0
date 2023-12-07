package com.xty.middleware.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class Keys {
    @Autowired
    private RedisTemplate redisTemplate;

    public static final String DELIMITER = ":";

    // Video 相关常量
    public static final String VIDEO_KEY = "video";
    public static final String COMMENT_COUNT_FIELD = "commentCount";
    public static final String VIDEO_FAVORITED_COUNT_FIELD = "favoritedCount";

    // User 相关常量
    public static final String USER_KEY = "user";
    public static final String WORK_COUNT_FIELD = "workCount";
    public static final String NAME_FIELD = "name";
    public static final String TOTAL_FAVORITE_FIELD = "totalFavorited";
    public static final String FAVORITE_COUNT_FIELD = "favoriteCount";

    public static final String VIDEO_LIST = "videos";
    // Lock 和行为相关常量
    public static final String LOCK = "lock";

    public String getVideoLockName(Long userId) {
        return WORK_COUNT_FIELD + DELIMITER + LOCK + DELIMITER + userId;
    }

    public void delUserHashField(Long userId, String field) {
        String key = USER_KEY + DELIMITER + userId;
        redisTemplate.opsForHash().delete(key, field);
    }

}
