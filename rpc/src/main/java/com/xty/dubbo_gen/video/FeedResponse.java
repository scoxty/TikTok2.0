// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: video.proto

package com.xty.dubbo_gen.video;

/**
 * Protobuf type {@code douyin.core.FeedResponse}
 */
public final class FeedResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:douyin.core.FeedResponse)
    FeedResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use FeedResponse.newBuilder() to construct.
  private FeedResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private FeedResponse() {
    statusMsg_ = "";
    videoList_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new FeedResponse();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.xty.dubbo_gen.video.VideoServiceProto.internal_static_douyin_core_FeedResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.xty.dubbo_gen.video.VideoServiceProto.internal_static_douyin_core_FeedResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.xty.dubbo_gen.video.FeedResponse.class, com.xty.dubbo_gen.video.FeedResponse.Builder.class);
  }

  public static final int STATUS_CODE_FIELD_NUMBER = 1;
  private int statusCode_ = 0;
  /**
   * <pre>
   * 状态码，0-成功，其他值-失败
   * </pre>
   *
   * <code>int32 status_code = 1;</code>
   * @return The statusCode.
   */
  @java.lang.Override
  public int getStatusCode() {
    return statusCode_;
  }

  public static final int STATUS_MSG_FIELD_NUMBER = 2;
  @SuppressWarnings("serial")
  private volatile java.lang.Object statusMsg_ = "";
  /**
   * <pre>
   * 返回状态描述
   * </pre>
   *
   * <code>string status_msg = 2;</code>
   * @return The statusMsg.
   */
  @java.lang.Override
  public java.lang.String getStatusMsg() {
    java.lang.Object ref = statusMsg_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      statusMsg_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * 返回状态描述
   * </pre>
   *
   * <code>string status_msg = 2;</code>
   * @return The bytes for statusMsg.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getStatusMsgBytes() {
    java.lang.Object ref = statusMsg_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      statusMsg_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int VIDEO_LIST_FIELD_NUMBER = 3;
  @SuppressWarnings("serial")
  private java.util.List<com.xty.dubbo_gen.video.Video> videoList_;
  /**
   * <pre>
   * 视频列表
   * </pre>
   *
   * <code>repeated .douyin.core.Video video_list = 3;</code>
   */
  @java.lang.Override
  public java.util.List<com.xty.dubbo_gen.video.Video> getVideoListList() {
    return videoList_;
  }
  /**
   * <pre>
   * 视频列表
   * </pre>
   *
   * <code>repeated .douyin.core.Video video_list = 3;</code>
   */
  @java.lang.Override
  public java.util.List<? extends com.xty.dubbo_gen.video.VideoOrBuilder> 
      getVideoListOrBuilderList() {
    return videoList_;
  }
  /**
   * <pre>
   * 视频列表
   * </pre>
   *
   * <code>repeated .douyin.core.Video video_list = 3;</code>
   */
  @java.lang.Override
  public int getVideoListCount() {
    return videoList_.size();
  }
  /**
   * <pre>
   * 视频列表
   * </pre>
   *
   * <code>repeated .douyin.core.Video video_list = 3;</code>
   */
  @java.lang.Override
  public com.xty.dubbo_gen.video.Video getVideoList(int index) {
    return videoList_.get(index);
  }
  /**
   * <pre>
   * 视频列表
   * </pre>
   *
   * <code>repeated .douyin.core.Video video_list = 3;</code>
   */
  @java.lang.Override
  public com.xty.dubbo_gen.video.VideoOrBuilder getVideoListOrBuilder(
      int index) {
    return videoList_.get(index);
  }

  public static final int NEXT_TIME_FIELD_NUMBER = 4;
  private long nextTime_ = 0L;
  /**
   * <pre>
   * 本次返回的视频中，发布最早的时间，作为下次请求时的latest_time
   * </pre>
   *
   * <code>int64 next_time = 4;</code>
   * @return The nextTime.
   */
  @java.lang.Override
  public long getNextTime() {
    return nextTime_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (statusCode_ != 0) {
      output.writeInt32(1, statusCode_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(statusMsg_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, statusMsg_);
    }
    for (int i = 0; i < videoList_.size(); i++) {
      output.writeMessage(3, videoList_.get(i));
    }
    if (nextTime_ != 0L) {
      output.writeInt64(4, nextTime_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (statusCode_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, statusCode_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(statusMsg_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, statusMsg_);
    }
    for (int i = 0; i < videoList_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, videoList_.get(i));
    }
    if (nextTime_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(4, nextTime_);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.xty.dubbo_gen.video.FeedResponse)) {
      return super.equals(obj);
    }
    com.xty.dubbo_gen.video.FeedResponse other = (com.xty.dubbo_gen.video.FeedResponse) obj;

    if (getStatusCode()
        != other.getStatusCode()) return false;
    if (!getStatusMsg()
        .equals(other.getStatusMsg())) return false;
    if (!getVideoListList()
        .equals(other.getVideoListList())) return false;
    if (getNextTime()
        != other.getNextTime()) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + STATUS_CODE_FIELD_NUMBER;
    hash = (53 * hash) + getStatusCode();
    hash = (37 * hash) + STATUS_MSG_FIELD_NUMBER;
    hash = (53 * hash) + getStatusMsg().hashCode();
    if (getVideoListCount() > 0) {
      hash = (37 * hash) + VIDEO_LIST_FIELD_NUMBER;
      hash = (53 * hash) + getVideoListList().hashCode();
    }
    hash = (37 * hash) + NEXT_TIME_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getNextTime());
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.xty.dubbo_gen.video.FeedResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.xty.dubbo_gen.video.FeedResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.xty.dubbo_gen.video.FeedResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.xty.dubbo_gen.video.FeedResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.xty.dubbo_gen.video.FeedResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.xty.dubbo_gen.video.FeedResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.xty.dubbo_gen.video.FeedResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.xty.dubbo_gen.video.FeedResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.xty.dubbo_gen.video.FeedResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.xty.dubbo_gen.video.FeedResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.xty.dubbo_gen.video.FeedResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.xty.dubbo_gen.video.FeedResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.xty.dubbo_gen.video.FeedResponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code douyin.core.FeedResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:douyin.core.FeedResponse)
      com.xty.dubbo_gen.video.FeedResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.xty.dubbo_gen.video.VideoServiceProto.internal_static_douyin_core_FeedResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.xty.dubbo_gen.video.VideoServiceProto.internal_static_douyin_core_FeedResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.xty.dubbo_gen.video.FeedResponse.class, com.xty.dubbo_gen.video.FeedResponse.Builder.class);
    }

    // Construct using com.xty.dubbo_gen.video.FeedResponse.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      statusCode_ = 0;
      statusMsg_ = "";
      if (videoListBuilder_ == null) {
        videoList_ = java.util.Collections.emptyList();
      } else {
        videoList_ = null;
        videoListBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000004);
      nextTime_ = 0L;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.xty.dubbo_gen.video.VideoServiceProto.internal_static_douyin_core_FeedResponse_descriptor;
    }

    @java.lang.Override
    public com.xty.dubbo_gen.video.FeedResponse getDefaultInstanceForType() {
      return com.xty.dubbo_gen.video.FeedResponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.xty.dubbo_gen.video.FeedResponse build() {
      com.xty.dubbo_gen.video.FeedResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.xty.dubbo_gen.video.FeedResponse buildPartial() {
      com.xty.dubbo_gen.video.FeedResponse result = new com.xty.dubbo_gen.video.FeedResponse(this);
      buildPartialRepeatedFields(result);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartialRepeatedFields(com.xty.dubbo_gen.video.FeedResponse result) {
      if (videoListBuilder_ == null) {
        if (((bitField0_ & 0x00000004) != 0)) {
          videoList_ = java.util.Collections.unmodifiableList(videoList_);
          bitField0_ = (bitField0_ & ~0x00000004);
        }
        result.videoList_ = videoList_;
      } else {
        result.videoList_ = videoListBuilder_.build();
      }
    }

    private void buildPartial0(com.xty.dubbo_gen.video.FeedResponse result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.statusCode_ = statusCode_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.statusMsg_ = statusMsg_;
      }
      if (((from_bitField0_ & 0x00000008) != 0)) {
        result.nextTime_ = nextTime_;
      }
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.xty.dubbo_gen.video.FeedResponse) {
        return mergeFrom((com.xty.dubbo_gen.video.FeedResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.xty.dubbo_gen.video.FeedResponse other) {
      if (other == com.xty.dubbo_gen.video.FeedResponse.getDefaultInstance()) return this;
      if (other.getStatusCode() != 0) {
        setStatusCode(other.getStatusCode());
      }
      if (!other.getStatusMsg().isEmpty()) {
        statusMsg_ = other.statusMsg_;
        bitField0_ |= 0x00000002;
        onChanged();
      }
      if (videoListBuilder_ == null) {
        if (!other.videoList_.isEmpty()) {
          if (videoList_.isEmpty()) {
            videoList_ = other.videoList_;
            bitField0_ = (bitField0_ & ~0x00000004);
          } else {
            ensureVideoListIsMutable();
            videoList_.addAll(other.videoList_);
          }
          onChanged();
        }
      } else {
        if (!other.videoList_.isEmpty()) {
          if (videoListBuilder_.isEmpty()) {
            videoListBuilder_.dispose();
            videoListBuilder_ = null;
            videoList_ = other.videoList_;
            bitField0_ = (bitField0_ & ~0x00000004);
            videoListBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getVideoListFieldBuilder() : null;
          } else {
            videoListBuilder_.addAllMessages(other.videoList_);
          }
        }
      }
      if (other.getNextTime() != 0L) {
        setNextTime(other.getNextTime());
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {
              statusCode_ = input.readInt32();
              bitField0_ |= 0x00000001;
              break;
            } // case 8
            case 18: {
              statusMsg_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000002;
              break;
            } // case 18
            case 26: {
              com.xty.dubbo_gen.video.Video m =
                  input.readMessage(
                      com.xty.dubbo_gen.video.Video.parser(),
                      extensionRegistry);
              if (videoListBuilder_ == null) {
                ensureVideoListIsMutable();
                videoList_.add(m);
              } else {
                videoListBuilder_.addMessage(m);
              }
              break;
            } // case 26
            case 32: {
              nextTime_ = input.readInt64();
              bitField0_ |= 0x00000008;
              break;
            } // case 32
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private int statusCode_ ;
    /**
     * <pre>
     * 状态码，0-成功，其他值-失败
     * </pre>
     *
     * <code>int32 status_code = 1;</code>
     * @return The statusCode.
     */
    @java.lang.Override
    public int getStatusCode() {
      return statusCode_;
    }
    /**
     * <pre>
     * 状态码，0-成功，其他值-失败
     * </pre>
     *
     * <code>int32 status_code = 1;</code>
     * @param value The statusCode to set.
     * @return This builder for chaining.
     */
    public Builder setStatusCode(int value) {

      statusCode_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 状态码，0-成功，其他值-失败
     * </pre>
     *
     * <code>int32 status_code = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearStatusCode() {
      bitField0_ = (bitField0_ & ~0x00000001);
      statusCode_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object statusMsg_ = "";
    /**
     * <pre>
     * 返回状态描述
     * </pre>
     *
     * <code>string status_msg = 2;</code>
     * @return The statusMsg.
     */
    public java.lang.String getStatusMsg() {
      java.lang.Object ref = statusMsg_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        statusMsg_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * 返回状态描述
     * </pre>
     *
     * <code>string status_msg = 2;</code>
     * @return The bytes for statusMsg.
     */
    public com.google.protobuf.ByteString
        getStatusMsgBytes() {
      java.lang.Object ref = statusMsg_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        statusMsg_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * 返回状态描述
     * </pre>
     *
     * <code>string status_msg = 2;</code>
     * @param value The statusMsg to set.
     * @return This builder for chaining.
     */
    public Builder setStatusMsg(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      statusMsg_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 返回状态描述
     * </pre>
     *
     * <code>string status_msg = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearStatusMsg() {
      statusMsg_ = getDefaultInstance().getStatusMsg();
      bitField0_ = (bitField0_ & ~0x00000002);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 返回状态描述
     * </pre>
     *
     * <code>string status_msg = 2;</code>
     * @param value The bytes for statusMsg to set.
     * @return This builder for chaining.
     */
    public Builder setStatusMsgBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      statusMsg_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }

    private java.util.List<com.xty.dubbo_gen.video.Video> videoList_ =
      java.util.Collections.emptyList();
    private void ensureVideoListIsMutable() {
      if (!((bitField0_ & 0x00000004) != 0)) {
        videoList_ = new java.util.ArrayList<com.xty.dubbo_gen.video.Video>(videoList_);
        bitField0_ |= 0x00000004;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.xty.dubbo_gen.video.Video, com.xty.dubbo_gen.video.Video.Builder, com.xty.dubbo_gen.video.VideoOrBuilder> videoListBuilder_;

    /**
     * <pre>
     * 视频列表
     * </pre>
     *
     * <code>repeated .douyin.core.Video video_list = 3;</code>
     */
    public java.util.List<com.xty.dubbo_gen.video.Video> getVideoListList() {
      if (videoListBuilder_ == null) {
        return java.util.Collections.unmodifiableList(videoList_);
      } else {
        return videoListBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     * 视频列表
     * </pre>
     *
     * <code>repeated .douyin.core.Video video_list = 3;</code>
     */
    public int getVideoListCount() {
      if (videoListBuilder_ == null) {
        return videoList_.size();
      } else {
        return videoListBuilder_.getCount();
      }
    }
    /**
     * <pre>
     * 视频列表
     * </pre>
     *
     * <code>repeated .douyin.core.Video video_list = 3;</code>
     */
    public com.xty.dubbo_gen.video.Video getVideoList(int index) {
      if (videoListBuilder_ == null) {
        return videoList_.get(index);
      } else {
        return videoListBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     * 视频列表
     * </pre>
     *
     * <code>repeated .douyin.core.Video video_list = 3;</code>
     */
    public Builder setVideoList(
        int index, com.xty.dubbo_gen.video.Video value) {
      if (videoListBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureVideoListIsMutable();
        videoList_.set(index, value);
        onChanged();
      } else {
        videoListBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * 视频列表
     * </pre>
     *
     * <code>repeated .douyin.core.Video video_list = 3;</code>
     */
    public Builder setVideoList(
        int index, com.xty.dubbo_gen.video.Video.Builder builderForValue) {
      if (videoListBuilder_ == null) {
        ensureVideoListIsMutable();
        videoList_.set(index, builderForValue.build());
        onChanged();
      } else {
        videoListBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 视频列表
     * </pre>
     *
     * <code>repeated .douyin.core.Video video_list = 3;</code>
     */
    public Builder addVideoList(com.xty.dubbo_gen.video.Video value) {
      if (videoListBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureVideoListIsMutable();
        videoList_.add(value);
        onChanged();
      } else {
        videoListBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     * 视频列表
     * </pre>
     *
     * <code>repeated .douyin.core.Video video_list = 3;</code>
     */
    public Builder addVideoList(
        int index, com.xty.dubbo_gen.video.Video value) {
      if (videoListBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureVideoListIsMutable();
        videoList_.add(index, value);
        onChanged();
      } else {
        videoListBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * 视频列表
     * </pre>
     *
     * <code>repeated .douyin.core.Video video_list = 3;</code>
     */
    public Builder addVideoList(
        com.xty.dubbo_gen.video.Video.Builder builderForValue) {
      if (videoListBuilder_ == null) {
        ensureVideoListIsMutable();
        videoList_.add(builderForValue.build());
        onChanged();
      } else {
        videoListBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 视频列表
     * </pre>
     *
     * <code>repeated .douyin.core.Video video_list = 3;</code>
     */
    public Builder addVideoList(
        int index, com.xty.dubbo_gen.video.Video.Builder builderForValue) {
      if (videoListBuilder_ == null) {
        ensureVideoListIsMutable();
        videoList_.add(index, builderForValue.build());
        onChanged();
      } else {
        videoListBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 视频列表
     * </pre>
     *
     * <code>repeated .douyin.core.Video video_list = 3;</code>
     */
    public Builder addAllVideoList(
        java.lang.Iterable<? extends com.xty.dubbo_gen.video.Video> values) {
      if (videoListBuilder_ == null) {
        ensureVideoListIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, videoList_);
        onChanged();
      } else {
        videoListBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     * 视频列表
     * </pre>
     *
     * <code>repeated .douyin.core.Video video_list = 3;</code>
     */
    public Builder clearVideoList() {
      if (videoListBuilder_ == null) {
        videoList_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
        onChanged();
      } else {
        videoListBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     * 视频列表
     * </pre>
     *
     * <code>repeated .douyin.core.Video video_list = 3;</code>
     */
    public Builder removeVideoList(int index) {
      if (videoListBuilder_ == null) {
        ensureVideoListIsMutable();
        videoList_.remove(index);
        onChanged();
      } else {
        videoListBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     * 视频列表
     * </pre>
     *
     * <code>repeated .douyin.core.Video video_list = 3;</code>
     */
    public com.xty.dubbo_gen.video.Video.Builder getVideoListBuilder(
        int index) {
      return getVideoListFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     * 视频列表
     * </pre>
     *
     * <code>repeated .douyin.core.Video video_list = 3;</code>
     */
    public com.xty.dubbo_gen.video.VideoOrBuilder getVideoListOrBuilder(
        int index) {
      if (videoListBuilder_ == null) {
        return videoList_.get(index);  } else {
        return videoListBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     * 视频列表
     * </pre>
     *
     * <code>repeated .douyin.core.Video video_list = 3;</code>
     */
    public java.util.List<? extends com.xty.dubbo_gen.video.VideoOrBuilder> 
         getVideoListOrBuilderList() {
      if (videoListBuilder_ != null) {
        return videoListBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(videoList_);
      }
    }
    /**
     * <pre>
     * 视频列表
     * </pre>
     *
     * <code>repeated .douyin.core.Video video_list = 3;</code>
     */
    public com.xty.dubbo_gen.video.Video.Builder addVideoListBuilder() {
      return getVideoListFieldBuilder().addBuilder(
          com.xty.dubbo_gen.video.Video.getDefaultInstance());
    }
    /**
     * <pre>
     * 视频列表
     * </pre>
     *
     * <code>repeated .douyin.core.Video video_list = 3;</code>
     */
    public com.xty.dubbo_gen.video.Video.Builder addVideoListBuilder(
        int index) {
      return getVideoListFieldBuilder().addBuilder(
          index, com.xty.dubbo_gen.video.Video.getDefaultInstance());
    }
    /**
     * <pre>
     * 视频列表
     * </pre>
     *
     * <code>repeated .douyin.core.Video video_list = 3;</code>
     */
    public java.util.List<com.xty.dubbo_gen.video.Video.Builder> 
         getVideoListBuilderList() {
      return getVideoListFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.xty.dubbo_gen.video.Video, com.xty.dubbo_gen.video.Video.Builder, com.xty.dubbo_gen.video.VideoOrBuilder> 
        getVideoListFieldBuilder() {
      if (videoListBuilder_ == null) {
        videoListBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.xty.dubbo_gen.video.Video, com.xty.dubbo_gen.video.Video.Builder, com.xty.dubbo_gen.video.VideoOrBuilder>(
                videoList_,
                ((bitField0_ & 0x00000004) != 0),
                getParentForChildren(),
                isClean());
        videoList_ = null;
      }
      return videoListBuilder_;
    }

    private long nextTime_ ;
    /**
     * <pre>
     * 本次返回的视频中，发布最早的时间，作为下次请求时的latest_time
     * </pre>
     *
     * <code>int64 next_time = 4;</code>
     * @return The nextTime.
     */
    @java.lang.Override
    public long getNextTime() {
      return nextTime_;
    }
    /**
     * <pre>
     * 本次返回的视频中，发布最早的时间，作为下次请求时的latest_time
     * </pre>
     *
     * <code>int64 next_time = 4;</code>
     * @param value The nextTime to set.
     * @return This builder for chaining.
     */
    public Builder setNextTime(long value) {

      nextTime_ = value;
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 本次返回的视频中，发布最早的时间，作为下次请求时的latest_time
     * </pre>
     *
     * <code>int64 next_time = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearNextTime() {
      bitField0_ = (bitField0_ & ~0x00000008);
      nextTime_ = 0L;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:douyin.core.FeedResponse)
  }

  // @@protoc_insertion_point(class_scope:douyin.core.FeedResponse)
  private static final com.xty.dubbo_gen.video.FeedResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.xty.dubbo_gen.video.FeedResponse();
  }

  public static com.xty.dubbo_gen.video.FeedResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<FeedResponse>
      PARSER = new com.google.protobuf.AbstractParser<FeedResponse>() {
    @java.lang.Override
    public FeedResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<FeedResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<FeedResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.xty.dubbo_gen.video.FeedResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

