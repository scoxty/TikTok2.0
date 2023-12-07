package com.xty;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

public class LocalCacheUtils {
    private static Cache<String, Object> localCache;

    static {
        localCache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterAccess(100L, TimeUnit.SECONDS)
                .build();
    }

    public static void put(String key, Object value) {
        localCache.put(key, value);
    }

    public static Object get(String key) {
        return localCache.getIfPresent(key);
    }
}
