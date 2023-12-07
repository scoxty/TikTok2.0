package com.xty;

public enum ErrorCode {
    CODE_SUCCESS(0, "success"),

    CODE_SERVER_BUSY(1, "服务器开小差啦,稍后再来试一试"),
    CODE_DB_ERROR(2, "数据库繁忙,请稍后再试"),
    CODE_INVALID_PARAM(3, "参数错误"),

    CODE_INVALID_TOKEN(4, "请退出重新登陆"),
    CODE_LIMITER_COUNT(5, "请求次数过多，已被限制，稍后再试"),

    CODE_USER_NOT_FOUND(6, "用户不存在"),
    CODE_USER_ALREADY_EXISTS(7, "用户已存在"),

    CODE_INVALID_REGISTER_USERNAME(8, "用户名不合规:不能为空或长度不能大于32"),
    CODE_INVALID_REGISTER_PASSWORD(9, "密码不合规:不能为空或长度不能大于32"),

    CODE_INVALID_LOGIN_USERNAME(10, "用户名不合规:不能为空或长度不能大于32"),
    CODE_INVALID_LOGIN_PASSWORD(11, "密码不合规:不能为空或长度不能大于32"),
    CODE_WRONG_LOGIN_CREDENTIALS(12, "用户名或密码错误"),

    CODE_INVALID_VIDEO_TITLE(13, "视频标题不能为空"),
    CODE_INVALID_VIDEO_FILE(14, "上传文件为空"),
    CODE_INVALID_FILE_TYPE(15, "无效的文件类型"),
    CODE_INVALID_FILE_SIZE(16, "文件过大或过小"),
    CODE_UPLOAD_FILE_ERROR(17, "文件上传失败"),

    CODE_FOLLOW_MYSELF(18, "不能关注自己哦"),
    CODE_NOT_FRIEND(19, "对方并不是您的好友"),
    CODE_FOLLOW_REPEAT(20, "请勿重复关注"),
    CODE_CANCEL_FOLLOW_REPEAT(21, "请勿重复取关"),

    CODE_INVALID_COMMENT_ACTION(22, "这不是您的评论");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
