// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: trillian_log_api.proto

package com.google.trillian.proto;

/**
 * Protobuf type {@code trillian.GetLeavesByIndexRequest}
 */
public  final class GetLeavesByIndexRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:trillian.GetLeavesByIndexRequest)
    GetLeavesByIndexRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GetLeavesByIndexRequest.newBuilder() to construct.
  private GetLeavesByIndexRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GetLeavesByIndexRequest() {
    logId_ = 0L;
    leafIndex_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GetLeavesByIndexRequest(
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
          case 8: {

            logId_ = input.readInt64();
            break;
          }
          case 16: {
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
              leafIndex_ = new java.util.ArrayList<java.lang.Long>();
              mutable_bitField0_ |= 0x00000002;
            }
            leafIndex_.add(input.readInt64());
            break;
          }
          case 18: {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002) && input.getBytesUntilLimit() > 0) {
              leafIndex_ = new java.util.ArrayList<java.lang.Long>();
              mutable_bitField0_ |= 0x00000002;
            }
            while (input.getBytesUntilLimit() > 0) {
              leafIndex_.add(input.readInt64());
            }
            input.popLimit(limit);
            break;
          }
          case 42: {
            com.google.trillian.proto.ChargeTo.Builder subBuilder = null;
            if (chargeTo_ != null) {
              subBuilder = chargeTo_.toBuilder();
            }
            chargeTo_ = input.readMessage(com.google.trillian.proto.ChargeTo.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(chargeTo_);
              chargeTo_ = subBuilder.buildPartial();
            }

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
      if (((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
        leafIndex_ = java.util.Collections.unmodifiableList(leafIndex_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.google.trillian.proto.TrillianLogApiProto.internal_static_trillian_GetLeavesByIndexRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.google.trillian.proto.TrillianLogApiProto.internal_static_trillian_GetLeavesByIndexRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.google.trillian.proto.GetLeavesByIndexRequest.class, com.google.trillian.proto.GetLeavesByIndexRequest.Builder.class);
  }

  private int bitField0_;
  public static final int LOG_ID_FIELD_NUMBER = 1;
  private long logId_;
  /**
   * <code>int64 log_id = 1;</code>
   */
  public long getLogId() {
    return logId_;
  }

  public static final int LEAF_INDEX_FIELD_NUMBER = 2;
  private java.util.List<java.lang.Long> leafIndex_;
  /**
   * <code>repeated int64 leaf_index = 2;</code>
   */
  public java.util.List<java.lang.Long>
      getLeafIndexList() {
    return leafIndex_;
  }
  /**
   * <code>repeated int64 leaf_index = 2;</code>
   */
  public int getLeafIndexCount() {
    return leafIndex_.size();
  }
  /**
   * <code>repeated int64 leaf_index = 2;</code>
   */
  public long getLeafIndex(int index) {
    return leafIndex_.get(index);
  }
  private int leafIndexMemoizedSerializedSize = -1;

  public static final int CHARGE_TO_FIELD_NUMBER = 5;
  private com.google.trillian.proto.ChargeTo chargeTo_;
  /**
   * <code>.trillian.ChargeTo charge_to = 5;</code>
   */
  public boolean hasChargeTo() {
    return chargeTo_ != null;
  }
  /**
   * <code>.trillian.ChargeTo charge_to = 5;</code>
   */
  public com.google.trillian.proto.ChargeTo getChargeTo() {
    return chargeTo_ == null ? com.google.trillian.proto.ChargeTo.getDefaultInstance() : chargeTo_;
  }
  /**
   * <code>.trillian.ChargeTo charge_to = 5;</code>
   */
  public com.google.trillian.proto.ChargeToOrBuilder getChargeToOrBuilder() {
    return getChargeTo();
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
    getSerializedSize();
    if (logId_ != 0L) {
      output.writeInt64(1, logId_);
    }
    if (getLeafIndexList().size() > 0) {
      output.writeUInt32NoTag(18);
      output.writeUInt32NoTag(leafIndexMemoizedSerializedSize);
    }
    for (int i = 0; i < leafIndex_.size(); i++) {
      output.writeInt64NoTag(leafIndex_.get(i));
    }
    if (chargeTo_ != null) {
      output.writeMessage(5, getChargeTo());
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (logId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, logId_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < leafIndex_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeInt64SizeNoTag(leafIndex_.get(i));
      }
      size += dataSize;
      if (!getLeafIndexList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      leafIndexMemoizedSerializedSize = dataSize;
    }
    if (chargeTo_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(5, getChargeTo());
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
    if (!(obj instanceof com.google.trillian.proto.GetLeavesByIndexRequest)) {
      return super.equals(obj);
    }
    com.google.trillian.proto.GetLeavesByIndexRequest other = (com.google.trillian.proto.GetLeavesByIndexRequest) obj;

    boolean result = true;
    result = result && (getLogId()
        == other.getLogId());
    result = result && getLeafIndexList()
        .equals(other.getLeafIndexList());
    result = result && (hasChargeTo() == other.hasChargeTo());
    if (hasChargeTo()) {
      result = result && getChargeTo()
          .equals(other.getChargeTo());
    }
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
    hash = (37 * hash) + LOG_ID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getLogId());
    if (getLeafIndexCount() > 0) {
      hash = (37 * hash) + LEAF_INDEX_FIELD_NUMBER;
      hash = (53 * hash) + getLeafIndexList().hashCode();
    }
    if (hasChargeTo()) {
      hash = (37 * hash) + CHARGE_TO_FIELD_NUMBER;
      hash = (53 * hash) + getChargeTo().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.google.trillian.proto.GetLeavesByIndexRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.trillian.proto.GetLeavesByIndexRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.trillian.proto.GetLeavesByIndexRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.trillian.proto.GetLeavesByIndexRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.trillian.proto.GetLeavesByIndexRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.trillian.proto.GetLeavesByIndexRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.trillian.proto.GetLeavesByIndexRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.google.trillian.proto.GetLeavesByIndexRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.google.trillian.proto.GetLeavesByIndexRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.google.trillian.proto.GetLeavesByIndexRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.google.trillian.proto.GetLeavesByIndexRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.google.trillian.proto.GetLeavesByIndexRequest parseFrom(
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
  public static Builder newBuilder(com.google.trillian.proto.GetLeavesByIndexRequest prototype) {
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
   * Protobuf type {@code trillian.GetLeavesByIndexRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:trillian.GetLeavesByIndexRequest)
      com.google.trillian.proto.GetLeavesByIndexRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.google.trillian.proto.TrillianLogApiProto.internal_static_trillian_GetLeavesByIndexRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.google.trillian.proto.TrillianLogApiProto.internal_static_trillian_GetLeavesByIndexRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.google.trillian.proto.GetLeavesByIndexRequest.class, com.google.trillian.proto.GetLeavesByIndexRequest.Builder.class);
    }

    // Construct using com.google.trillian.proto.GetLeavesByIndexRequest.newBuilder()
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
      }
    }
    public Builder clear() {
      super.clear();
      logId_ = 0L;

      leafIndex_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000002);
      if (chargeToBuilder_ == null) {
        chargeTo_ = null;
      } else {
        chargeTo_ = null;
        chargeToBuilder_ = null;
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.google.trillian.proto.TrillianLogApiProto.internal_static_trillian_GetLeavesByIndexRequest_descriptor;
    }

    public com.google.trillian.proto.GetLeavesByIndexRequest getDefaultInstanceForType() {
      return com.google.trillian.proto.GetLeavesByIndexRequest.getDefaultInstance();
    }

    public com.google.trillian.proto.GetLeavesByIndexRequest build() {
      com.google.trillian.proto.GetLeavesByIndexRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.google.trillian.proto.GetLeavesByIndexRequest buildPartial() {
      com.google.trillian.proto.GetLeavesByIndexRequest result = new com.google.trillian.proto.GetLeavesByIndexRequest(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.logId_ = logId_;
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        leafIndex_ = java.util.Collections.unmodifiableList(leafIndex_);
        bitField0_ = (bitField0_ & ~0x00000002);
      }
      result.leafIndex_ = leafIndex_;
      if (chargeToBuilder_ == null) {
        result.chargeTo_ = chargeTo_;
      } else {
        result.chargeTo_ = chargeToBuilder_.build();
      }
      result.bitField0_ = to_bitField0_;
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
      if (other instanceof com.google.trillian.proto.GetLeavesByIndexRequest) {
        return mergeFrom((com.google.trillian.proto.GetLeavesByIndexRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.google.trillian.proto.GetLeavesByIndexRequest other) {
      if (other == com.google.trillian.proto.GetLeavesByIndexRequest.getDefaultInstance()) return this;
      if (other.getLogId() != 0L) {
        setLogId(other.getLogId());
      }
      if (!other.leafIndex_.isEmpty()) {
        if (leafIndex_.isEmpty()) {
          leafIndex_ = other.leafIndex_;
          bitField0_ = (bitField0_ & ~0x00000002);
        } else {
          ensureLeafIndexIsMutable();
          leafIndex_.addAll(other.leafIndex_);
        }
        onChanged();
      }
      if (other.hasChargeTo()) {
        mergeChargeTo(other.getChargeTo());
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
      com.google.trillian.proto.GetLeavesByIndexRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.google.trillian.proto.GetLeavesByIndexRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long logId_ ;
    /**
     * <code>int64 log_id = 1;</code>
     */
    public long getLogId() {
      return logId_;
    }
    /**
     * <code>int64 log_id = 1;</code>
     */
    public Builder setLogId(long value) {
      
      logId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 log_id = 1;</code>
     */
    public Builder clearLogId() {
      
      logId_ = 0L;
      onChanged();
      return this;
    }

    private java.util.List<java.lang.Long> leafIndex_ = java.util.Collections.emptyList();
    private void ensureLeafIndexIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        leafIndex_ = new java.util.ArrayList<java.lang.Long>(leafIndex_);
        bitField0_ |= 0x00000002;
       }
    }
    /**
     * <code>repeated int64 leaf_index = 2;</code>
     */
    public java.util.List<java.lang.Long>
        getLeafIndexList() {
      return java.util.Collections.unmodifiableList(leafIndex_);
    }
    /**
     * <code>repeated int64 leaf_index = 2;</code>
     */
    public int getLeafIndexCount() {
      return leafIndex_.size();
    }
    /**
     * <code>repeated int64 leaf_index = 2;</code>
     */
    public long getLeafIndex(int index) {
      return leafIndex_.get(index);
    }
    /**
     * <code>repeated int64 leaf_index = 2;</code>
     */
    public Builder setLeafIndex(
        int index, long value) {
      ensureLeafIndexIsMutable();
      leafIndex_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int64 leaf_index = 2;</code>
     */
    public Builder addLeafIndex(long value) {
      ensureLeafIndexIsMutable();
      leafIndex_.add(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int64 leaf_index = 2;</code>
     */
    public Builder addAllLeafIndex(
        java.lang.Iterable<? extends java.lang.Long> values) {
      ensureLeafIndexIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, leafIndex_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int64 leaf_index = 2;</code>
     */
    public Builder clearLeafIndex() {
      leafIndex_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000002);
      onChanged();
      return this;
    }

    private com.google.trillian.proto.ChargeTo chargeTo_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.trillian.proto.ChargeTo, com.google.trillian.proto.ChargeTo.Builder, com.google.trillian.proto.ChargeToOrBuilder> chargeToBuilder_;
    /**
     * <code>.trillian.ChargeTo charge_to = 5;</code>
     */
    public boolean hasChargeTo() {
      return chargeToBuilder_ != null || chargeTo_ != null;
    }
    /**
     * <code>.trillian.ChargeTo charge_to = 5;</code>
     */
    public com.google.trillian.proto.ChargeTo getChargeTo() {
      if (chargeToBuilder_ == null) {
        return chargeTo_ == null ? com.google.trillian.proto.ChargeTo.getDefaultInstance() : chargeTo_;
      } else {
        return chargeToBuilder_.getMessage();
      }
    }
    /**
     * <code>.trillian.ChargeTo charge_to = 5;</code>
     */
    public Builder setChargeTo(com.google.trillian.proto.ChargeTo value) {
      if (chargeToBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        chargeTo_ = value;
        onChanged();
      } else {
        chargeToBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.trillian.ChargeTo charge_to = 5;</code>
     */
    public Builder setChargeTo(
        com.google.trillian.proto.ChargeTo.Builder builderForValue) {
      if (chargeToBuilder_ == null) {
        chargeTo_ = builderForValue.build();
        onChanged();
      } else {
        chargeToBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.trillian.ChargeTo charge_to = 5;</code>
     */
    public Builder mergeChargeTo(com.google.trillian.proto.ChargeTo value) {
      if (chargeToBuilder_ == null) {
        if (chargeTo_ != null) {
          chargeTo_ =
            com.google.trillian.proto.ChargeTo.newBuilder(chargeTo_).mergeFrom(value).buildPartial();
        } else {
          chargeTo_ = value;
        }
        onChanged();
      } else {
        chargeToBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.trillian.ChargeTo charge_to = 5;</code>
     */
    public Builder clearChargeTo() {
      if (chargeToBuilder_ == null) {
        chargeTo_ = null;
        onChanged();
      } else {
        chargeTo_ = null;
        chargeToBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.trillian.ChargeTo charge_to = 5;</code>
     */
    public com.google.trillian.proto.ChargeTo.Builder getChargeToBuilder() {
      
      onChanged();
      return getChargeToFieldBuilder().getBuilder();
    }
    /**
     * <code>.trillian.ChargeTo charge_to = 5;</code>
     */
    public com.google.trillian.proto.ChargeToOrBuilder getChargeToOrBuilder() {
      if (chargeToBuilder_ != null) {
        return chargeToBuilder_.getMessageOrBuilder();
      } else {
        return chargeTo_ == null ?
            com.google.trillian.proto.ChargeTo.getDefaultInstance() : chargeTo_;
      }
    }
    /**
     * <code>.trillian.ChargeTo charge_to = 5;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.trillian.proto.ChargeTo, com.google.trillian.proto.ChargeTo.Builder, com.google.trillian.proto.ChargeToOrBuilder> 
        getChargeToFieldBuilder() {
      if (chargeToBuilder_ == null) {
        chargeToBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.google.trillian.proto.ChargeTo, com.google.trillian.proto.ChargeTo.Builder, com.google.trillian.proto.ChargeToOrBuilder>(
                getChargeTo(),
                getParentForChildren(),
                isClean());
        chargeTo_ = null;
      }
      return chargeToBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:trillian.GetLeavesByIndexRequest)
  }

  // @@protoc_insertion_point(class_scope:trillian.GetLeavesByIndexRequest)
  private static final com.google.trillian.proto.GetLeavesByIndexRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.google.trillian.proto.GetLeavesByIndexRequest();
  }

  public static com.google.trillian.proto.GetLeavesByIndexRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GetLeavesByIndexRequest>
      PARSER = new com.google.protobuf.AbstractParser<GetLeavesByIndexRequest>() {
    public GetLeavesByIndexRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new GetLeavesByIndexRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GetLeavesByIndexRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GetLeavesByIndexRequest> getParserForType() {
    return PARSER;
  }

  public com.google.trillian.proto.GetLeavesByIndexRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

