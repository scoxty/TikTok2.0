package com.xty.middleware.rocketmq;

import com.alibaba.fastjson2.JSON;
import com.xty.service.FavoriteServiceImpl;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@RocketMQMessageListener(consumerGroup = "tiktok-favorite-consumer", topic = "favoriteTopic")
public class FavoriteConsumer implements RocketMQListener<MessageExt> {
    @Autowired
    private FavoriteServiceImpl favoriteServiceImpl;

    @Override
    public void onMessage(MessageExt msg) {
        FavoriteMsg favoriteMsg = JSON.parseObject(msg.getBody(), FavoriteMsg.class);
        favoriteServiceImpl.mapLock.lock();
        try {
            favoriteServiceImpl.map.computeIfAbsent(favoriteMsg.getUserId(), k -> new HashMap<>());
            favoriteServiceImpl.map.get(favoriteMsg.getUserId()).put(favoriteMsg.getVideoId(), favoriteMsg.getActionType());
        } finally {
            favoriteServiceImpl.mapLock.unlock();
        }
    }
}
