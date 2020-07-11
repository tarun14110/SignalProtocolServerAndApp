// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: trillian_log_api.proto

package com.google.trillian.proto;

/**
 * Protobuf type {@code trillian.GetLeavesByRangeRequest}
 */
public  final class GetLeavesByRangeRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:trillian.GetLeavesByRangeRequest)
    GetLeavesByRangeRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GetLeavesByRangeRequest.newBuilder() to construct.
  private GetLeavesByRangeRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GetLeavesByRangeRequest() {
    logId_ = 0L;
    startIndex_ = 0L;
    count_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GetLeavesByRangeRequest(
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

            startIndex_ = input.readInt64();
            break;
          }
          case 24: {

            count_ = input.readInt64();
            break;
          }
          case 34: {
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
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.google.trillian.proto.TrillianLogApiProto.internal_static_trillian_GetLeavesByRangeRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.google.trillian.proto.TrillianLogApiProto.internal_static_trillian_GetLeavesByRangeRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.google.trillian.proto.GetLeavesByRangeRequest.class, com.google.trillian.proto.GetLeavesByRangeRequest.Builder.class);
  }

  public static final int LOG_ID_FIELD_NUMBER = 1;
  private long logId_;
  /**
   * <code>int64 log_id = 1;</code>
   */
  public long getLogId() {
    return logId_;
  }

  public static final int START_INDEX_FIELD_NUMBER = 2;
  private long startIndex_;
  /**
   * <code>int64 start_index = 2;</code>
   */
  public long getStartIndex() {
    return startIndex_;
  }

  public static final int COUNT_FIELD_NUMBER = 3;
  private long count_;
  /**
   * <code>int64 count = 3;</code>
   */
  public long getCount() {
    return count_;
  }

  public static final int CHARGE_TO_FIELD_NUMBER = 4;
  private com.google.trillian.proto.ChargeTo chargeTo_;
  /**
   * <code>.trillian.ChargeTo charge_to = 4;</code>
   */
  public boolean hasChargeTo() {
    return chargeTo_ != null;
  }
  /**
   * <code>.trillian.ChargeTo charge_to = 4;</code>
   */
  public com.google.trillian.proto.ChargeTo getChargeTo() {
    return chargeTo_ == null ? com.google.trillian.proto.ChargeTo.getDefaultInstance() : chargeTo_;
  }
  /**
   * <code>.trillian.ChargeTo charge_to = 4;</code>
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
    if (logId_ != 0L) {
      output.writeInt64(1, logId_);
    }
    if (startIndex_ != 0L) {
      output.writeInt64(2, startIndex_);
    }
    if (count_ != 0L) {
      output.writeInt64(3, count_);
    }
    if (chargeTo_ != null) {
      output.writeMessage(4, getChargeTo());
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
    if (startIndex_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, startIndex_);
    }
    if (count_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, count_);
    }
    if (chargeTo_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(4, getChargeTo());
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
    if (!(obj instanceof com.google.trillian.proto.GetLeavesByRangeRequest)) {
      return super.equals(obj);
    }
    com.google.trillian.proto.GetLeavesByRangeRequest other = (com.google.trillian.proto.GetLeavesByRangeRequest) obj;

    boolean result = true;
    result = result && (getLogId()
        == other.getLogId());
    result = result && (getStartIndex()
        == other.getStartIndex());
    result = result && (getCount()
        == other.getCount());
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
    hash = (37 * hash) + START_INDEX_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getStartIndex());
    hash = (37 * hash) + COUNT_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getCount());
    if (hasChargeTo()) {
      hash = (37 * hash) + CHARGE_TO_FIELD_NUMBER;
      hash = (53 * hash) + getChargeTo().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.google.trillian.proto.GetLeavesByRangeRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.trillian.proto.GetLeavesByRangeRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.trillian.proto.GetLeavesByRangeRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.trillian.proto.GetLeavesByRangeRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.trillian.proto.GetLeavesByRangeRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.trillian.proto.GetLeavesByRangeRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.trillian.proto.GetLeavesByRangeRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.google.trillian.proto.GetLeavesByRangeRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.google.trillian.proto.GetLeavesByRangeRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.google.trillian.proto.GetLeavesByRangeRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.google.trillian.proto.GetLeavesByRangeRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.google.trillian.proto.GetLeavesByRangeRequest parseFrom(
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
  public static Builder newBuilder(com.google.trillian.proto.GetLeavesByRangeRequest prototype) {
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
   * Protobuf type {@code trillian.GetLeavesByRangeRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:trillian.GetLeavesByRangeRequest)
      com.google.trillian.proto.GetLeavesByRangeRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.google.trillian.proto.TrillianLogApiProto.internal_static_trillian_GetLeavesByRangeRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.google.trillian.proto.TrillianLogApiProto.internal_static_trillian_GetLeavesByRangeRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.google.trillian.proto.GetLeavesByRangeRequest.class, com.google.trillian.proto.GetLeavesByRangeRequest.Builder.class);
    }

    // Construct using com.google.trillian.proto.GetLeavesByRangeRequest.newBuilder()
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

      startIndex_ = 0L;

      count_ = 0L;

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
      return com.google.trillian.proto.TrillianLogApiProto.internal_static_trillian_GetLeavesByRangeRequest_descriptor;
    }

    public com.google.trillian.proto.GetLeavesByRangeRequest getDefaultInstanceForType() {
      return com.google.trillian.proto.GetLeavesByRangeRequest.getDefaultInstance();
    }

    public com.google.trillian.proto.GetLeavesByRangeRequest build() {
      com.google.trillian.proto.GetLeavesByRangeRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.google.trillian.proto.GetLeavesByRangeRequest buildPartial() {
      com.google.trillian.proto.GetLeavesByRangeRequest result = new com.google.trillian.proto.GetLeavesByRangeRequest(this);
      result.logId_ = logId_;
      result.startIndex_ = startIndex_;
      result.count_ = count_;
      if (chargeToBuilder_ == null) {
        result.chargeTo_ = chargeTo_;
      } else {
        result.chargeTo_ = chargeToBuilder_.build();
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
      if (other instanceof com.google.trillian.proto.GetLeavesByRangeRequest) {
        return mergeFrom((com.google.trillian.proto.GetLeavesByRangeRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.google.trillian.proto.GetLeavesByRangeRequest other) {
      if (other == com.google.trillian.proto.GetLeavesByRangeRequest.getDefaultInstance()) return this;
      if (other.getLogId() != 0L) {
        setLogId(other.getLogId());
      }
      if (other.getStartIndex() != 0L) {
        setStartIndex(other.getStartIndex());
      }
      if (other.getCount() != 0L) {
        setCount(other.getCount());
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
      com.google.trillian.proto.GetLeavesByRangeRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.google.trillian.proto.GetLeavesByRangeRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

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

    private long startIndex_ ;
    /**
     * <code>int64 start_index = 2;</code>
     */
    public long getStartIndex() {
      return startIndex_;
    }
    /**
     * <code>int64 start_index = 2;</code>
     */
    public Builder setStartIndex(long value) {
      
      startIndex_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 start_index = 2;</code>
     */
    public Builder clearStartIndex() {
      
      startIndex_ = 0L;
      onChanged();
      return this;
    }

    private long count_ ;
    /**
     * <code>int64 count = 3;</code>
     */
    public long getCount() {
      return count_;
    }
    /**
     * <code>int64 count = 3;</code>
     */
    public Builder setCount(long value) {
      
      count_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 count = 3;</code>
     */
    public Builder clearCount() {
      
      count_ = 0L;
      onChanged();
      return this;
    }

    private com.google.trillian.proto.ChargeTo chargeTo_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.trillian.proto.ChargeTo, com.google.trillian.proto.ChargeTo.Builder, com.google.trillian.proto.ChargeToOrBuilder> chargeToBuilder_;
    /**
     * <code>.trillian.ChargeTo charge_to = 4;</code>
     */
    public boolean hasChargeTo() {
      return chargeToBuilder_ != null || chargeTo_ != null;
    }
    /**
     * <code>.trillian.ChargeTo charge_to = 4;</code>
     */
    public com.google.trillian.proto.ChargeTo getChargeTo() {
      if (chargeToBuilder_ == null) {
        return chargeTo_ == null ? com.google.trillian.proto.ChargeTo.getDefaultInstance() : chargeTo_;
      } else {
        return chargeToBuilder_.getMessage();
      }
    }
    /**
     * <code>.trillian.ChargeTo charge_to = 4;</code>
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
     * <code>.trillian.ChargeTo charge_to = 4;</code>
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
     * <code>.trillian.ChargeTo charge_to = 4;</code>
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
     * <code>.trillian.ChargeTo charge_to = 4;</code>
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
     * <code>.trillian.ChargeTo charge_to = 4;</code>
     */
    public com.google.trillian.proto.ChargeTo.Builder getChargeToBuilder() {
      
      onChanged();
      return getChargeToFieldBuilder().getBuilder();
    }
    /**
     * <code>.trillian.ChargeTo charge_to = 4;</code>
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
     * <code>.trillian.ChargeTo charge_to = 4;</code>
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


    // @@protoc_insertion_point(builder_scope:trillian.GetLeavesByRangeRequest)
  }

  // @@protoc_insertion_point(class_scope:trillian.GetLeavesByRangeRequest)
  private static final com.google.trillian.proto.GetLeavesByRangeRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.google.trillian.proto.GetLeavesByRangeRequest();
  }

  public static com.google.trillian.proto.GetLeavesByRangeRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GetLeavesByRangeRequest>
      PARSER = new com.google.protobuf.AbstractParser<GetLeavesByRangeRequest>() {
    public GetLeavesByRangeRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new GetLeavesByRangeRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GetLeavesByRangeRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GetLeavesByRangeRequest> getParserForType() {
    return PARSER;
  }

  public com.google.trillian.proto.GetLeavesByRangeRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
