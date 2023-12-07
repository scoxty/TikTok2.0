package com.xty;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyuncs.exceptions.ClientException;
import com.xty.business.VideoConstant;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.Date;
import java.util.UUID;

public class OssUtil {
    private static OSS getOssClient() throws ClientException {
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        return new OSSClientBuilder().build(VideoConstant.endPoint, credentialsProvider);
    }

    public static void uploadVideo(String localPath, String videoName) throws ClientException, OSSException {
        OSS ossClient = getOssClient();

        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(VideoConstant.bucketName, VideoConstant.videoDir + videoName, new File(localPath));
            ossClient.putObject(putObjectRequest);
        } finally {
            ossClient.shutdown();
        }
    }

    public static void uploadPicture(String url, String pictureName) throws ClientException, IOException {
        OSS ossClient = getOssClient();

        try {
            InputStream inputStream = new URL(url).openStream();
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(VideoConstant.bucketName, VideoConstant.pictureDir + pictureName, inputStream);
            // 创建PutObject请求, 上传图片。
            ossClient.putObject(putObjectRequest);
        } finally {
            ossClient.shutdown();
        }
    }

    public static String getVideoCover(String videoName) throws ClientException, IOException {
        OSS ossClient = getOssClient();
        URL signedUrl = null;
        try {
            // 使用精确时间模式截取视频1s处的内容，输出为JPG格式的图片，宽度为720，高度为1280。
            String style = "video/snapshot,t_1000,f_jpg,w_720,h_1280";
            // 指定过期时间为10分钟。
            Date expiration = new Date(new Date().getTime() + 1000 * 60 * 10 );
            // 生成请求
            GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(VideoConstant.bucketName, VideoConstant.videoDir + videoName, HttpMethod.GET);
            req.setExpiration(expiration);
            req.setProcess(style);
            // 获取url
            signedUrl = ossClient.generatePresignedUrl(req);
            Object content = signedUrl.getContent();
        }  finally {
            ossClient.shutdown();
        }
        return signedUrl.toString();
    }
}
