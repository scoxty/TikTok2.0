package com.xty.Interceptor.jwt;

import com.xty.ErrorCode;
import com.xty.JwtUtil;
import com.xty.RequestContextUtil;
import com.xty.middleware.redis.TokenManager;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.concurrent.Executors;

@Component
public class Auth implements HandlerInterceptor {
    @Autowired
    private TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getParameter("token");
        String userIdStr;

        // 解析JWT
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userIdStr = claims.getSubject();
        } catch (Exception e) {
            response.setStatus(ErrorCode.CODE_INVALID_TOKEN.getCode());
            response.getWriter().write(ErrorCode.CODE_INVALID_TOKEN.getMessage());
            return false;
        }

        Long userId = Long.parseLong(userIdStr);

        // 若没过期查看token是否存在, 若在，给token续期, 若不在，则阻止后面函数执行
        if (!tokenManager.tokenIsExisted(userId)) {
            response.setStatus(ErrorCode.CODE_INVALID_TOKEN.getCode());
            response.getWriter().write(ErrorCode.CODE_INVALID_TOKEN.getMessage());
            return false;
        }
        Executors.newSingleThreadExecutor().submit(() -> {
            tokenManager.setToken(userId, token);
        });

        RequestContextUtil.setCurrentUserId(userId);
        return true;
    }
}
