// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: video.proto

package com.xty.dubbo_gen.video;

public interface PublishActionResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:douyin.core.PublishActionResponse)
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
}