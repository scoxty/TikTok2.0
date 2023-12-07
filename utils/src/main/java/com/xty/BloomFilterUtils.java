package com.xty;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

// TODO: 换成redis
public class BloomFilterUtils {
    private static final int EXPECTED_INSERTIONS = 100000;
    private static final double FPP = 0.03;
    private static BloomFilter<String> userBloomFilter;
    private static BloomFilter<Long> workCountBloomFilter;
    private static BloomFilter<String> isFavoriteBloom;
    private static BloomFilter<Long> favoriteVideoIdBloom;

    static {
        userBloomFilter = BloomFilter.create(Funnels.unencodedCharsFunnel(), EXPECTED_INSERTIONS, FPP);
        workCountBloomFilter = BloomFilter.create(Funnels.longFunnel(), EXPECTED_INSERTIONS, FPP);
        isFavoriteBloom = BloomFilter.create(Funnels.unencodedCharsFunnel(), EXPECTED_INSERTIONS, FPP);
        favoriteVideoIdBloom = BloomFilter.create(Funnels.longFunnel(), EXPECTED_INSERTIONS, FPP);
    }

    public static void insertUserName(String username) {
        userBloomFilter.put(username);
    }

    public static boolean hasUserName(String username) {
        return userBloomFilter.mightContain(username);
    }

    public static void insertAuthorId(Long userId) {
        workCountBloomFilter.put(userId);
    }

    public static boolean hasAuthorId(Long userId) {
        return workCountBloomFilter.mightContain(userId);
    }

    public static void insertIsFavorite(Long userId, Long videoId) {
        String key = userId + "" + videoId;
        isFavoriteBloom.put(key);
    }

    public static void insertFavoriteVideoId(Long videoId) {
        favoriteVideoIdBloom.put(videoId);
    }

    public static boolean hasFavoriteVideoId(Long videoId) {
        return favoriteVideoIdBloom.mightContain(videoId);
    }

    public static boolean hasIsFavorite(Long userId, Long videoId) {
        String key = userId + "" + videoId;
        return isFavoriteBloom.mightContain(key);
    }
}
