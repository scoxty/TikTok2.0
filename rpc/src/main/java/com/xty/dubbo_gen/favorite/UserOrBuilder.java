// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: favorite.proto

package com.xty.dubbo_gen.favorite;

public interface UserOrBuilder extends
    // @@protoc_insertion_point(interface_extends:douyin.extra.first.User)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 用户id
   * </pre>
   *
   * <code>int64 id = 1;</code>
   * @return The id.
   */
  long getId();

  /**
   * <pre>
   * 用户名称
   * </pre>
   *
   * <code>string name = 2;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <pre>
   * 用户名称
   * </pre>
   *
   * <code>string name = 2;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <pre>
   * 关注总数
   * </pre>
   *
   * <code>int64 follow_count = 3;</code>
   * @return The followCount.
   */
  long getFollowCount();

  /**
   * <pre>
   * 粉丝总数
   * </pre>
   *
   * <code>int64 follower_count = 4;</code>
   * @return The followerCount.
   */
  long getFollowerCount();

  /**
   * <pre>
   * true-已关注，false-未关注
   * </pre>
   *
   * <code>bool is_follow = 5;</code>
   * @return The isFollow.
   */
  boolean getIsFollow();

  /**
   * <pre>
   *用户头像
   * </pre>
   *
   * <code>string avatar = 6;</code>
   * @return The avatar.
   */
  java.lang.String getAvatar();
  /**
   * <pre>
   *用户头像
   * </pre>
   *
   * <code>string avatar = 6;</code>
   * @return The bytes for avatar.
   */
  com.google.protobuf.ByteString
      getAvatarBytes();

  /**
   * <pre>
   *用户个人页顶部大图
   * </pre>
   *
   * <code>string background_image = 7;</code>
   * @return The backgroundImage.
   */
  java.lang.String getBackgroundImage();
  /**
   * <pre>
   *用户个人页顶部大图
   * </pre>
   *
   * <code>string background_image = 7;</code>
   * @return The bytes for backgroundImage.
   */
  com.google.protobuf.ByteString
      getBackgroundImageBytes();

  /**
   * <pre>
   *个人简介
   * </pre>
   *
   * <code>string signature = 8;</code>
   * @return The signature.
   */
  java.lang.String getSignature();
  /**
   * <pre>
   *个人简介
   * </pre>
   *
   * <code>string signature = 8;</code>
   * @return The bytes for signature.
   */
  com.google.protobuf.ByteString
      getSignatureBytes();

  /**
   * <pre>
   *获赞数量
   * </pre>
   *
   * <code>int64 total_favorited = 9;</code>
   * @return The totalFavorited.
   */
  long getTotalFavorited();

  /**
   * <pre>
   *作品数量
   * </pre>
   *
   * <code>int64 work_count = 10;</code>
   * @return The workCount.
   */
  long getWorkCount();

  /**
   * <pre>
   *点赞数量
   * </pre>
   *
   * <code>int64 favorite_count = 11;</code>
   * @return The favoriteCount.
   */
  long getFavoriteCount();
}
