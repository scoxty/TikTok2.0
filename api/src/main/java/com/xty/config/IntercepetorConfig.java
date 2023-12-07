package com.xty.config;

import com.xty.Interceptor.count_limiter.LoginCountLimiter;
import com.xty.Interceptor.count_limiter.PublishCountLimiter;
import com.xty.Interceptor.count_limiter.RegisterCountLimiter;
import com.xty.Interceptor.jwt.Auth;
import com.xty.Interceptor.jwt.AuthWithoutLogin;
import com.xty.Interceptor.rate_limiter_baseIp.RatedLimiterBasedIp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class IntercepetorConfig implements WebMvcConfigurer {
    @Autowired
    private LoginCountLimiter loginCountLimiter;
    @Autowired
    private RegisterCountLimiter registerCountLimiter;
    @Autowired
    private PublishCountLimiter publishCountLimiter;
    @Autowired
    private RatedLimiterBasedIp ratedLimiterBasedIp;
    @Autowired
    private AuthWithoutLogin authWithoutLogin;
    @Autowired
    private Auth auth;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 限制访问次数的中间件
        registry.addInterceptor(loginCountLimiter).addPathPatterns("/douyin/user/login/");
        registry.addInterceptor(registerCountLimiter).addPathPatterns("/douyin/user/register/");
        registry.addInterceptor(publishCountLimiter).addPathPatterns("/douyin/publish/action/");

       // 限制访问速率的中间件
        registry.addInterceptor(ratedLimiterBasedIp).addPathPatterns("/douyin/user/",
                "/douyin/feed", "/douyin/publish/list/", "/douyin/favorite/action/", "/douyin/favorite/list/");

        // JWT认证中间件
        // 允许不登录
        registry.addInterceptor(authWithoutLogin).addPathPatterns("/douyin/user/",
                "/douyin/feed", "/douyin/publish/list/", "/douyin/favorite/list/");
        // 必须登录
        registry.addInterceptor(auth).addPathPatterns("/douyin/publish/action/",
                "/douyin/favorite/action/");
    }
}
