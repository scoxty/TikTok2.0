// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: video.proto

package com.xty.dubbo_gen.video;

/**
 * Protobuf type {@code douyin.core.PublishActionResponse}
 */
public final class PublishActionResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:douyin.core.PublishActionResponse)
    PublishActionResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use PublishActionResponse.newBuilder() to construct.
  private PublishActionResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private PublishActionResponse() {
    statusMsg_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new PublishActionResponse();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.xty.dubbo_gen.video.VideoServiceProto.internal_static_douyin_core_PublishActionResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.xty.dubbo_gen.video.VideoServiceProto.internal_static_douyin_core_PublishActionResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.xty.dubbo_gen.video.PublishActionResponse.class, com.xty.dubbo_gen.video.PublishActionResponse.Builder.class);
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
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.xty.dubbo_gen.video.PublishActionResponse)) {
      return super.equals(obj);
    }
    com.xty.dubbo_gen.video.PublishActionResponse other = (com.xty.dubbo_gen.video.PublishActionResponse) obj;

    if (getStatusCode()
        != other.getStatusCode()) return false;
    if (!getStatusMsg()
        .equals(other.getStatusMsg())) return false;
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
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.xty.dubbo_gen.video.PublishActionResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.xty.dubbo_gen.video.PublishActionResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.xty.dubbo_gen.video.PublishActionResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.xty.dubbo_gen.video.PublishActionResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.xty.dubbo_gen.video.PublishActionResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.xty.dubbo_gen.video.PublishActionResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.xty.dubbo_gen.video.PublishActionResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.xty.dubbo_gen.video.PublishActionResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.xty.dubbo_gen.video.PublishActionResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.xty.dubbo_gen.video.PublishActionResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.xty.dubbo_gen.video.PublishActionResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.xty.dubbo_gen.video.PublishActionResponse parseFrom(
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
  public static Builder newBuilder(com.xty.dubbo_gen.video.PublishActionResponse prototype) {
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
   * Protobuf type {@code douyin.core.PublishActionResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:douyin.core.PublishActionResponse)
      com.xty.dubbo_gen.video.PublishActionResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.xty.dubbo_gen.video.VideoServiceProto.internal_static_douyin_core_PublishActionResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.xty.dubbo_gen.video.VideoServiceProto.internal_static_douyin_core_PublishActionResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.xty.dubbo_gen.video.PublishActionResponse.class, com.xty.dubbo_gen.video.PublishActionResponse.Builder.class);
    }

    // Construct using com.xty.dubbo_gen.video.PublishActionResponse.newBuilder()
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
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.xty.dubbo_gen.video.VideoServiceProto.internal_static_douyin_core_PublishActionResponse_descriptor;
    }

    @java.lang.Override
    public com.xty.dubbo_gen.video.PublishActionResponse getDefaultInstanceForType() {
      return com.xty.dubbo_gen.video.PublishActionResponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.xty.dubbo_gen.video.PublishActionResponse build() {
      com.xty.dubbo_gen.video.PublishActionResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.xty.dubbo_gen.video.PublishActionResponse buildPartial() {
      com.xty.dubbo_gen.video.PublishActionResponse result = new com.xty.dubbo_gen.video.PublishActionResponse(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(com.xty.dubbo_gen.video.PublishActionResponse result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.statusCode_ = statusCode_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.statusMsg_ = statusMsg_;
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
      if (other instanceof com.xty.dubbo_gen.video.PublishActionResponse) {
        return mergeFrom((com.xty.dubbo_gen.video.PublishActionResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.xty.dubbo_gen.video.PublishActionResponse other) {
      if (other == com.xty.dubbo_gen.video.PublishActionResponse.getDefaultInstance()) return this;
      if (other.getStatusCode() != 0) {
        setStatusCode(other.getStatusCode());
      }
      if (!other.getStatusMsg().isEmpty()) {
        statusMsg_ = other.statusMsg_;
        bitField0_ |= 0x00000002;
        onChanged();
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


    // @@protoc_insertion_point(builder_scope:douyin.core.PublishActionResponse)
  }

  // @@protoc_insertion_point(class_scope:douyin.core.PublishActionResponse)
  private static final com.xty.dubbo_gen.video.PublishActionResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.xty.dubbo_gen.video.PublishActionResponse();
  }

  public static com.xty.dubbo_gen.video.PublishActionResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<PublishActionResponse>
      PARSER = new com.google.protobuf.AbstractParser<PublishActionResponse>() {
    @java.lang.Override
    public PublishActionResponse parsePartialFrom(
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

  public static com.google.protobuf.Parser<PublishActionResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<PublishActionResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.xty.dubbo_gen.video.PublishActionResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

