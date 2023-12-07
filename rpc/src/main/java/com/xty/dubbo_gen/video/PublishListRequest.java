// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: video.proto

package com.xty.dubbo_gen.video;

/**
 * Protobuf type {@code douyin.core.PublishListRequest}
 */
public final class PublishListRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:douyin.core.PublishListRequest)
    PublishListRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use PublishListRequest.newBuilder() to construct.
  private PublishListRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private PublishListRequest() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new PublishListRequest();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.xty.dubbo_gen.video.VideoServiceProto.internal_static_douyin_core_PublishListRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.xty.dubbo_gen.video.VideoServiceProto.internal_static_douyin_core_PublishListRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.xty.dubbo_gen.video.PublishListRequest.class, com.xty.dubbo_gen.video.PublishListRequest.Builder.class);
  }

  public static final int FROM_USER_ID_FIELD_NUMBER = 1;
  private long fromUserId_ = 0L;
  /**
   * <pre>
   * 访问用户id
   * </pre>
   *
   * <code>int64 from_user_id = 1;</code>
   * @return The fromUserId.
   */
  @java.lang.Override
  public long getFromUserId() {
    return fromUserId_;
  }

  public static final int TO_USER_ID_FIELD_NUMBER = 2;
  private long toUserId_ = 0L;
  /**
   * <pre>
   * 被访问用户id
   * </pre>
   *
   * <code>int64 to_user_id = 2;</code>
   * @return The toUserId.
   */
  @java.lang.Override
  public long getToUserId() {
    return toUserId_;
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
    if (fromUserId_ != 0L) {
      output.writeInt64(1, fromUserId_);
    }
    if (toUserId_ != 0L) {
      output.writeInt64(2, toUserId_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (fromUserId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, fromUserId_);
    }
    if (toUserId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, toUserId_);
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
    if (!(obj instanceof com.xty.dubbo_gen.video.PublishListRequest)) {
      return super.equals(obj);
    }
    com.xty.dubbo_gen.video.PublishListRequest other = (com.xty.dubbo_gen.video.PublishListRequest) obj;

    if (getFromUserId()
        != other.getFromUserId()) return false;
    if (getToUserId()
        != other.getToUserId()) return false;
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
    hash = (37 * hash) + FROM_USER_ID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getFromUserId());
    hash = (37 * hash) + TO_USER_ID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getToUserId());
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.xty.dubbo_gen.video.PublishListRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.xty.dubbo_gen.video.PublishListRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.xty.dubbo_gen.video.PublishListRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.xty.dubbo_gen.video.PublishListRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.xty.dubbo_gen.video.PublishListRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.xty.dubbo_gen.video.PublishListRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.xty.dubbo_gen.video.PublishListRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.xty.dubbo_gen.video.PublishListRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.xty.dubbo_gen.video.PublishListRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.xty.dubbo_gen.video.PublishListRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.xty.dubbo_gen.video.PublishListRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.xty.dubbo_gen.video.PublishListRequest parseFrom(
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
  public static Builder newBuilder(com.xty.dubbo_gen.video.PublishListRequest prototype) {
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
   * Protobuf type {@code douyin.core.PublishListRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:douyin.core.PublishListRequest)
      com.xty.dubbo_gen.video.PublishListRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.xty.dubbo_gen.video.VideoServiceProto.internal_static_douyin_core_PublishListRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.xty.dubbo_gen.video.VideoServiceProto.internal_static_douyin_core_PublishListRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.xty.dubbo_gen.video.PublishListRequest.class, com.xty.dubbo_gen.video.PublishListRequest.Builder.class);
    }

    // Construct using com.xty.dubbo_gen.video.PublishListRequest.newBuilder()
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
      fromUserId_ = 0L;
      toUserId_ = 0L;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.xty.dubbo_gen.video.VideoServiceProto.internal_static_douyin_core_PublishListRequest_descriptor;
    }

    @java.lang.Override
    public com.xty.dubbo_gen.video.PublishListRequest getDefaultInstanceForType() {
      return com.xty.dubbo_gen.video.PublishListRequest.getDefaultInstance();
    }

    @java.lang.Override
    public com.xty.dubbo_gen.video.PublishListRequest build() {
      com.xty.dubbo_gen.video.PublishListRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.xty.dubbo_gen.video.PublishListRequest buildPartial() {
      com.xty.dubbo_gen.video.PublishListRequest result = new com.xty.dubbo_gen.video.PublishListRequest(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(com.xty.dubbo_gen.video.PublishListRequest result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.fromUserId_ = fromUserId_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.toUserId_ = toUserId_;
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
      if (other instanceof com.xty.dubbo_gen.video.PublishListRequest) {
        return mergeFrom((com.xty.dubbo_gen.video.PublishListRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.xty.dubbo_gen.video.PublishListRequest other) {
      if (other == com.xty.dubbo_gen.video.PublishListRequest.getDefaultInstance()) return this;
      if (other.getFromUserId() != 0L) {
        setFromUserId(other.getFromUserId());
      }
      if (other.getToUserId() != 0L) {
        setToUserId(other.getToUserId());
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
              fromUserId_ = input.readInt64();
              bitField0_ |= 0x00000001;
              break;
            } // case 8
            case 16: {
              toUserId_ = input.readInt64();
              bitField0_ |= 0x00000002;
              break;
            } // case 16
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

    private long fromUserId_ ;
    /**
     * <pre>
     * 访问用户id
     * </pre>
     *
     * <code>int64 from_user_id = 1;</code>
     * @return The fromUserId.
     */
    @java.lang.Override
    public long getFromUserId() {
      return fromUserId_;
    }
    /**
     * <pre>
     * 访问用户id
     * </pre>
     *
     * <code>int64 from_user_id = 1;</code>
     * @param value The fromUserId to set.
     * @return This builder for chaining.
     */
    public Builder setFromUserId(long value) {

      fromUserId_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 访问用户id
     * </pre>
     *
     * <code>int64 from_user_id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearFromUserId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      fromUserId_ = 0L;
      onChanged();
      return this;
    }

    private long toUserId_ ;
    /**
     * <pre>
     * 被访问用户id
     * </pre>
     *
     * <code>int64 to_user_id = 2;</code>
     * @return The toUserId.
     */
    @java.lang.Override
    public long getToUserId() {
      return toUserId_;
    }
    /**
     * <pre>
     * 被访问用户id
     * </pre>
     *
     * <code>int64 to_user_id = 2;</code>
     * @param value The toUserId to set.
     * @return This builder for chaining.
     */
    public Builder setToUserId(long value) {

      toUserId_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 被访问用户id
     * </pre>
     *
     * <code>int64 to_user_id = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearToUserId() {
      bitField0_ = (bitField0_ & ~0x00000002);
      toUserId_ = 0L;
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


    // @@protoc_insertion_point(builder_scope:douyin.core.PublishListRequest)
  }

  // @@protoc_insertion_point(class_scope:douyin.core.PublishListRequest)
  private static final com.xty.dubbo_gen.video.PublishListRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.xty.dubbo_gen.video.PublishListRequest();
  }

  public static com.xty.dubbo_gen.video.PublishListRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<PublishListRequest>
      PARSER = new com.google.protobuf.AbstractParser<PublishListRequest>() {
    @java.lang.Override
    public PublishListRequest parsePartialFrom(
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

  public static com.google.protobuf.Parser<PublishListRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<PublishListRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.xty.dubbo_gen.video.PublishListRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

