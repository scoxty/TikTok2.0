package com.xty.Interceptor.rate_limiter_baseIp;

import com.alibaba.fastjson2.JSONObject;
import com.xty.ErrorCode;
import com.xty.middleware.redis.RatedLimiter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;

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
            // 设置response状态
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");

            //返回的数据
            JSONObject res = new JSONObject();
            res.put("status_code", ErrorCode.CODE_LIMITER_COUNT.getCode());
            res.put("status_msg", ErrorCode.CODE_LIMITER_COUNT.getMessage());
            PrintWriter out = response.getWriter();
            out.write(res.toString());
            out.flush();
            out.close();

            return false;
        }

        return true;
    }
}
