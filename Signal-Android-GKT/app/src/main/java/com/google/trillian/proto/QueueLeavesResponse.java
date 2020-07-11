// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: trillian_log_api.proto

package com.google.trillian.proto;

/**
 * Protobuf type {@code trillian.QueueLeavesResponse}
 */
public  final class QueueLeavesResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:trillian.QueueLeavesResponse)
    QueueLeavesResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use QueueLeavesResponse.newBuilder() to construct.
  private QueueLeavesResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private QueueLeavesResponse() {
    queuedLeaves_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private QueueLeavesResponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 18: {
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
              queuedLeaves_ = new java.util.ArrayList<com.google.trillian.proto.QueuedLogLeaf>();
              mutable_bitField0_ |= 0x00000001;
            }
            queuedLeaves_.add(
                input.readMessage(com.google.trillian.proto.QueuedLogLeaf.parser(), extensionRegistry));
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
        queuedLeaves_ = java.util.Collections.unmodifiableList(queuedLeaves_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.google.trillian.proto.TrillianLogApiProto.internal_static_trillian_QueueLeavesResponse_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.google.trillian.proto.TrillianLogApiProto.internal_static_trillian_QueueLeavesResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.google.trillian.proto.QueueLeavesResponse.class, com.google.trillian.proto.QueueLeavesResponse.Builder.class);
  }

  public static final int QUEUED_LEAVES_FIELD_NUMBER = 2;
  private java.util.List<com.google.trillian.proto.QueuedLogLeaf> queuedLeaves_;
  /**
   * <pre>
   * Same number and order as in the corresponding request.
   * </pre>
   *
   * <code>repeated .trillian.QueuedLogLeaf queued_leaves = 2;</code>
   */
  public java.util.List<com.google.trillian.proto.QueuedLogLeaf> getQueuedLeavesList() {
    return queuedLeaves_;
  }
  /**
   * <pre>
   * Same number and order as in the corresponding request.
   * </pre>
   *
   * <code>repeated .trillian.QueuedLogLeaf queued_leaves = 2;</code>
   */
  public java.util.List<? extends com.google.trillian.proto.QueuedLogLeafOrBuilder> 
      getQueuedLeavesOrBuilderList() {
    return queuedLeaves_;
  }
  /**
   * <pre>
   * Same number and order as in the corresponding request.
   * </pre>
   *
   * <code>repeated .trillian.QueuedLogLeaf queued_leaves = 2;</code>
   */
  public int getQueuedLeavesCount() {
    return queuedLeaves_.size();
  }
  /**
   * <pre>
   * Same number and order as in the corresponding request.
   * </pre>
   *
   * <code>repeated .trillian.QueuedLogLeaf queued_leaves = 2;</code>
   */
  public com.google.trillian.proto.QueuedLogLeaf getQueuedLeaves(int index) {
    return queuedLeaves_.get(index);
  }
  /**
   * <pre>
   * Same number and order as in the corresponding request.
   * </pre>
   *
   * <code>repeated .trillian.QueuedLogLeaf queued_leaves = 2;</code>
   */
  public com.google.trillian.proto.QueuedLogLeafOrBuilder getQueuedLeavesOrBuilder(
      int index) {
    return queuedLeaves_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < queuedLeaves_.size(); i++) {
      output.writeMessage(2, queuedLeaves_.get(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < queuedLeaves_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, queuedLeaves_.get(i));
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.google.trillian.proto.QueueLeavesResponse)) {
      return super.equals(obj);
    }
    com.google.trillian.proto.QueueLeavesResponse other = (com.google.trillian.proto.QueueLeavesResponse) obj;

    boolean result = true;
    result = result && getQueuedLeavesList()
        .equals(other.getQueuedLeavesList());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getQueuedLeavesCount() > 0) {
      hash = (37 * hash) + QUEUED_LEAVES_FIELD_NUMBER;
      hash = (53 * hash) + getQueuedLeavesList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.google.trillian.proto.QueueLeavesResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.trillian.proto.QueueLeavesResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.trillian.proto.QueueLeavesResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.trillian.proto.QueueLeavesResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.trillian.proto.QueueLeavesResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.trillian.proto.QueueLeavesResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.trillian.proto.QueueLeavesResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.google.trillian.proto.QueueLeavesResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.google.trillian.proto.QueueLeavesResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.google.trillian.proto.QueueLeavesResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.google.trillian.proto.QueueLeavesResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.google.trillian.proto.QueueLeavesResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.google.trillian.proto.QueueLeavesResponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
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
   * Protobuf type {@code trillian.QueueLeavesResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:trillian.QueueLeavesResponse)
      com.google.trillian.proto.QueueLeavesResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.google.trillian.proto.TrillianLogApiProto.internal_static_trillian_QueueLeavesResponse_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.google.trillian.proto.TrillianLogApiProto.internal_static_trillian_QueueLeavesResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.google.trillian.proto.QueueLeavesResponse.class, com.google.trillian.proto.QueueLeavesResponse.Builder.class);
    }

    // Construct using com.google.trillian.proto.QueueLeavesResponse.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
        getQueuedLeavesFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (queuedLeavesBuilder_ == null) {
        queuedLeaves_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        queuedLeavesBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.google.trillian.proto.TrillianLogApiProto.internal_static_trillian_QueueLeavesResponse_descriptor;
    }

    public com.google.trillian.proto.QueueLeavesResponse getDefaultInstanceForType() {
      return com.google.trillian.proto.QueueLeavesResponse.getDefaultInstance();
    }

    public com.google.trillian.proto.QueueLeavesResponse build() {
      com.google.trillian.proto.QueueLeavesResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.google.trillian.proto.QueueLeavesResponse buildPartial() {
      com.google.trillian.proto.QueueLeavesResponse result = new com.google.trillian.proto.QueueLeavesResponse(this);
      int from_bitField0_ = bitField0_;
      if (queuedLeavesBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          queuedLeaves_ = java.util.Collections.unmodifiableList(queuedLeaves_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.queuedLeaves_ = queuedLeaves_;
      } else {
        result.queuedLeaves_ = queuedLeavesBuilder_.build();
      }
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.google.trillian.proto.QueueLeavesResponse) {
        return mergeFrom((com.google.trillian.proto.QueueLeavesResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.google.trillian.proto.QueueLeavesResponse other) {
      if (other == com.google.trillian.proto.QueueLeavesResponse.getDefaultInstance()) return this;
      if (queuedLeavesBuilder_ == null) {
        if (!other.queuedLeaves_.isEmpty()) {
          if (queuedLeaves_.isEmpty()) {
            queuedLeaves_ = other.queuedLeaves_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureQueuedLeavesIsMutable();
            queuedLeaves_.addAll(other.queuedLeaves_);
          }
          onChanged();
        }
      } else {
        if (!other.queuedLeaves_.isEmpty()) {
          if (queuedLeavesBuilder_.isEmpty()) {
            queuedLeavesBuilder_.dispose();
            queuedLeavesBuilder_ = null;
            queuedLeaves_ = other.queuedLeaves_;
            bitField0_ = (bitField0_ & ~0x00000001);
            queuedLeavesBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getQueuedLeavesFieldBuilder() : null;
          } else {
            queuedLeavesBuilder_.addAllMessages(other.queuedLeaves_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.google.trillian.proto.QueueLeavesResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.google.trillian.proto.QueueLeavesResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<com.google.trillian.proto.QueuedLogLeaf> queuedLeaves_ =
      java.util.Collections.emptyList();
    private void ensureQueuedLeavesIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        queuedLeaves_ = new java.util.ArrayList<com.google.trillian.proto.QueuedLogLeaf>(queuedLeaves_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.google.trillian.proto.QueuedLogLeaf, com.google.trillian.proto.QueuedLogLeaf.Builder, com.google.trillian.proto.QueuedLogLeafOrBuilder> queuedLeavesBuilder_;

    /**
     * <pre>
     * Same number and order as in the corresponding request.
     * </pre>
     *
     * <code>repeated .trillian.QueuedLogLeaf queued_leaves = 2;</code>
     */
    public java.util.List<com.google.trillian.proto.QueuedLogLeaf> getQueuedLeavesList() {
      if (queuedLeavesBuilder_ == null) {
        return java.util.Collections.unmodifiableList(queuedLeaves_);
      } else {
        return queuedLeavesBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     * Same number and order as in the corresponding request.
     * </pre>
     *
     * <code>repeated .trillian.QueuedLogLeaf queued_leaves = 2;</code>
     */
    public int getQueuedLeavesCount() {
      if (queuedLeavesBuilder_ == null) {
        return queuedLeaves_.size();
      } else {
        return queuedLeavesBuilder_.getCount();
      }
    }
    /**
     * <pre>
     * Same number and order as in the corresponding request.
     * </pre>
     *
     * <code>repeated .trillian.QueuedLogLeaf queued_leaves = 2;</code>
     */
    public com.google.trillian.proto.QueuedLogLeaf getQueuedLeaves(int index) {
      if (queuedLeavesBuilder_ == null) {
        return queuedLeaves_.get(index);
      } else {
        return queuedLeavesBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     * Same number and order as in the corresponding request.
     * </pre>
     *
     * <code>repeated .trillian.QueuedLogLeaf queued_leaves = 2;</code>
     */
    public Builder setQueuedLeaves(
        int index, com.google.trillian.proto.QueuedLogLeaf value) {
      if (queuedLeavesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureQueuedLeavesIsMutable();
        queuedLeaves_.set(index, value);
        onChanged();
      } else {
        queuedLeavesBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * Same number and order as in the corresponding request.
     * </pre>
     *
     * <code>repeated .trillian.QueuedLogLeaf queued_leaves = 2;</code>
     */
    public Builder setQueuedLeaves(
        int index, com.google.trillian.proto.QueuedLogLeaf.Builder builderForValue) {
      if (queuedLeavesBuilder_ == null) {
        ensureQueuedLeavesIsMutable();
        queuedLeaves_.set(index, builderForValue.build());
        onChanged();
      } else {
        queuedLeavesBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * Same number and order as in the corresponding request.
     * </pre>
     *
     * <code>repeated .trillian.QueuedLogLeaf queued_leaves = 2;</code>
     */
    public Builder addQueuedLeaves(com.google.trillian.proto.QueuedLogLeaf value) {
      if (queuedLeavesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureQueuedLeavesIsMutable();
        queuedLeaves_.add(value);
        onChanged();
      } else {
        queuedLeavesBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     * Same number and order as in the corresponding request.
     * </pre>
     *
     * <code>repeated .trillian.QueuedLogLeaf queued_leaves = 2;</code>
     */
    public Builder addQueuedLeaves(
        int index, com.google.trillian.proto.QueuedLogLeaf value) {
      if (queuedLeavesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureQueuedLeavesIsMutable();
        queuedLeaves_.add(index, value);
        onChanged();
      } else {
        queuedLeavesBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * Same number and order as in the corresponding request.
     * </pre>
     *
     * <code>repeated .trillian.QueuedLogLeaf queued_leaves = 2;</code>
     */
    public Builder addQueuedLeaves(
        com.google.trillian.proto.QueuedLogLeaf.Builder builderForValue) {
      if (queuedLeavesBuilder_ == null) {
        ensureQueuedLeavesIsMutable();
        queuedLeaves_.add(builderForValue.build());
        onChanged();
      } else {
        queuedLeavesBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * Same number and order as in the corresponding request.
     * </pre>
     *
     * <code>repeated .trillian.QueuedLogLeaf queued_leaves = 2;</code>
     */
    public Builder addQueuedLeaves(
        int index, com.google.trillian.proto.QueuedLogLeaf.Builder builderForValue) {
      if (queuedLeavesBuilder_ == null) {
        ensureQueuedLeavesIsMutable();
        queuedLeaves_.add(index, builderForValue.build());
        onChanged();
      } else {
        queuedLeavesBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * Same number and order as in the corresponding request.
     * </pre>
     *
     * <code>repeated .trillian.QueuedLogLeaf queued_leaves = 2;</code>
     */
    public Builder addAllQueuedLeaves(
        java.lang.Iterable<? extends com.google.trillian.proto.QueuedLogLeaf> values) {
      if (queuedLeavesBuilder_ == null) {
        ensureQueuedLeavesIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, queuedLeaves_);
        onChanged();
      } else {
        queuedLeavesBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     * Same number and order as in the corresponding request.
     * </pre>
     *
     * <code>repeated .trillian.QueuedLogLeaf queued_leaves = 2;</code>
     */
    public Builder clearQueuedLeaves() {
      if (queuedLeavesBuilder_ == null) {
        queuedLeaves_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        queuedLeavesBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     * Same number and order as in the corresponding request.
     * </pre>
     *
     * <code>repeated .trillian.QueuedLogLeaf queued_leaves = 2;</code>
     */
    public Builder removeQueuedLeaves(int index) {
      if (queuedLeavesBuilder_ == null) {
        ensureQueuedLeavesIsMutable();
        queuedLeaves_.remove(index);
        onChanged();
      } else {
        queuedLeavesBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     * Same number and order as in the corresponding request.
     * </pre>
     *
     * <code>repeated .trillian.QueuedLogLeaf queued_leaves = 2;</code>
     */
    public com.google.trillian.proto.QueuedLogLeaf.Builder getQueuedLeavesBuilder(
        int index) {
      return getQueuedLeavesFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     * Same number and order as in the corresponding request.
     * </pre>
     *
     * <code>repeated .trillian.QueuedLogLeaf queued_leaves = 2;</code>
     */
    public com.google.trillian.proto.QueuedLogLeafOrBuilder getQueuedLeavesOrBuilder(
        int index) {
      if (queuedLeavesBuilder_ == null) {
        return queuedLeaves_.get(index);  } else {
        return queuedLeavesBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     * Same number and order as in the corresponding request.
     * </pre>
     *
     * <code>repeated .trillian.QueuedLogLeaf queued_leaves = 2;</code>
     */
    public java.util.List<? extends com.google.trillian.proto.QueuedLogLeafOrBuilder> 
         getQueuedLeavesOrBuilderList() {
      if (queuedLeavesBuilder_ != null) {
        return queuedLeavesBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(queuedLeaves_);
      }
    }
    /**
     * <pre>
     * Same number and order as in the corresponding request.
     * </pre>
     *
     * <code>repeated .trillian.QueuedLogLeaf queued_leaves = 2;</code>
     */
    public com.google.trillian.proto.QueuedLogLeaf.Builder addQueuedLeavesBuilder() {
      return getQueuedLeavesFieldBuilder().addBuilder(
          com.google.trillian.proto.QueuedLogLeaf.getDefaultInstance());
    }
    /**
     * <pre>
     * Same number and order as in the corresponding request.
     * </pre>
     *
     * <code>repeated .trillian.QueuedLogLeaf queued_leaves = 2;</code>
     */
    public com.google.trillian.proto.QueuedLogLeaf.Builder addQueuedLeavesBuilder(
        int index) {
      return getQueuedLeavesFieldBuilder().addBuilder(
          index, com.google.trillian.proto.QueuedLogLeaf.getDefaultInstance());
    }
    /**
     * <pre>
     * Same number and order as in the corresponding request.
     * </pre>
     *
     * <code>repeated .trillian.QueuedLogLeaf queued_leaves = 2;</code>
     */
    public java.util.List<com.google.trillian.proto.QueuedLogLeaf.Builder> 
         getQueuedLeavesBuilderList() {
      return getQueuedLeavesFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.google.trillian.proto.QueuedLogLeaf, com.google.trillian.proto.QueuedLogLeaf.Builder, com.google.trillian.proto.QueuedLogLeafOrBuilder> 
        getQueuedLeavesFieldBuilder() {
      if (queuedLeavesBuilder_ == null) {
        queuedLeavesBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.google.trillian.proto.QueuedLogLeaf, com.google.trillian.proto.QueuedLogLeaf.Builder, com.google.trillian.proto.QueuedLogLeafOrBuilder>(
                queuedLeaves_,
                ((bitField0_ & 0x00000001) == 0x00000001),
                getParentForChildren(),
                isClean());
        queuedLeaves_ = null;
      }
      return queuedLeavesBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:trillian.QueueLeavesResponse)
  }

  // @@protoc_insertion_point(class_scope:trillian.QueueLeavesResponse)
  private static final com.google.trillian.proto.QueueLeavesResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.google.trillian.proto.QueueLeavesResponse();
  }

  public static com.google.trillian.proto.QueueLeavesResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<QueueLeavesResponse>
      PARSER = new com.google.protobuf.AbstractParser<QueueLeavesResponse>() {
    public QueueLeavesResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new QueueLeavesResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<QueueLeavesResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<QueueLeavesResponse> getParserForType() {
    return PARSER;
  }

  public com.google.trillian.proto.QueueLeavesResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
