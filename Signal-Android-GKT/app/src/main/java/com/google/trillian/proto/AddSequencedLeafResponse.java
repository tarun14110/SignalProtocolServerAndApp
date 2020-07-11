// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: trillian_log_api.proto

package com.google.trillian.proto;

/**
 * Protobuf type {@code trillian.AddSequencedLeafResponse}
 */
public  final class AddSequencedLeafResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:trillian.AddSequencedLeafResponse)
    AddSequencedLeafResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use AddSequencedLeafResponse.newBuilder() to construct.
  private AddSequencedLeafResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AddSequencedLeafResponse() {
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private AddSequencedLeafResponse(
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
            com.google.trillian.proto.QueuedLogLeaf.Builder subBuilder = null;
            if (result_ != null) {
              subBuilder = result_.toBuilder();
            }
            result_ = input.readMessage(com.google.trillian.proto.QueuedLogLeaf.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(result_);
              result_ = subBuilder.buildPartial();
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
    return com.google.trillian.proto.TrillianLogApiProto.internal_static_trillian_AddSequencedLeafResponse_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.google.trillian.proto.TrillianLogApiProto.internal_static_trillian_AddSequencedLeafResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.google.trillian.proto.AddSequencedLeafResponse.class, com.google.trillian.proto.AddSequencedLeafResponse.Builder.class);
  }

  public static final int RESULT_FIELD_NUMBER = 2;
  private com.google.trillian.proto.QueuedLogLeaf result_;
  /**
   * <code>.trillian.QueuedLogLeaf result = 2;</code>
   */
  public boolean hasResult() {
    return result_ != null;
  }
  /**
   * <code>.trillian.QueuedLogLeaf result = 2;</code>
   */
  public com.google.trillian.proto.QueuedLogLeaf getResult() {
    return result_ == null ? com.google.trillian.proto.QueuedLogLeaf.getDefaultInstance() : result_;
  }
  /**
   * <code>.trillian.QueuedLogLeaf result = 2;</code>
   */
  public com.google.trillian.proto.QueuedLogLeafOrBuilder getResultOrBuilder() {
    return getResult();
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
    if (result_ != null) {
      output.writeMessage(2, getResult());
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (result_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getResult());
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
    if (!(obj instanceof com.google.trillian.proto.AddSequencedLeafResponse)) {
      return super.equals(obj);
    }
    com.google.trillian.proto.AddSequencedLeafResponse other = (com.google.trillian.proto.AddSequencedLeafResponse) obj;

    boolean result = true;
    result = result && (hasResult() == other.hasResult());
    if (hasResult()) {
      result = result && getResult()
          .equals(other.getResult());
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
    if (hasResult()) {
      hash = (37 * hash) + RESULT_FIELD_NUMBER;
      hash = (53 * hash) + getResult().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.google.trillian.proto.AddSequencedLeafResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.trillian.proto.AddSequencedLeafResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.trillian.proto.AddSequencedLeafResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.trillian.proto.AddSequencedLeafResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.trillian.proto.AddSequencedLeafResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.trillian.proto.AddSequencedLeafResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.trillian.proto.AddSequencedLeafResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.google.trillian.proto.AddSequencedLeafResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.google.trillian.proto.AddSequencedLeafResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.google.trillian.proto.AddSequencedLeafResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.google.trillian.proto.AddSequencedLeafResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.google.trillian.proto.AddSequencedLeafResponse parseFrom(
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
  public static Builder newBuilder(com.google.trillian.proto.AddSequencedLeafResponse prototype) {
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
   * Protobuf type {@code trillian.AddSequencedLeafResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:trillian.AddSequencedLeafResponse)
      com.google.trillian.proto.AddSequencedLeafResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.google.trillian.proto.TrillianLogApiProto.internal_static_trillian_AddSequencedLeafResponse_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.google.trillian.proto.TrillianLogApiProto.internal_static_trillian_AddSequencedLeafResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.google.trillian.proto.AddSequencedLeafResponse.class, com.google.trillian.proto.AddSequencedLeafResponse.Builder.class);
    }

    // Construct using com.google.trillian.proto.AddSequencedLeafResponse.newBuilder()
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
      if (resultBuilder_ == null) {
        result_ = null;
      } else {
        result_ = null;
        resultBuilder_ = null;
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.google.trillian.proto.TrillianLogApiProto.internal_static_trillian_AddSequencedLeafResponse_descriptor;
    }

    public com.google.trillian.proto.AddSequencedLeafResponse getDefaultInstanceForType() {
      return com.google.trillian.proto.AddSequencedLeafResponse.getDefaultInstance();
    }

    public com.google.trillian.proto.AddSequencedLeafResponse build() {
      com.google.trillian.proto.AddSequencedLeafResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.google.trillian.proto.AddSequencedLeafResponse buildPartial() {
      com.google.trillian.proto.AddSequencedLeafResponse result = new com.google.trillian.proto.AddSequencedLeafResponse(this);
      if (resultBuilder_ == null) {
        result.result_ = result_;
      } else {
        result.result_ = resultBuilder_.build();
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
      if (other instanceof com.google.trillian.proto.AddSequencedLeafResponse) {
        return mergeFrom((com.google.trillian.proto.AddSequencedLeafResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.google.trillian.proto.AddSequencedLeafResponse other) {
      if (other == com.google.trillian.proto.AddSequencedLeafResponse.getDefaultInstance()) return this;
      if (other.hasResult()) {
        mergeResult(other.getResult());
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
      com.google.trillian.proto.AddSequencedLeafResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.google.trillian.proto.AddSequencedLeafResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private com.google.trillian.proto.QueuedLogLeaf result_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.trillian.proto.QueuedLogLeaf, com.google.trillian.proto.QueuedLogLeaf.Builder, com.google.trillian.proto.QueuedLogLeafOrBuilder> resultBuilder_;
    /**
     * <code>.trillian.QueuedLogLeaf result = 2;</code>
     */
    public boolean hasResult() {
      return resultBuilder_ != null || result_ != null;
    }
    /**
     * <code>.trillian.QueuedLogLeaf result = 2;</code>
     */
    public com.google.trillian.proto.QueuedLogLeaf getResult() {
      if (resultBuilder_ == null) {
        return result_ == null ? com.google.trillian.proto.QueuedLogLeaf.getDefaultInstance() : result_;
      } else {
        return resultBuilder_.getMessage();
      }
    }
    /**
     * <code>.trillian.QueuedLogLeaf result = 2;</code>
     */
    public Builder setResult(com.google.trillian.proto.QueuedLogLeaf value) {
      if (resultBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        result_ = value;
        onChanged();
      } else {
        resultBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.trillian.QueuedLogLeaf result = 2;</code>
     */
    public Builder setResult(
        com.google.trillian.proto.QueuedLogLeaf.Builder builderForValue) {
      if (resultBuilder_ == null) {
        result_ = builderForValue.build();
        onChanged();
      } else {
        resultBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.trillian.QueuedLogLeaf result = 2;</code>
     */
    public Builder mergeResult(com.google.trillian.proto.QueuedLogLeaf value) {
      if (resultBuilder_ == null) {
        if (result_ != null) {
          result_ =
            com.google.trillian.proto.QueuedLogLeaf.newBuilder(result_).mergeFrom(value).buildPartial();
        } else {
          result_ = value;
        }
        onChanged();
      } else {
        resultBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.trillian.QueuedLogLeaf result = 2;</code>
     */
    public Builder clearResult() {
      if (resultBuilder_ == null) {
        result_ = null;
        onChanged();
      } else {
        result_ = null;
        resultBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.trillian.QueuedLogLeaf result = 2;</code>
     */
    public com.google.trillian.proto.QueuedLogLeaf.Builder getResultBuilder() {
      
      onChanged();
      return getResultFieldBuilder().getBuilder();
    }
    /**
     * <code>.trillian.QueuedLogLeaf result = 2;</code>
     */
    public com.google.trillian.proto.QueuedLogLeafOrBuilder getResultOrBuilder() {
      if (resultBuilder_ != null) {
        return resultBuilder_.getMessageOrBuilder();
      } else {
        return result_ == null ?
            com.google.trillian.proto.QueuedLogLeaf.getDefaultInstance() : result_;
      }
    }
    /**
     * <code>.trillian.QueuedLogLeaf result = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.trillian.proto.QueuedLogLeaf, com.google.trillian.proto.QueuedLogLeaf.Builder, com.google.trillian.proto.QueuedLogLeafOrBuilder> 
        getResultFieldBuilder() {
      if (resultBuilder_ == null) {
        resultBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.google.trillian.proto.QueuedLogLeaf, com.google.trillian.proto.QueuedLogLeaf.Builder, com.google.trillian.proto.QueuedLogLeafOrBuilder>(
                getResult(),
                getParentForChildren(),
                isClean());
        result_ = null;
      }
      return resultBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:trillian.AddSequencedLeafResponse)
  }

  // @@protoc_insertion_point(class_scope:trillian.AddSequencedLeafResponse)
  private static final com.google.trillian.proto.AddSequencedLeafResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.google.trillian.proto.AddSequencedLeafResponse();
  }

  public static com.google.trillian.proto.AddSequencedLeafResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AddSequencedLeafResponse>
      PARSER = new com.google.protobuf.AbstractParser<AddSequencedLeafResponse>() {
    public AddSequencedLeafResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new AddSequencedLeafResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AddSequencedLeafResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AddSequencedLeafResponse> getParserForType() {
    return PARSER;
  }

  public com.google.trillian.proto.AddSequencedLeafResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

