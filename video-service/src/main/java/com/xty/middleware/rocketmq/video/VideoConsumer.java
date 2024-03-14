package com.xty.middleware.rocketmq.video;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.xty.BloomFilterUtils;
import com.xty.OssUtil;
import com.xty.dal.mysql.mapper.VideoMapper;
import com.xty.dal.mysql.pojo.VideoPojo;
import com.xty.middleware.redis.Keys;
import com.xty.middleware.redis.VideoRedis;
import com.xty.service.VideoServiceImpl;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
@RocketMQMessageListener(consumerGroup = "tiktok-video-consumer", topic = "videoTopic")
public class VideoConsumer implements RocketMQListener<MessageExt> {
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private VideoRedis videoRedis;
    @Autowired
    private Keys keysRedis;
    @Autowired
    private VideoServiceImpl videoService;
    @Autowired
    private ThreadPoolExecutor videoExecutor;

    @Override
    public void onMessage(MessageExt msg) {
        VideoMsg videoMsg = JSON.parseObject(msg.getBody(), VideoMsg.class);

        // 上传文件到oss
        String videoName = UUID.randomUUID() + ".mp4";
        try {
            OssUtil.uploadVideo(videoMsg.getLocalVideoPath(), videoName);
        } catch (Exception e) {
            System.out.println("上传文件失败: " + e.getMessage());
        }

        // 截取视频封面并上传至oss
        String coverName = UUID.randomUUID() + ".jpg";
        try {
            String url = OssUtil.getVideoCover(videoName);
            OssUtil.uploadPicture(url, coverName);
        } catch (Exception e) {
            System.out.println("截取视频封面并上传失败: " + e.getMessage());
        }

        // 删除视频副本
        File file = new File(videoMsg.getLocalVideoPath());
        file.delete();

        // 保存元信息到mysql
        Date now = new Date();
        VideoPojo video = new VideoPojo(IdWorker.getId(), videoMsg.getUserId(), videoName, coverName, videoMsg.getTitle(), now, now);
        videoMapper.insert(video);

        // 异步修改redis和bloom过滤器
        // 提交任务到线程池
        videoExecutor.submit(()->{
            videoRedis.addVideo(video);
        });
        videoExecutor.submit(()->{
            videoService.delLocalCache();
            keysRedis.delUserHashField(videoMsg.getUserId(), Keys.WORK_COUNT_FIELD);
        });
        videoExecutor.submit(()->{
           BloomFilterUtils.insertAuthorId(videoMsg.getUserId());
        });

        System.out.println("视频消息处理成功!");
    }
}
