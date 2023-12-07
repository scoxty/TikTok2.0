// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: favorite.proto

package com.xty.dubbo_gen.favorite;

public interface FavoriteActionRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:douyin.extra.first.FavoriteActionRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 用户鉴权token
   * </pre>
   *
   * <code>int64 user_id = 1;</code>
   * @return The userId.
   */
  long getUserId();

  /**
   * <pre>
   * 视频id
   * </pre>
   *
   * <code>int64 video_id = 2;</code>
   * @return The videoId.
   */
  long getVideoId();

  /**
   * <pre>
   * 1-点赞，2-取消点赞
   * </pre>
   *
   * <code>int32 action_type = 3;</code>
   * @return The actionType.
   */
  int getActionType();
}
