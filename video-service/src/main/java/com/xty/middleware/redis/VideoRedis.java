package com.xty.middleware.redis;

import com.alibaba.fastjson2.JSON;
import com.xty.dal.mysql.pojo.VideoPojo;
import io.netty.util.internal.ThreadLocalRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class VideoRedis {
    @Autowired
    private RedisTemplate redisTemplate;

    public void addVideo(VideoPojo video) {
        String value = JSON.toJSONString(video);
        redisTemplate.opsForZSet().add(Keys.VIDEO_LIST, value, (double)video.getCreatedTime().getTime());
    }

    public List<VideoPojo> getVideos(Long latestTime) {
        List<VideoPojo> videoPojoList = new ArrayList<>();
        Set<String> feed = redisTemplate.opsForZSet().reverseRangeByScore(Keys.VIDEO_LIST, 0, (double) latestTime, 0, 29);
        for (String feedVideo : feed) {
            VideoPojo videoPojo = JSON.parseObject(feedVideo, VideoPojo.class);
            videoPojoList.add(videoPojo);
        }
        return videoPojoList;
    }

    public void setWorkCount(Long userId, Long workCount) {
        String key = Keys.USER_KEY + Keys.DELIMITER + userId;
        redisTemplate.opsForHash().put(key, Keys.WORK_COUNT_FIELD, workCount);
        int expireTime = 600 + ThreadLocalRandom.current().nextInt(31); // 随机600-630，防止雪崩
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    public Long getWorkCount(Long userId) {
        String key = Keys.USER_KEY + Keys.DELIMITER + userId;
        Integer res = (Integer) redisTemplate.opsForHash().get(key, Keys.WORK_COUNT_FIELD);
        Long workCount = null;
        if (res != null) {
            workCount = res.longValue();
        }
        return workCount;
    }
}
