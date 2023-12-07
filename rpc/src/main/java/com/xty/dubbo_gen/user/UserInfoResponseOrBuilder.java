// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: user.proto

package com.xty.dubbo_gen.user;

public interface UserInfoResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:douyin.core.UserInfoResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 状态码，0-成功，其他值-失败
   * </pre>
   *
   * <code>int32 status_code = 1;</code>
   * @return The statusCode.
   */
  int getStatusCode();

  /**
   * <pre>
   * 返回状态描述
   * </pre>
   *
   * <code>string status_msg = 2;</code>
   * @return The statusMsg.
   */
  java.lang.String getStatusMsg();
  /**
   * <pre>
   * 返回状态描述
   * </pre>
   *
   * <code>string status_msg = 2;</code>
   * @return The bytes for statusMsg.
   */
  com.google.protobuf.ByteString
      getStatusMsgBytes();

  /**
   * <pre>
   * 用户信息
   * </pre>
   *
   * <code>.douyin.core.User user = 3;</code>
   * @return Whether the user field is set.
   */
  boolean hasUser();
  /**
   * <pre>
   * 用户信息
   * </pre>
   *
   * <code>.douyin.core.User user = 3;</code>
   * @return The user.
   */
  com.xty.dubbo_gen.user.User getUser();
  /**
   * <pre>
   * 用户信息
   * </pre>
   *
   * <code>.douyin.core.User user = 3;</code>
   */
  com.xty.dubbo_gen.user.UserOrBuilder getUserOrBuilder();
}
