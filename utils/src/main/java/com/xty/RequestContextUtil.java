package com.xty;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class RequestContextUtil {

    public static void setCurrentUserId(Long userId) {
        RequestContextHolder.currentRequestAttributes().setAttribute(
            "user_id", userId, RequestAttributes.SCOPE_REQUEST);
    }

    public static Long getCurrentUserId() {
        return (Long) RequestContextHolder.currentRequestAttributes().getAttribute(
            "user_id", RequestAttributes.SCOPE_REQUEST);
    }
}
