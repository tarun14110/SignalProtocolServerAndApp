// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: trillian_map_api.proto

package com.google.trillian.proto;

/**
 * <pre>
 * MapLeaf represents the data behind Map leaves.
 * </pre>
 *
 * Protobuf type {@code trillian.MapLeaf}
 */
public  final class MapLeaf extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:trillian.MapLeaf)
    MapLeafOrBuilder {
private static final long serialVersionUID = 0L;
  // Use MapLeaf.newBuilder() to construct.
  private MapLeaf(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private MapLeaf() {
    index_ = com.google.protobuf.ByteString.EMPTY;
    leafHash_ = com.google.protobuf.ByteString.EMPTY;
    leafValue_ = com.google.protobuf.ByteString.EMPTY;
    extraData_ = com.google.protobuf.ByteString.EMPTY;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private MapLeaf(
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
          case 10: {

            index_ = input.readBytes();
            break;
          }
          case 18: {

            leafHash_ = input.readBytes();
            break;
          }
          case 26: {

            leafValue_ = input.readBytes();
            break;
          }
          case 34: {

            extraData_ = input.readBytes();
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
    return com.google.trillian.proto.TrillianMapApiProto.internal_static_trillian_MapLeaf_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.google.trillian.proto.TrillianMapApiProto.internal_static_trillian_MapLeaf_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.google.trillian.proto.MapLeaf.class, com.google.trillian.proto.MapLeaf.Builder.class);
  }

  public static final int INDEX_FIELD_NUMBER = 1;
  private com.google.protobuf.ByteString index_;
  /**
   * <pre>
   * index is the location of this leaf.
   * All indexes for a given Map must contain a constant number of bits.
   * These are not numeric indices. Note that this is typically derived using a
   * hash and thus the length of all indices in the map will match the number
   * of bits in the hash function.
   * </pre>
   *
   * <code>bytes index = 1;</code>
   */
  public com.google.protobuf.ByteString getIndex() {
    return index_;
  }

  public static final int LEAF_HASH_FIELD_NUMBER = 2;
  private com.google.protobuf.ByteString leafHash_;
  /**
   * <pre>
   * leaf_hash is the tree hash of leaf_value.  This does not need to be set
   * on SetMapLeavesRequest; the server will fill it in.
   * For an empty leaf (len(leaf_value)==0), there may be two possible values
   * for this hash:
   *  - If the leaf has never been set, it counts as an empty subtree and
   *    a nil value is used.
   *  - If the leaf has been explicitly set to a zero-length entry, it no
   *    longer counts as empty and the value of hasher.HashLeaf(index, nil)
   *    will be used.
   * </pre>
   *
   * <code>bytes leaf_hash = 2;</code>
   */
  public com.google.protobuf.ByteString getLeafHash() {
    return leafHash_;
  }

  public static final int LEAF_VALUE_FIELD_NUMBER = 3;
  private com.google.protobuf.ByteString leafValue_;
  /**
   * <pre>
   * leaf_value is the data the tree commits to.
   * </pre>
   *
   * <code>bytes leaf_value = 3;</code>
   */
  public com.google.protobuf.ByteString getLeafValue() {
    return leafValue_;
  }

  public static final int EXTRA_DATA_FIELD_NUMBER = 4;
  private com.google.protobuf.ByteString extraData_;
  /**
   * <pre>
   * extra_data holds related contextual data, but is not covered by any hash.
   * </pre>
   *
   * <code>bytes extra_data = 4;</code>
   */
  public com.google.protobuf.ByteString getExtraData() {
    return extraData_;
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
    if (!index_.isEmpty()) {
      output.writeBytes(1, index_);
    }
    if (!leafHash_.isEmpty()) {
      output.writeBytes(2, leafHash_);
    }
    if (!leafValue_.isEmpty()) {
      output.writeBytes(3, leafValue_);
    }
    if (!extraData_.isEmpty()) {
      output.writeBytes(4, extraData_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!index_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(1, index_);
    }
    if (!leafHash_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(2, leafHash_);
    }
    if (!leafValue_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(3, leafValue_);
    }
    if (!extraData_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(4, extraData_);
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
    if (!(obj instanceof com.google.trillian.proto.MapLeaf)) {
      return super.equals(obj);
    }
    com.google.trillian.proto.MapLeaf other = (com.google.trillian.proto.MapLeaf) obj;

    boolean result = true;
    result = result && getIndex()
        .equals(other.getIndex());
    result = result && getLeafHash()
        .equals(other.getLeafHash());
    result = result && getLeafValue()
        .equals(other.getLeafValue());
    result = result && getExtraData()
        .equals(other.getExtraData());
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
    hash = (37 * hash) + INDEX_FIELD_NUMBER;
    hash = (53 * hash) + getIndex().hashCode();
    hash = (37 * hash) + LEAF_HASH_FIELD_NUMBER;
    hash = (53 * hash) + getLeafHash().hashCode();
    hash = (37 * hash) + LEAF_VALUE_FIELD_NUMBER;
    hash = (53 * hash) + getLeafValue().hashCode();
    hash = (37 * hash) + EXTRA_DATA_FIELD_NUMBER;
    hash = (53 * hash) + getExtraData().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.google.trillian.proto.MapLeaf parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.trillian.proto.MapLeaf parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.trillian.proto.MapLeaf parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.trillian.proto.MapLeaf parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.trillian.proto.MapLeaf parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.trillian.proto.MapLeaf parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.trillian.proto.MapLeaf parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.google.trillian.proto.MapLeaf parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.google.trillian.proto.MapLeaf parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.google.trillian.proto.MapLeaf parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.google.trillian.proto.MapLeaf parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.google.trillian.proto.MapLeaf parseFrom(
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
  public static Builder newBuilder(com.google.trillian.proto.MapLeaf prototype) {
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
   * MapLeaf represents the data behind Map leaves.
   * </pre>
   *
   * Protobuf type {@code trillian.MapLeaf}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:trillian.MapLeaf)
      com.google.trillian.proto.MapLeafOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.google.trillian.proto.TrillianMapApiProto.internal_static_trillian_MapLeaf_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.google.trillian.proto.TrillianMapApiProto.internal_static_trillian_MapLeaf_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.google.trillian.proto.MapLeaf.class, com.google.trillian.proto.MapLeaf.Builder.class);
    }

    // Construct using com.google.trillian.proto.MapLeaf.newBuilder()
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
      index_ = com.google.protobuf.ByteString.EMPTY;

      leafHash_ = com.google.protobuf.ByteString.EMPTY;

      leafValue_ = com.google.protobuf.ByteString.EMPTY;

      extraData_ = com.google.protobuf.ByteString.EMPTY;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.google.trillian.proto.TrillianMapApiProto.internal_static_trillian_MapLeaf_descriptor;
    }

    public com.google.trillian.proto.MapLeaf getDefaultInstanceForType() {
      return com.google.trillian.proto.MapLeaf.getDefaultInstance();
    }

    public com.google.trillian.proto.MapLeaf build() {
      com.google.trillian.proto.MapLeaf result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.google.trillian.proto.MapLeaf buildPartial() {
      com.google.trillian.proto.MapLeaf result = new com.google.trillian.proto.MapLeaf(this);
      result.index_ = index_;
      result.leafHash_ = leafHash_;
      result.leafValue_ = leafValue_;
      result.extraData_ = extraData_;
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
      if (other instanceof com.google.trillian.proto.MapLeaf) {
        return mergeFrom((com.google.trillian.proto.MapLeaf)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.google.trillian.proto.MapLeaf other) {
      if (other == com.google.trillian.proto.MapLeaf.getDefaultInstance()) return this;
      if (other.getIndex() != com.google.protobuf.ByteString.EMPTY) {
        setIndex(other.getIndex());
      }
      if (other.getLeafHash() != com.google.protobuf.ByteString.EMPTY) {
        setLeafHash(other.getLeafHash());
      }
      if (other.getLeafValue() != com.google.protobuf.ByteString.EMPTY) {
        setLeafValue(other.getLeafValue());
      }
      if (other.getExtraData() != com.google.protobuf.ByteString.EMPTY) {
        setExtraData(other.getExtraData());
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
      com.google.trillian.proto.MapLeaf parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.google.trillian.proto.MapLeaf) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private com.google.protobuf.ByteString index_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <pre>
     * index is the location of this leaf.
     * All indexes for a given Map must contain a constant number of bits.
     * These are not numeric indices. Note that this is typically derived using a
     * hash and thus the length of all indices in the map will match the number
     * of bits in the hash function.
     * </pre>
     *
     * <code>bytes index = 1;</code>
     */
    public com.google.protobuf.ByteString getIndex() {
      return index_;
    }
    /**
     * <pre>
     * index is the location of this leaf.
     * All indexes for a given Map must contain a constant number of bits.
     * These are not numeric indices. Note that this is typically derived using a
     * hash and thus the length of all indices in the map will match the number
     * of bits in the hash function.
     * </pre>
     *
     * <code>bytes index = 1;</code>
     */
    public Builder setIndex(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      index_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * index is the location of this leaf.
     * All indexes for a given Map must contain a constant number of bits.
     * These are not numeric indices. Note that this is typically derived using a
     * hash and thus the length of all indices in the map will match the number
     * of bits in the hash function.
     * </pre>
     *
     * <code>bytes index = 1;</code>
     */
    public Builder clearIndex() {
      
      index_ = getDefaultInstance().getIndex();
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString leafHash_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <pre>
     * leaf_hash is the tree hash of leaf_value.  This does not need to be set
     * on SetMapLeavesRequest; the server will fill it in.
     * For an empty leaf (len(leaf_value)==0), there may be two possible values
     * for this hash:
     *  - If the leaf has never been set, it counts as an empty subtree and
     *    a nil value is used.
     *  - If the leaf has been explicitly set to a zero-length entry, it no
     *    longer counts as empty and the value of hasher.HashLeaf(index, nil)
     *    will be used.
     * </pre>
     *
     * <code>bytes leaf_hash = 2;</code>
     */
    public com.google.protobuf.ByteString getLeafHash() {
      return leafHash_;
    }
    /**
     * <pre>
     * leaf_hash is the tree hash of leaf_value.  This does not need to be set
     * on SetMapLeavesRequest; the server will fill it in.
     * For an empty leaf (len(leaf_value)==0), there may be two possible values
     * for this hash:
     *  - If the leaf has never been set, it counts as an empty subtree and
     *    a nil value is used.
     *  - If the leaf has been explicitly set to a zero-length entry, it no
     *    longer counts as empty and the value of hasher.HashLeaf(index, nil)
     *    will be used.
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
     * leaf_hash is the tree hash of leaf_value.  This does not need to be set
     * on SetMapLeavesRequest; the server will fill it in.
     * For an empty leaf (len(leaf_value)==0), there may be two possible values
     * for this hash:
     *  - If the leaf has never been set, it counts as an empty subtree and
     *    a nil value is used.
     *  - If the leaf has been explicitly set to a zero-length entry, it no
     *    longer counts as empty and the value of hasher.HashLeaf(index, nil)
     *    will be used.
     * </pre>
     *
     * <code>bytes leaf_hash = 2;</code>
     */
    public Builder clearLeafHash() {
      
      leafHash_ = getDefaultInstance().getLeafHash();
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString leafValue_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <pre>
     * leaf_value is the data the tree commits to.
     * </pre>
     *
     * <code>bytes leaf_value = 3;</code>
     */
    public com.google.protobuf.ByteString getLeafValue() {
      return leafValue_;
    }
    /**
     * <pre>
     * leaf_value is the data the tree commits to.
     * </pre>
     *
     * <code>bytes leaf_value = 3;</code>
     */
    public Builder setLeafValue(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      leafValue_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * leaf_value is the data the tree commits to.
     * </pre>
     *
     * <code>bytes leaf_value = 3;</code>
     */
    public Builder clearLeafValue() {
      
      leafValue_ = getDefaultInstance().getLeafValue();
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString extraData_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <pre>
     * extra_data holds related contextual data, but is not covered by any hash.
     * </pre>
     *
     * <code>bytes extra_data = 4;</code>
     */
    public com.google.protobuf.ByteString getExtraData() {
      return extraData_;
    }
    /**
     * <pre>
     * extra_data holds related contextual data, but is not covered by any hash.
     * </pre>
     *
     * <code>bytes extra_data = 4;</code>
     */
    public Builder setExtraData(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      extraData_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * extra_data holds related contextual data, but is not covered by any hash.
     * </pre>
     *
     * <code>bytes extra_data = 4;</code>
     */
    public Builder clearExtraData() {
      
      extraData_ = getDefaultInstance().getExtraData();
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


    // @@protoc_insertion_point(builder_scope:trillian.MapLeaf)
  }

  // @@protoc_insertion_point(class_scope:trillian.MapLeaf)
  private static final com.google.trillian.proto.MapLeaf DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.google.trillian.proto.MapLeaf();
  }

  public static com.google.trillian.proto.MapLeaf getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<MapLeaf>
      PARSER = new com.google.protobuf.AbstractParser<MapLeaf>() {
    public MapLeaf parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new MapLeaf(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<MapLeaf> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<MapLeaf> getParserForType() {
    return PARSER;
  }

  public com.google.trillian.proto.MapLeaf getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

