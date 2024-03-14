package com.xty.controller;

import com.xty.ErrorCode;
import com.xty.RequestContextUtil;
import com.xty.dubbo_gen.user.*;
import com.xty.response.UserInfo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @DubboReference
    private UserService userService;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/douyin/user/register/")
    public Map<String, Object> register(@RequestParam Map<String, String> req) {
        log.info("/douyin/user/register/被调用");
        log.info("/douyin/user/register/请求参数: " + req);

        String username = req.get("username");
        String password = req.get("password");
        HashMap<String, Object> resp = new HashMap<>();

        // 参数校验
        if (username == null) {
            resp.put("status_code", ErrorCode.CODE_INVALID_REGISTER_USERNAME.getCode());
            resp.put("status_msg", ErrorCode.CODE_INVALID_REGISTER_USERNAME.getMessage());
            return resp;
        }
        username = username.trim(); // 删除头尾空白字符
        if (username.length() == 0 || username.length() > 32) {
            resp.put("status_code", ErrorCode.CODE_INVALID_REGISTER_USERNAME.getCode());
            resp.put("status_msg", ErrorCode.CODE_INVALID_REGISTER_USERNAME.getMessage());
            return resp;
        }
        if (password == null || password.equals("") || password.length() > 32) {
            resp.put("status_code", ErrorCode.CODE_INVALID_REGISTER_PASSWORD.getCode());
            resp.put("status_msg", ErrorCode.CODE_INVALID_REGISTER_PASSWORD.getMessage());
            return resp;
        }

        // 调用rpc服务
        UserRegisterRequest rpcRequest = UserRegisterRequest.newBuilder().
                setUsername(username).
                setPassword(password).
                build();
        UserRegisterResponse rpcResponse = userService.register(rpcRequest);

        // 返回响应
        resp.put("status_code", rpcResponse.getStatusCode());
        resp.put("status_msg", rpcResponse.getStatusMsg());
        resp.put("user_id", rpcResponse.getUserId());
        resp.put("token", rpcResponse.getToken());

        log.info("/douyin/user/register/的响应结果: " + resp);

        return resp;
    }

    @PostMapping("/douyin/user/login/")
    public Map<String, Object> login(@RequestParam Map<String, String> req) {
        log.info("/douyin/user/login/被调用");
        log.info("/douyin/user/login/的请求参数: " + req);

        String username = req.get("username");
        String password = req.get("password");
        HashMap<String, Object> resp = new HashMap<>();

        // 参数校验
        if (username == null) {
            resp.put("status_code", ErrorCode.CODE_INVALID_LOGIN_USERNAME.getCode());
            resp.put("status_msg", ErrorCode.CODE_INVALID_LOGIN_USERNAME.getMessage());
            return resp;
        }
        username = username.trim(); // 删除头尾空白字符
        if (username.length() == 0 || username.length() > 32) {
            resp.put("status_code", ErrorCode.CODE_INVALID_LOGIN_USERNAME.getCode());
            resp.put("status_msg", ErrorCode.CODE_INVALID_LOGIN_USERNAME.getMessage());
            return resp;
        }
        if (password == null || password.equals("") || password.length() > 32) {
            resp.put("status_code", ErrorCode.CODE_INVALID_LOGIN_USERNAME.getCode());
            resp.put("status_msg", ErrorCode.CODE_INVALID_LOGIN_USERNAME.getMessage());
            return resp;
        }

        // 调用rpc服务
        UserLoginRequest rpcRequest = UserLoginRequest.newBuilder().
                setUsername(username).
                setPassword(password).
                build();
        UserLoginResponse rpcResponse = userService.login(rpcRequest);

        resp.put("status_code", rpcResponse.getStatusCode());
        resp.put("status_msg", rpcResponse.getStatusMsg());
        resp.put("user_id", rpcResponse.getUserId());
        resp.put("token", rpcResponse.getToken());

        log.info("/douyin/user/login/的响应结果: " + resp);

        return resp;
    }

    @GetMapping("/douyin/user/")
    public Map<String, Object> getInfo(@RequestParam Map<String, String> req) {
        log.info("/douyin/user/被调用");
        log.info("/douyin/user/的请求参数: " + req);

        String userId = req.get("user_id");
        HashMap<String, Object> resp = new HashMap<>();

        // 参数校验
        if (userId == null || userId.equals("")) {
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

        UserInfoRequest rpcRequest = UserInfoRequest.newBuilder().
                setFromUserId(from_user_id).
                setToUserId(to_user_id).
                build();
        UserInfoResponse rpcResponse = userService.getUserInfo(rpcRequest);

        UserInfo userInfo = new UserInfo(
                rpcResponse.getUser().getId(),
                rpcResponse.getUser().getName(),
                rpcResponse.getUser().getFollowCount(),
                rpcResponse.getUser().getFollowerCount(),
                rpcResponse.getUser().getIsFollow(),
                rpcResponse.getUser().getAvatar(),
                rpcResponse.getUser().getBackgroundImage(),
                rpcResponse.getUser().getSignature(),
                rpcResponse.getUser().getTotalFavorited(),
                rpcResponse.getUser().getWorkCount(),
                rpcResponse.getUser().getFavoriteCount()
        );

        resp.put("status_code", rpcResponse.getStatusCode());
        resp.put("status_msg", rpcResponse.getStatusMsg());
        resp.put("user", userInfo);

        log.info("/douyin/user/的响应结果: " + resp);

        return resp;
    }


}
