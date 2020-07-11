// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: trillian.proto

package com.google.trillian.proto;

/**
 * <pre>
 * Proof holds a consistency or inclusion proof for a Merkle tree, as returned
 * by the API.
 * </pre>
 *
 * Protobuf type {@code trillian.Proof}
 */
public  final class Proof extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:trillian.Proof)
    ProofOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Proof.newBuilder() to construct.
  private Proof(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Proof() {
    leafIndex_ = 0L;
    hashes_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private Proof(
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

            leafIndex_ = input.readInt64();
            break;
          }
          case 26: {
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
              hashes_ = new java.util.ArrayList<com.google.protobuf.ByteString>();
              mutable_bitField0_ |= 0x00000002;
            }
            hashes_.add(input.readBytes());
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
        hashes_ = java.util.Collections.unmodifiableList(hashes_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.google.trillian.proto.TrillianProto.internal_static_trillian_Proof_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.google.trillian.proto.TrillianProto.internal_static_trillian_Proof_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.google.trillian.proto.Proof.class, com.google.trillian.proto.Proof.Builder.class);
  }

  private int bitField0_;
  public static final int LEAF_INDEX_FIELD_NUMBER = 1;
  private long leafIndex_;
  /**
   * <pre>
   * leaf_index indicates the requested leaf index when this message is used for
   * a leaf inclusion proof.  This field is set to zero when this message is
   * used for a consistency proof.
   * </pre>
   *
   * <code>int64 leaf_index = 1;</code>
   */
  public long getLeafIndex() {
    return leafIndex_;
  }

  public static final int HASHES_FIELD_NUMBER = 3;
  private java.util.List<com.google.protobuf.ByteString> hashes_;
  /**
   * <code>repeated bytes hashes = 3;</code>
   */
  public java.util.List<com.google.protobuf.ByteString>
      getHashesList() {
    return hashes_;
  }
  /**
   * <code>repeated bytes hashes = 3;</code>
   */
  public int getHashesCount() {
    return hashes_.size();
  }
  /**
   * <code>repeated bytes hashes = 3;</code>
   */
  public com.google.protobuf.ByteString getHashes(int index) {
    return hashes_.get(index);
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
    if (leafIndex_ != 0L) {
      output.writeInt64(1, leafIndex_);
    }
    for (int i = 0; i < hashes_.size(); i++) {
      output.writeBytes(3, hashes_.get(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (leafIndex_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, leafIndex_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < hashes_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeBytesSizeNoTag(hashes_.get(i));
      }
      size += dataSize;
      size += 1 * getHashesList().size();
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
    if (!(obj instanceof com.google.trillian.proto.Proof)) {
      return super.equals(obj);
    }
    com.google.trillian.proto.Proof other = (com.google.trillian.proto.Proof) obj;

    boolean result = true;
    result = result && (getLeafIndex()
        == other.getLeafIndex());
    result = result && getHashesList()
        .equals(other.getHashesList());
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
    hash = (37 * hash) + LEAF_INDEX_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getLeafIndex());
    if (getHashesCount() > 0) {
      hash = (37 * hash) + HASHES_FIELD_NUMBER;
      hash = (53 * hash) + getHashesList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.google.trillian.proto.Proof parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.trillian.proto.Proof parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.trillian.proto.Proof parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.trillian.proto.Proof parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.trillian.proto.Proof parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.trillian.proto.Proof parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.trillian.proto.Proof parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.google.trillian.proto.Proof parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.google.trillian.proto.Proof parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.google.trillian.proto.Proof parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.google.trillian.proto.Proof parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.google.trillian.proto.Proof parseFrom(
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
  public static Builder newBuilder(com.google.trillian.proto.Proof prototype) {
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
   * <pre>
   * Proof holds a consistency or inclusion proof for a Merkle tree, as returned
   * by the API.
   * </pre>
   *
   * Protobuf type {@code trillian.Proof}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:trillian.Proof)
      com.google.trillian.proto.ProofOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.google.trillian.proto.TrillianProto.internal_static_trillian_Proof_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.google.trillian.proto.TrillianProto.internal_static_trillian_Proof_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.google.trillian.proto.Proof.class, com.google.trillian.proto.Proof.Builder.class);
    }

    // Construct using com.google.trillian.proto.Proof.newBuilder()
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
      leafIndex_ = 0L;

      hashes_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.google.trillian.proto.TrillianProto.internal_static_trillian_Proof_descriptor;
    }

    public com.google.trillian.proto.Proof getDefaultInstanceForType() {
      return com.google.trillian.proto.Proof.getDefaultInstance();
    }

    public com.google.trillian.proto.Proof build() {
      com.google.trillian.proto.Proof result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.google.trillian.proto.Proof buildPartial() {
      com.google.trillian.proto.Proof result = new com.google.trillian.proto.Proof(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.leafIndex_ = leafIndex_;
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        hashes_ = java.util.Collections.unmodifiableList(hashes_);
        bitField0_ = (bitField0_ & ~0x00000002);
      }
      result.hashes_ = hashes_;
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
      if (other instanceof com.google.trillian.proto.Proof) {
        return mergeFrom((com.google.trillian.proto.Proof)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.google.trillian.proto.Proof other) {
      if (other == com.google.trillian.proto.Proof.getDefaultInstance()) return this;
      if (other.getLeafIndex() != 0L) {
        setLeafIndex(other.getLeafIndex());
      }
      if (!other.hashes_.isEmpty()) {
        if (hashes_.isEmpty()) {
          hashes_ = other.hashes_;
          bitField0_ = (bitField0_ & ~0x00000002);
        } else {
          ensureHashesIsMutable();
          hashes_.addAll(other.hashes_);
        }
        onChanged();
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
      com.google.trillian.proto.Proof parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.google.trillian.proto.Proof) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long leafIndex_ ;
    /**
     * <pre>
     * leaf_index indicates the requested leaf index when this message is used for
     * a leaf inclusion proof.  This field is set to zero when this message is
     * used for a consistency proof.
     * </pre>
     *
     * <code>int64 leaf_index = 1;</code>
     */
    public long getLeafIndex() {
      return leafIndex_;
    }
    /**
     * <pre>
     * leaf_index indicates the requested leaf index when this message is used for
     * a leaf inclusion proof.  This field is set to zero when this message is
     * used for a consistency proof.
     * </pre>
     *
     * <code>int64 leaf_index = 1;</code>
     */
    public Builder setLeafIndex(long value) {
      
      leafIndex_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * leaf_index indicates the requested leaf index when this message is used for
     * a leaf inclusion proof.  This field is set to zero when this message is
     * used for a consistency proof.
     * </pre>
     *
     * <code>int64 leaf_index = 1;</code>
     */
    public Builder clearLeafIndex() {
      
      leafIndex_ = 0L;
      onChanged();
      return this;
    }

    private java.util.List<com.google.protobuf.ByteString> hashes_ = java.util.Collections.emptyList();
    private void ensureHashesIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        hashes_ = new java.util.ArrayList<com.google.protobuf.ByteString>(hashes_);
        bitField0_ |= 0x00000002;
       }
    }
    /**
     * <code>repeated bytes hashes = 3;</code>
     */
    public java.util.List<com.google.protobuf.ByteString>
        getHashesList() {
      return java.util.Collections.unmodifiableList(hashes_);
    }
    /**
     * <code>repeated bytes hashes = 3;</code>
     */
    public int getHashesCount() {
      return hashes_.size();
    }
    /**
     * <code>repeated bytes hashes = 3;</code>
     */
    public com.google.protobuf.ByteString getHashes(int index) {
      return hashes_.get(index);
    }
    /**
     * <code>repeated bytes hashes = 3;</code>
     */
    public Builder setHashes(
        int index, com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureHashesIsMutable();
      hashes_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated bytes hashes = 3;</code>
     */
    public Builder addHashes(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureHashesIsMutable();
      hashes_.add(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated bytes hashes = 3;</code>
     */
    public Builder addAllHashes(
        java.lang.Iterable<? extends com.google.protobuf.ByteString> values) {
      ensureHashesIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, hashes_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated bytes hashes = 3;</code>
     */
    public Builder clearHashes() {
      hashes_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000002);
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:trillian.Proof)
  }

  // @@protoc_insertion_point(class_scope:trillian.Proof)
  private static final com.google.trillian.proto.Proof DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.google.trillian.proto.Proof();
  }

  public static com.google.trillian.proto.Proof getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Proof>
      PARSER = new com.google.protobuf.AbstractParser<Proof>() {
    public Proof parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new Proof(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Proof> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Proof> getParserForType() {
    return PARSER;
  }

  public com.google.trillian.proto.Proof getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
