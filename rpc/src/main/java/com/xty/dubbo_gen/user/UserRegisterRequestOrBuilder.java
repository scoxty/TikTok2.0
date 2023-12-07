// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: user.proto

package com.xty.dubbo_gen.user;

public interface UserRegisterRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:douyin.core.UserRegisterRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 注册用户名，最长32个字符
   * </pre>
   *
   * <code>string username = 1;</code>
   * @return The username.
   */
  java.lang.String getUsername();
  /**
   * <pre>
   * 注册用户名，最长32个字符
   * </pre>
   *
   * <code>string username = 1;</code>
   * @return The bytes for username.
   */
  com.google.protobuf.ByteString
      getUsernameBytes();

  /**
   * <pre>
   * 密码，最长32个字符
   * </pre>
   *
   * <code>string password = 2;</code>
   * @return The password.
   */
  java.lang.String getPassword();
  /**
   * <pre>
   * 密码，最长32个字符
   * </pre>
   *
   * <code>string password = 2;</code>
   * @return The bytes for password.
   */
  com.google.protobuf.ByteString
      getPasswordBytes();
}
