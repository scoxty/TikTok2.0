package com.xty.middleware.redis;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.vavr.control.Try;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class CountLimiter {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;
    @Autowired
    private RateLimiterRegistry rateLimiterRegistry;

    private CircuitBreaker loginCircuitBreaker;
    private CircuitBreaker registerCircuitBreaker;
    private CircuitBreaker uploadCircuitBreaker;
    private RateLimiter loginRateLimiter;
    private RateLimiter registerRateLimiter;
    private RateLimiter uploadRateLimiter;

    // 限流时间设置
    private static final long LIMITER_TIME = 2 * 60 * 60 * 1000; // 2 小时，转换为毫秒

    // 限流类型
    private static final String LOGIN_LIMIT = "loginLimit";
    private static final String REGISTER_LIMIT = "registerLimit";
    private static final String UPLOAD_LIMIT = "uploadLimit";

    // TODO: 开发完成待修改相应值
    // 各类型的最大尝试次数
    private static final int LOGIN_MAX_COUNT = 100;
    private static final int REGISTER_MAX_COUNT = 100;
    private static final int UPLOAD_MAX_COUNT = 100;

    @PostConstruct
    public void init() {
        loginCircuitBreaker = circuitBreakerRegistry.circuitBreaker("redisCircuitBreaker");
        registerCircuitBreaker = circuitBreakerRegistry.circuitBreaker("redisCircuitBreaker");
        uploadCircuitBreaker = circuitBreakerRegistry.circuitBreaker("redisCircuitBreaker");
        loginRateLimiter = rateLimiterRegistry.rateLimiter("localRateLimiter");
        registerRateLimiter = rateLimiterRegistry.rateLimiter("localRateLimiter");
        uploadRateLimiter = rateLimiterRegistry.rateLimiter("localRateLimiter");
    }

    public boolean incrementLoginLimiterCount(String ip) {
        // 使用CircuitBreaker进行熔断处理
        return Try.ofSupplier(CircuitBreaker.decorateSupplier(loginCircuitBreaker, () -> {
                    String key = LOGIN_LIMIT + Keys.DELIMITER + ip;
                    redisTemplate.opsForValue().setIfAbsent(key, 0, LIMITER_TIME, TimeUnit.MILLISECONDS);
                    Integer cnt = (Integer) redisTemplate.opsForValue().get(key);

                    if (cnt < LOGIN_MAX_COUNT) {
                        redisTemplate.opsForValue().increment(key);
                        return true;
                    }

                    return false;
                }))
                // 使用RateLimit作为降级策略
                .recover(throwable -> Try.ofSupplier(RateLimiter.decorateSupplier(loginRateLimiter, () -> {
                    System.out.println("Fallback due to Redis failure, using local rate limiter.");
                    return true;
                })).getOrElse(false)) // 如果RateLimit也失败，请使用false作为默认恢复值
                .get();
    }


    public boolean incrementRegisterLimiterCount(String ip) {
        // 使用CircuitBreaker进行熔断处理
        return Try.ofSupplier(CircuitBreaker.decorateSupplier(registerCircuitBreaker, () -> {
                    String key = REGISTER_LIMIT + Keys.DELIMITER + ip;
                    redisTemplate.opsForValue().setIfAbsent(key, 0, LIMITER_TIME, TimeUnit.MILLISECONDS);
                    Integer cnt = (Integer) redisTemplate.opsForValue().get(key);

                    if (cnt < REGISTER_MAX_COUNT) {
                        redisTemplate.opsForValue().increment(key);
                        return true;
                    }

                    return false;
                }))
                // 使用RateLimit作为降级策略
                .recover(throwable -> Try.ofSupplier(RateLimiter.decorateSupplier(registerRateLimiter, () -> {
                    System.out.println("Fallback due to Redis failure, using local rate limiter.");
                    return true;
                })).getOrElse(false)) // 如果RateLimit也失败，请使用false作为默认恢复值
                .get();
    }

    public boolean incrementPublishLimiterCount(String ip) {
        // 使用CircuitBreaker进行熔断处理
        return Try.ofSupplier(CircuitBreaker.decorateSupplier(uploadCircuitBreaker, () -> {
                    String key = UPLOAD_LIMIT + Keys.DELIMITER + ip;
                    redisTemplate.opsForValue().setIfAbsent(key, 0, LIMITER_TIME, TimeUnit.MILLISECONDS);
                    Integer cnt = (Integer) redisTemplate.opsForValue().get(key);

                    if (cnt < UPLOAD_MAX_COUNT) {
                        redisTemplate.opsForValue().increment(key);
                        return true;
                    }

                    return false;
                }))
                // 使用RateLimit作为降级策略
                .recover(throwable -> Try.ofSupplier(RateLimiter.decorateSupplier(uploadRateLimiter, () -> {
                    System.out.println("Fallback due to Redis failure, using local rate limiter.");
                    return true;
                })).getOrElse(false)) // 如果RateLimit也失败，请使用false作为默认恢复值
                .get();
    }
}
