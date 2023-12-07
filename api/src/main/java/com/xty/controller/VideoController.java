package com.xty.controller;

import com.google.protobuf.ByteString;
import com.xty.ErrorCode;
import com.xty.RequestContextUtil;
import com.xty.dubbo_gen.user.User;
import com.xty.dubbo_gen.video.*;
import com.xty.response.UserInfo;
import com.xty.response.VideoInfo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
public class VideoController {
    @DubboReference
    private VideoService videoService;

    private static final Integer MAX_FILE_SIZE = 50 * 1024 * 1024; // 50MB
    private static final Integer MIN_FILE_SIZE = 1024 * 1024; // 1MB

    private static final Logger log = LoggerFactory.getLogger(VideoController.class);

    @PostMapping("/douyin/publish/action/")
    public Map<String, Object> publishAction(@RequestPart("data") MultipartFile data,
                                             @RequestParam("token") String token,
                                             @RequestParam("title") String title) {
        log.info("/douyin/publish/action/被调用");
        log.info("/douyin/publish/action/的请求参数: " + "data = " + data + " token = " + token + " title = " + title);


        Map<String, Object> resp = new HashMap<>();

        Long userId = RequestContextUtil.getCurrentUserId();

        // 参数校验
        // 判断标题是否为空
        if (title == null || "".equals(title)) {
            resp.put("status_code", ErrorCode.CODE_INVALID_VIDEO_TITLE.getCode());
            resp.put("status_msg", ErrorCode.CODE_INVALID_VIDEO_FILE.getMessage());
            return resp;
        }
        // 判断上传内容是否为空
        if (data == null || data.isEmpty()) {
            resp.put("status_code", ErrorCode.CODE_INVALID_VIDEO_FILE.getCode());
            resp.put("status_msg", ErrorCode.CODE_INVALID_VIDEO_FILE.getMessage());
            return resp;
        }
        // 校验文件类型
        if (!isVideoFile(data)) {
            resp.put("status_code", ErrorCode.CODE_INVALID_FILE_TYPE.getCode());
            resp.put("status_msg", ErrorCode.CODE_INVALID_FILE_TYPE.getMessage());
            return resp;
        }
        // 校验文件大小
        long dataSize = data.getSize();
        if (dataSize < MIN_FILE_SIZE || dataSize > MAX_FILE_SIZE) {
            resp.put("status_code", ErrorCode.CODE_INVALID_FILE_SIZE.getCode());
            resp.put("status_msg", ErrorCode.CODE_INVALID_FILE_SIZE.getMessage());
            return resp;
        }

        byte[] dataBytes = null;
        try {
            dataBytes = data.getBytes();
        } catch (IOException e) {
            resp.put("status_code", ErrorCode.CODE_SERVER_BUSY.getCode());
            resp.put("status_msg", ErrorCode.CODE_SERVER_BUSY.getMessage());
        }

        // 调用rpc服务
        PublishActionRequest rpcRequest = PublishActionRequest.newBuilder()
                .setUserId(userId)
                .setTitle(title)
                .setData(ByteString.copyFrom(dataBytes))
                .build();
        PublishActionResponse rpcResponse = videoService.publishAction(rpcRequest);

        resp.put("status_code", rpcResponse.getStatusCode());
        resp.put("status_msg", rpcResponse.getStatusMsg());

        log.info("/douyin/publish/action/的响应参数: " + resp);

        return resp;
    }

    @GetMapping("/douyin/publish/list/")
    public Map<String, Object> getPublishList(@RequestParam Map<String, String> req) {
        log.info("/douyin/publish/list/被调用");
        log.info("/douyin/publish/list/的请求参数: " + req);

        Map<String, Object> resp = new HashMap<>();

        String userId = req.get("user_id");

        // 参数校验
        if (userId == null || "".equals(userId)) {
            resp.put("status_code", ErrorCode.CODE_INVALID_PARAM.getCode());
            resp.put("status_msg", ErrorCode.CODE_INVALID_PARAM.getMessage());
            return resp;
        }

        Long from_user_id = RequestContextUtil.getCurrentUserId();
        Long to_user_id = 0L;
        try {
            to_user_id = Long.parseLong(userId);
        } catch (NumberFormatException e) {
            resp.put("status_code", ErrorCode.CODE_INVALID_PARAM.getCode());
            resp.put("status_msg", ErrorCode.CODE_INVALID_PARAM.getMessage());
            return resp;
        }

        // 调用rpc服务
        PublishListRequest rpcRequest = PublishListRequest.newBuilder()
                .setFromUserId(from_user_id)
                .setToUserId(to_user_id)
                .build();
        PublishListResponse rpcResponse = videoService.getPublishList(rpcRequest);

        // 封装响应
        int n = rpcResponse.getVideoListCount();
        List<VideoInfo> videoList = new ArrayList<>();
        if (n > 0) {
            User author = rpcResponse.getVideoList(0).getAuthor();
            UserInfo userInfo = new UserInfo(
                    author.getId(),
                    author.getName(),
                    author.getFollowCount(),
                    author.getFollowerCount(),
                    author.getIsFollow(),
                    author.getAvatar(),
                    author.getBackgroundImage(),
                    author.getSignature(),
                    author.getTotalFavorited(),
                    author.getWorkCount(),
                    author.getFavoriteCount());

            for (int i = 0; i < n; i ++) {
                Video video = rpcResponse.getVideoList(i);
                VideoInfo videoInfo = new VideoInfo(
                        video.getId(),
                        userInfo,
                        video.getPlayUrl(),
                        video.getCoverUrl(),
                        video.getFavoriteCount(),
                        video.getCommentCount(),
                        video.getIsFavorite(),
                        video.getTitle()
                );
                videoList.add(videoInfo);
            }
        }

        resp.put("status_code", rpcResponse.getStatusCode());
        resp.put("status_msg", rpcResponse.getStatusMsg());
        resp.put("video_list", videoList);

        log.info("/douyin/publish/list/的响应参数: " + resp);

        return resp;
    }

    @GetMapping("/douyin/feed")
    public Map<String, Object> getFeedList(@RequestParam(required = false) Long latest_time, @RequestParam(required = false) String token) {
        log.info("/douyin/feed被调用");
        log.info("/douyin/feed的请求参数: " + "latest_time = " + latest_time + " token = " + token);

        Map<String, Object> resp = new HashMap<>();
        Long latestTime = new Date().getTime();
        Long userId = RequestContextUtil.getCurrentUserId();

        if (latest_time != null) {
            if (latest_time < 0) {
                resp.put("status_code", ErrorCode.CODE_INVALID_PARAM.getCode());
                resp.put("status_msg", ErrorCode.CODE_INVALID_PARAM.getMessage());
                return resp;
            }

            latestTime = Math.max(latest_time * 1000, latestTime);
        }

        // 调用rpc服务
        FeedRequest rpcRequest = FeedRequest.newBuilder()
                .setUserId(userId)
                .setLatestTime(latestTime)
                .build();
        FeedResponse rpcResponse = videoService.getVideoFeed(rpcRequest);

        // 封装响应
        int n = rpcResponse.getVideoListCount();
        List<VideoInfo> videoList = new ArrayList<>();
        if (n > 0) {
            for (int i = 0; i < n; i ++) {
                Video video = rpcResponse.getVideoList(i);
                User author = video.getAuthor();
                UserInfo userInfo = new UserInfo(
                        author.getId(),
                        author.getName(),
                        author.getFollowCount(),
                        author.getFollowerCount(),
                        author.getIsFollow(),
                        author.getAvatar(),
                        author.getBackgroundImage(),
                        author.getSignature(),
                        author.getTotalFavorited(),
                        author.getWorkCount(),
                        author.getFavoriteCount());
                VideoInfo videoInfo = new VideoInfo(
                        video.getId(),
                        userInfo,
                        video.getPlayUrl(),
                        video.getCoverUrl(),
                        video.getFavoriteCount(),
                        video.getCommentCount(),
                        video.getIsFavorite(),
                        video.getTitle()
                );
                videoList.add(videoInfo);
            }
        }

        resp.put("status_code", rpcResponse.getStatusCode());
        resp.put("status_msg", rpcResponse.getStatusMsg());
        resp.put("video_list", videoList);
        resp.put("next_time", rpcResponse.getNextTime());

        log.info("/douyin/feed的响应参数: " + resp);

        return resp;
    }

    public boolean isVideoFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (fileName == null) {
            return false;
        }
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        return extension.matches("mp4|avi|mov|flv|wmv|mkv");
    }


}
