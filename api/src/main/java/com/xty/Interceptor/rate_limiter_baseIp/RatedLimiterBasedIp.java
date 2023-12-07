package com.xty.Interceptor.rate_limiter_baseIp;

import com.xty.ErrorCode;
import com.xty.middleware.redis.RatedLimiter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RatedLimiterBasedIp implements HandlerInterceptor {
    @Autowired
    private RatedLimiter ratedLimiter;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String addr = request.getRemoteAddr();
        String ip = addr.split(":")[0];

        boolean check = ratedLimiter.acquireBucketToken(ip);

        if (!check) {
            response.setStatus(ErrorCode.CODE_LIMITER_COUNT.getCode());
            response.getWriter().write(ErrorCode.CODE_LIMITER_COUNT.getMessage());
            return false;
        }

        return true;
    }
}
