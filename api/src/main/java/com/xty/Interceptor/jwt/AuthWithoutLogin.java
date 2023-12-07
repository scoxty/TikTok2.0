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
public class AuthWithoutLogin implements HandlerInterceptor {
    @Autowired
    private TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getParameter("token");
        Long from_user_id;

        // 解析JWT
        try {
            Claims claims = JwtUtil.parseJWT(token);
            from_user_id = Long.parseLong(claims.getSubject());
        } catch (Exception e) {
            from_user_id = 0L; // 设置访客id
        }


        // token为过期且在redis存在，则自动刷新
        if (tokenManager.tokenIsExisted(from_user_id)) {
            Long finalFrom_user_id = from_user_id;
            Executors.newSingleThreadExecutor().submit(() -> {
                tokenManager.setToken(finalFrom_user_id, token);
            });
        }

        RequestContextUtil.setCurrentUserId(from_user_id);

        return true;
    }
}
