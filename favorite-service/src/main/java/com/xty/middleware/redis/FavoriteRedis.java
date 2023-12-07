package com.xty.middleware.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Component
public class FavoriteRedis {
    @Autowired
    private RedisTemplate redisTemplate;

    public void setVideoFavoriteCountByVideoId(Long videoId, Long favoriteCount) {
        String key = Keys.VIDEO_KEY + Keys.DELIMITER + videoId;
        redisTemplate.opsForHash().put(key, Keys.VIDEO_FAVORITED_COUNT_FIELD, favoriteCount);
        Integer expireTime = 600 + ThreadLocalRandom.current().nextInt(31); // 随机600-630防止雪崩
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    public Long getVideoFavoriteCountByVideoId(Long videoId) {
        String key = Keys.VIDEO_KEY + Keys.DELIMITER + videoId;
        Integer favoriteCount = (Integer) redisTemplate.opsForHash().get(key, Keys.VIDEO_FAVORITED_COUNT_FIELD);
        if (favoriteCount == null) {
            return null;
        }
        return favoriteCount.longValue();
    }

    public void setUserFavoriteCountByUserId(Long userId, Long favoriteCount) {
        String key = Keys.USER_KEY + Keys.DELIMITER + userId;
        redisTemplate.opsForHash().put(key, Keys.FAVORITE_COUNT_FIELD, favoriteCount);
        Integer expireTime = 600 + ThreadLocalRandom.current().nextInt(31);
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    public Long getUserFavoriteCountByUserId(Long userId) {
        String key = Keys.USER_KEY + Keys.DELIMITER + userId;
        Integer favoriteCount = (Integer) redisTemplate.opsForHash().get(key, Keys.FAVORITE_COUNT_FIELD);
        if (favoriteCount == null) {
            return null;
        }
        return favoriteCount.longValue();
    }

    public void setTotalFavoriteCountByUserId(Long userId, Long favoriteCount) {
        String key = Keys.USER_KEY + Keys.DELIMITER + userId;
        redisTemplate.opsForHash().put(key, Keys.TOTAL_FAVORITE_FIELD, favoriteCount);
        Integer expireTime = 600 + ThreadLocalRandom.current().nextInt(31);
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    public Long getTotalFavoriteCountByUserId(Long userId) {
        String key = Keys.USER_KEY + Keys.DELIMITER + userId;
        Integer favoriteCount = (Integer) redisTemplate.opsForHash().get(key, Keys.TOTAL_FAVORITE_FIELD);
        if (favoriteCount == null) {
            return null;
        }
        return favoriteCount.longValue();
    }

    public void addFavoriteCount(Long userId, Long authorId, Long videoId) {
        String key1 = Keys.VIDEO_KEY + Keys.DELIMITER + videoId;
        String key2 = Keys.USER_KEY + Keys.DELIMITER + userId;
        String key3 = Keys.USER_KEY + Keys.DELIMITER + authorId;

        redisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.opsForHash().increment(key1, Keys.VIDEO_FAVORITED_COUNT_FIELD, 1);
                operations.opsForHash().increment(key2, Keys.FAVORITE_COUNT_FIELD, 1);
                operations.opsForHash().increment(key3, Keys.TOTAL_FAVORITE_FIELD, 1);
                //参数回调必须返回null，否则将抛出异常，可参考源码。
                return null;
            }
        });
    }

    public void delFavoriteCount(Long userId, Long authorId, Long videoId) {
        String key1 = Keys.VIDEO_KEY + Keys.DELIMITER + videoId;
        String key2 = Keys.USER_KEY + Keys.DELIMITER + userId;
        String key3 = Keys.USER_KEY + Keys.DELIMITER + authorId;

        redisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.opsForHash().increment(key1, Keys.VIDEO_FAVORITED_COUNT_FIELD, -1);
                operations.opsForHash().increment(key2, Keys.FAVORITE_COUNT_FIELD, -1);
                operations.opsForHash().increment(key3, Keys.TOTAL_FAVORITE_FIELD, -1);
                //参数回调必须返回null，否则将抛出异常，可参考源码。
                return null;
            }
        });
    }
}
