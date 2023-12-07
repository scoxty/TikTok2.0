package com.xty.middleware.redis;

public class Keys {
    public static final String DELIMITER = ":";

    // Video 相关常量
    public static final String VIDEO_KEY = "video";
    public static final String COMMENT_COUNT_FIELD = "commentCount";
    public static final String VIDEO_FAVORITED_COUNT_FIELD = "favoritedCount";

    // User 相关常量
    public static final String USER_KEY = "user";
    public static final String WORK_COUNT_FIELD = "workCount";
    public static final String NAME_FIELD = "name";
    public static final String TOTAL_FAVORITE_FIELD = "totalFavorited";
    public static final String FAVORITE_COUNT_FIELD = "favoriteCount";

    // List 类型相关常量
    public static final String FAVORITE_LIST = "favoriteList";
    public static final String FOLLOW_LIST = "followList";
    public static final String FOLLOWER_LIST = "followerList";

    public static final String VIDEO_LIST = "videos";

    public static final String TOKEN_KEY = "token";

    public static final String MONTHLY_ACTIVE = "monthlyActive";

    // Lock 和行为相关常量
    public static final String LOCK = "lock";
    public static final String FOLLOW_ACTION = "followAction";
    public static final long RETRY_TIME = 30; // 毫秒
    public static final int KEY_EXISTS_AND_NOT_SET = 0;
    public static final int KEY_UPDATED = 1;
    public static final int KEY_NOT_EXISTS_IN_BOTH = 2;

    public static String getFavoriteLockName(Long id, String field) {
        String lockName = null;
        switch (field) {
            case FAVORITE_LIST:
                lockName = FAVORITE_LIST + DELIMITER + id + DELIMITER + LOCK;
                break;
            case VIDEO_FAVORITED_COUNT_FIELD:
                lockName = VIDEO_FAVORITED_COUNT_FIELD + DELIMITER + id + DELIMITER + LOCK;
                break;
            case TOTAL_FAVORITE_FIELD:
                lockName = TOTAL_FAVORITE_FIELD + DELIMITER + id + DELIMITER + LOCK;
                break;
            case FAVORITE_COUNT_FIELD:
                lockName = FAVORITE_COUNT_FIELD + DELIMITER + id + DELIMITER + LOCK;
                break;
            default:
                return null;
        }

        return lockName;
    }


}
