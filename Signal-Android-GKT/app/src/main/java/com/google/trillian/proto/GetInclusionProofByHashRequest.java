// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: trillian_log_api.proto

package com.google.trillian.proto;

/**
 * Protobuf type {@code trillian.GetInclusionProofByHashRequest}
 */
public  final class GetInclusionProofByHashRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:trillian.GetInclusionProofByHashRequest)
    GetInclusionProofByHashRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GetInclusionProofByHashRequest.newBuilder() to construct.
  private GetInclusionProofByHashRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GetInclusionProofByHashRequest() {
    logId_ = 0L;
    leafHash_ = com.google.protobuf.ByteString.EMPTY;
    treeSize_ = 0L;
    orderBySequence_ = false;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GetInclusionProofByHashRequest(
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
          case 18: {

            leafHash_ = input.readBytes();
            break;
          }
          case 24: {

            treeSize_ = input.readInt64();
            break;
          }
          case 32: {

            orderBySequence_ = input.readBool();
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
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.google.trillian.proto.TrillianLogApiProto.internal_static_trillian_GetInclusionProofByHashRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.google.trillian.proto.TrillianLogApiProto.internal_static_trillian_GetInclusionProofByHashRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.google.trillian.proto.GetInclusionProofByHashRequest.class, com.google.trillian.proto.GetInclusionProofByHashRequest.Builder.class);
  }

  public static final int LOG_ID_FIELD_NUMBER = 1;
  private long logId_;
  /**
   * <code>int64 log_id = 1;</code>
   */
  public long getLogId() {
    return logId_;
  }

  public static final int LEAF_HASH_FIELD_NUMBER = 2;
  private com.google.protobuf.ByteString leafHash_;
  /**
   * <pre>
   * The leaf hash field provides the Merkle tree hash of the leaf entry
   * to be retrieved.
   * </pre>
   *
   * <code>bytes leaf_hash = 2;</code>
   */
  public com.google.protobuf.ByteString getLeafHash() {
    return leafHash_;
  }

  public static final int TREE_SIZE_FIELD_NUMBER = 3;
  private long treeSize_;
  /**
   * <code>int64 tree_size = 3;</code>
   */
  public long getTreeSize() {
    return treeSize_;
  }

  public static final int ORDER_BY_SEQUENCE_FIELD_NUMBER = 4;
  private boolean orderBySequence_;
  /**
   * <code>bool order_by_sequence = 4;</code>
   */
  public boolean getOrderBySequence() {
    return orderBySequence_;
  }

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
    if (logId_ != 0L) {
      output.writeInt64(1, logId_);
    }
    if (!leafHash_.isEmpty()) {
      output.writeBytes(2, leafHash_);
    }
    if (treeSize_ != 0L) {
      output.writeInt64(3, treeSize_);
    }
    if (orderBySequence_ != false) {
      output.writeBool(4, orderBySequence_);
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
    if (!leafHash_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(2, leafHash_);
    }
    if (treeSize_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, treeSize_);
    }
    if (orderBySequence_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(4, orderBySequence_);
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
    if (!(obj instanceof com.google.trillian.proto.GetInclusionProofByHashRequest)) {
      return super.equals(obj);
    }
    com.google.trillian.proto.GetInclusionProofByHashRequest other = (com.google.trillian.proto.GetInclusionProofByHashRequest) obj;

    boolean result = true;
    result = result && (getLogId()
        == other.getLogId());
    result = result && getLeafHash()
        .equals(other.getLeafHash());
    result = result && (getTreeSize()
        == other.getTreeSize());
    result = result && (getOrderBySequence()
        == other.getOrderBySequence());
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
    hash = (37 * hash) + LEAF_HASH_FIELD_NUMBER;
    hash = (53 * hash) + getLeafHash().hashCode();
    hash = (37 * hash) + TREE_SIZE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getTreeSize());
    hash = (37 * hash) + ORDER_BY_SEQUENCE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getOrderBySequence());
    if (hasChargeTo()) {
      hash = (37 * hash) + CHARGE_TO_FIELD_NUMBER;
      hash = (53 * hash) + getChargeTo().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.google.trillian.proto.GetInclusionProofByHashRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.trillian.proto.GetInclusionProofByHashRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.trillian.proto.GetInclusionProofByHashRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.trillian.proto.GetInclusionProofByHashRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.trillian.proto.GetInclusionProofByHashRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.trillian.proto.GetInclusionProofByHashRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.trillian.proto.GetInclusionProofByHashRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.google.trillian.proto.GetInclusionProofByHashRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.google.trillian.proto.GetInclusionProofByHashRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.google.trillian.proto.GetInclusionProofByHashRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.google.trillian.proto.GetInclusionProofByHashRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.google.trillian.proto.GetInclusionProofByHashRequest parseFrom(
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
  public static Builder newBuilder(com.google.trillian.proto.GetInclusionProofByHashRequest prototype) {
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
   * Protobuf type {@code trillian.GetInclusionProofByHashRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:trillian.GetInclusionProofByHashRequest)
      com.google.trillian.proto.GetInclusionProofByHashRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.google.trillian.proto.TrillianLogApiProto.internal_static_trillian_GetInclusionProofByHashRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.google.trillian.proto.TrillianLogApiProto.internal_static_trillian_GetInclusionProofByHashRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.google.trillian.proto.GetInclusionProofByHashRequest.class, com.google.trillian.proto.GetInclusionProofByHashRequest.Builder.class);
    }

    // Construct using com.google.trillian.proto.GetInclusionProofByHashRequest.newBuilder()
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

      leafHash_ = com.google.protobuf.ByteString.EMPTY;

      treeSize_ = 0L;

      orderBySequence_ = false;

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
      return com.google.trillian.proto.TrillianLogApiProto.internal_static_trillian_GetInclusionProofByHashRequest_descriptor;
    }

    public com.google.trillian.proto.GetInclusionProofByHashRequest getDefaultInstanceForType() {
      return com.google.trillian.proto.GetInclusionProofByHashRequest.getDefaultInstance();
    }

    public com.google.trillian.proto.GetInclusionProofByHashRequest build() {
      com.google.trillian.proto.GetInclusionProofByHashRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.google.trillian.proto.GetInclusionProofByHashRequest buildPartial() {
      com.google.trillian.proto.GetInclusionProofByHashRequest result = new com.google.trillian.proto.GetInclusionProofByHashRequest(this);
      result.logId_ = logId_;
      result.leafHash_ = leafHash_;
      result.treeSize_ = treeSize_;
      result.orderBySequence_ = orderBySequence_;
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
      if (other instanceof com.google.trillian.proto.GetInclusionProofByHashRequest) {
        return mergeFrom((com.google.trillian.proto.GetInclusionProofByHashRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.google.trillian.proto.GetInclusionProofByHashRequest other) {
      if (other == com.google.trillian.proto.GetInclusionProofByHashRequest.getDefaultInstance()) return this;
      if (other.getLogId() != 0L) {
        setLogId(other.getLogId());
      }
      if (other.getLeafHash() != com.google.protobuf.ByteString.EMPTY) {
        setLeafHash(other.getLeafHash());
      }
      if (other.getTreeSize() != 0L) {
        setTreeSize(other.getTreeSize());
      }
      if (other.getOrderBySequence() != false) {
        setOrderBySequence(other.getOrderBySequence());
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
      com.google.trillian.proto.GetInclusionProofByHashRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.google.trillian.proto.GetInclusionProofByHashRequest) e.getUnfinishedMessage();
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

    private com.google.protobuf.ByteString leafHash_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <pre>
     * The leaf hash field provides the Merkle tree hash of the leaf entry
     * to be retrieved.
     * </pre>
     *
     * <code>bytes leaf_hash = 2;</code>
     */
    public com.google.protobuf.ByteString getLeafHash() {
      return leafHash_;
    }
    /**
     * <pre>
     * The leaf hash field provides the Merkle tree hash of the leaf entry
     * to be retrieved.
     * </pre>
     *
     * <code>bytes leaf_hash = 2;</code>
     */
    public Builder setLeafHash(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      leafHash_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * The leaf hash field provides the Merkle tree hash of the leaf entry
     * to be retrieved.
     * </pre>
     *
     * <code>bytes leaf_hash = 2;</code>
     */
    public Builder clearLeafHash() {
      
      leafHash_ = getDefaultInstance().getLeafHash();
      onChanged();
      return this;
    }

    private long treeSize_ ;
    /**
     * <code>int64 tree_size = 3;</code>
     */
    public long getTreeSize() {
      return treeSize_;
    }
    /**
     * <code>int64 tree_size = 3;</code>
     */
    public Builder setTreeSize(long value) {
      
      treeSize_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 tree_size = 3;</code>
     */
    public Builder clearTreeSize() {
      
      treeSize_ = 0L;
      onChanged();
      return this;
    }

    private boolean orderBySequence_ ;
    /**
     * <code>bool order_by_sequence = 4;</code>
     */
    public boolean getOrderBySequence() {
      return orderBySequence_;
    }
    /**
     * <code>bool order_by_sequence = 4;</code>
     */
    public Builder setOrderBySequence(boolean value) {
      
      orderBySequence_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bool order_by_sequence = 4;</code>
     */
    public Builder clearOrderBySequence() {
      
      orderBySequence_ = false;
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


    // @@protoc_insertion_point(builder_scope:trillian.GetInclusionProofByHashRequest)
  }

  // @@protoc_insertion_point(class_scope:trillian.GetInclusionProofByHashRequest)
  private static final com.google.trillian.proto.GetInclusionProofByHashRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.google.trillian.proto.GetInclusionProofByHashRequest();
  }

  public static com.google.trillian.proto.GetInclusionProofByHashRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GetInclusionProofByHashRequest>
      PARSER = new com.google.protobuf.AbstractParser<GetInclusionProofByHashRequest>() {
    public GetInclusionProofByHashRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new GetInclusionProofByHashRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GetInclusionProofByHashRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GetInclusionProofByHashRequest> getParserForType() {
    return PARSER;
  }

  public com.google.trillian.proto.GetInclusionProofByHashRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

