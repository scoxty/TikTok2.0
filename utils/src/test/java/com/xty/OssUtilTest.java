package com.xty;

import com.aliyuncs.exceptions.ClientException;
import lombok.var;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.UUID;

public class OssUtilTest {
    @Test
    public void testPublishVideo()  {
        try {
            String localPath = "D:\\HONOR Share\\Screenshot\\简易音乐播放器.mp4";
            String videoName = UUID.randomUUID() + ".mp4";
            OssUtil.uploadVideo(localPath, videoName);
            System.out.println("上传成功!");
        } catch (Exception e) {
            System.out.println("上传文件失败: " + e.getMessage());
        }
    }

    @Test
    public void testGetVideoCover() {
        try {
            String videoName = "1c1ed3a4-8376-45b4-863f-e96c09a9f.mp4";
            String url = OssUtil.getVideoCover(videoName);

            String coverName = UUID.randomUUID() + ".jpg";

            OssUtil.uploadPicture(url, coverName);
            System.out.println("视频封面截取成功!");
        } catch (Exception e) {
            System.out.println("获取视频封面失败: " + e.getMessage());
        }
    }

    @Test
    public void testUrlLen() {
    }
}
