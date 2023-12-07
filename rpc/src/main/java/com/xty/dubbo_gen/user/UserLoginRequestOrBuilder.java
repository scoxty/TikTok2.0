// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: user.proto

package com.xty.dubbo_gen.user;

public interface UserLoginRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:douyin.core.UserLoginRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 登录用户名
   * </pre>
   *
   * <code>string username = 1;</code>
   * @return The username.
   */
  java.lang.String getUsername();
  /**
   * <pre>
   * 登录用户名
   * </pre>
   *
   * <code>string username = 1;</code>
   * @return The bytes for username.
   */
  com.google.protobuf.ByteString
      getUsernameBytes();

  /**
   * <pre>
   * 登录密码
   * </pre>
   *
   * <code>string password = 2;</code>
   * @return The password.
   */
  java.lang.String getPassword();
  /**
   * <pre>
   * 登录密码
   * </pre>
   *
   * <code>string password = 2;</code>
   * @return The bytes for password.
   */
  com.google.protobuf.ByteString
      getPasswordBytes();
}