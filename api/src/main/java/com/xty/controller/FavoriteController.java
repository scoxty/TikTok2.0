package com.xty.controller;

import com.xty.ErrorCode;
import com.xty.RequestContextUtil;
import com.xty.dubbo_gen.favorite.*;
import com.xty.response.UserInfo;
import com.xty.response.VideoInfo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FavoriteController {
    @DubboReference
    private FavoriteService favoriteService;
    private static final Logger log = LoggerFactory.getLogger(FavoriteController.class);

    @PostMapping("/douyin/favorite/action/")
    public Map<String, Object> favoriteAction(@RequestParam Map<String, String> req) {
        log.info("/douyin/favorite/action/被调用");
        log.info("/douyin/favorite/action/的请求参数为: " + req);

        Map<String, Object> resp = new HashMap<>();

        Long userId = RequestContextUtil.getCurrentUserId();
        Long videoId = null;
        Integer actionType = null;

        // 参数校验
        try {
            videoId = Long.parseLong(req.get("video_id"));
            actionType = Integer.parseInt(req.get("action_type"));
        } catch (Exception e) {
            resp.put("status_code", ErrorCode.CODE_INVALID_PARAM.getCode());
            resp.put("status_msg", ErrorCode.CODE_INVALID_PARAM.getMessage());
        }

        // 调用rpc服务
        FavoriteActionRequest rpcRequest = FavoriteActionRequest.newBuilder()
                .setUserId(userId)
                .setVideoId(videoId)
                .setActionType(actionType)
                .build();
        FavoriteActionResponse rpcResponse = favoriteService.favoriteAction(rpcRequest);

        resp.put("status_code", rpcResponse.getStatusCode());
        resp.put("status_msg", rpcResponse.getStatusMsg());

        log.info("/douyin/favorite/action/的响应结果为: " + resp);

        return resp;
    }

    @GetMapping("/douyin/favorite/list/")
    public Map<String, Object> getFavoriteList(@RequestParam Map<String, String> req) {
        log.info("/douyin/favorite/list/被调用");
        log.info("/douyin/favorite/list/的请求参数是: " + req);

        Map<String, Object> resp = new HashMap<>();

        Long from_user_id = RequestContextUtil.getCurrentUserId();
        Long to_user_id = null;

        // 参数校验
        try {
            to_user_id = Long.parseLong(req.get("user_id"));
        } catch (Exception e) {
            resp.put("status_code", ErrorCode.CODE_INVALID_PARAM.getCode());
            resp.put("status_code", ErrorCode.CODE_INVALID_PARAM.getMessage());
            return resp;
        }

        // 调用rpc服务
        FavoriteListRequest rpcRequest = FavoriteListRequest.newBuilder()
                .setFromUserId(from_user_id)
                .setToUserId(to_user_id)
                .build();
        FavoriteListResponse rpcResponse = favoriteService.getFavoriteList(rpcRequest);

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

        resp.put("status_code", rpcResponse.getStatusMsg());
        resp.put("status_msg", rpcResponse.getStatusMsg());
        resp.put("video_list", videoList);

        log.info("/douyin/favorite/list/的响应结果是: " + resp);

        return resp;
    }

}
