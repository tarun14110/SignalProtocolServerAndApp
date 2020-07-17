// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: trillian_map_api.proto

package google.trillian.proto;

/**
 * Protobuf type {@code trillian.InitMapResponse}
 */
public  final class InitMapResponse extends
    com.google.protobuf.GeneratedMessageLite<
        InitMapResponse, InitMapResponse.Builder> implements
    // @@protoc_insertion_point(message_implements:trillian.InitMapResponse)
    InitMapResponseOrBuilder {
  private InitMapResponse() {
  }
  public static final int CREATED_FIELD_NUMBER = 1;
  private google.trillian.proto.SignedMapRoot created_;
  /**
   * <code>.trillian.SignedMapRoot created = 1;</code>
   */
  @java.lang.Override
  public boolean hasCreated() {
    return created_ != null;
  }
  /**
   * <code>.trillian.SignedMapRoot created = 1;</code>
   */
  @java.lang.Override
  public google.trillian.proto.SignedMapRoot getCreated() {
    return created_ == null ? google.trillian.proto.SignedMapRoot.getDefaultInstance() : created_;
  }
  /**
   * <code>.trillian.SignedMapRoot created = 1;</code>
   */
  private void setCreated(google.trillian.proto.SignedMapRoot value) {
    value.getClass();
  created_ = value;
    
    }
  /**
   * <code>.trillian.SignedMapRoot created = 1;</code>
   */
  @java.lang.SuppressWarnings({"ReferenceEquality"})
  private void mergeCreated(google.trillian.proto.SignedMapRoot value) {
    value.getClass();
  if (created_ != null &&
        created_ != google.trillian.proto.SignedMapRoot.getDefaultInstance()) {
      created_ =
        google.trillian.proto.SignedMapRoot.newBuilder(created_).mergeFrom(value).buildPartial();
    } else {
      created_ = value;
    }
    
  }
  /**
   * <code>.trillian.SignedMapRoot created = 1;</code>
   */
  private void clearCreated() {  created_ = null;
    
  }

  public static google.trillian.proto.InitMapResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static google.trillian.proto.InitMapResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static google.trillian.proto.InitMapResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static google.trillian.proto.InitMapResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static google.trillian.proto.InitMapResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static google.trillian.proto.InitMapResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static google.trillian.proto.InitMapResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input);
  }
  public static google.trillian.proto.InitMapResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input, extensionRegistry);
  }
  public static google.trillian.proto.InitMapResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return parseDelimitedFrom(DEFAULT_INSTANCE, input);
  }
  public static google.trillian.proto.InitMapResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
  }
  public static google.trillian.proto.InitMapResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input);
  }
  public static google.trillian.proto.InitMapResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input, extensionRegistry);
  }

  public static Builder newBuilder() {
    return (Builder) DEFAULT_INSTANCE.createBuilder();
  }
  public static Builder newBuilder(google.trillian.proto.InitMapResponse prototype) {
    return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
  }

  /**
   * Protobuf type {@code trillian.InitMapResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageLite.Builder<
        google.trillian.proto.InitMapResponse, Builder> implements
      // @@protoc_insertion_point(builder_implements:trillian.InitMapResponse)
      google.trillian.proto.InitMapResponseOrBuilder {
    // Construct using google.trillian.proto.InitMapResponse.newBuilder()
    private Builder() {
      super(DEFAULT_INSTANCE);
    }


    /**
     * <code>.trillian.SignedMapRoot created = 1;</code>
     */
    @java.lang.Override
    public boolean hasCreated() {
      return instance.hasCreated();
    }
    /**
     * <code>.trillian.SignedMapRoot created = 1;</code>
     */
    @java.lang.Override
    public google.trillian.proto.SignedMapRoot getCreated() {
      return instance.getCreated();
    }
    /**
     * <code>.trillian.SignedMapRoot created = 1;</code>
     */
    public Builder setCreated(google.trillian.proto.SignedMapRoot value) {
      copyOnWrite();
      instance.setCreated(value);
      return this;
      }
    /**
     * <code>.trillian.SignedMapRoot created = 1;</code>
     */
    public Builder setCreated(
        google.trillian.proto.SignedMapRoot.Builder builderForValue) {
      copyOnWrite();
      instance.setCreated(builderForValue.build());
      return this;
    }
    /**
     * <code>.trillian.SignedMapRoot created = 1;</code>
     */
    public Builder mergeCreated(google.trillian.proto.SignedMapRoot value) {
      copyOnWrite();
      instance.mergeCreated(value);
      return this;
    }
    /**
     * <code>.trillian.SignedMapRoot created = 1;</code>
     */
    public Builder clearCreated() {  copyOnWrite();
      instance.clearCreated();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:trillian.InitMapResponse)
  }
  @java.lang.Override
  @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
  protected final java.lang.Object dynamicMethod(
      com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
      java.lang.Object arg0, java.lang.Object arg1) {
    switch (method) {
      case NEW_MUTABLE_INSTANCE: {
        return new google.trillian.proto.InitMapResponse();
      }
      case NEW_BUILDER: {
        return new Builder();
      }
      case BUILD_MESSAGE_INFO: {
          java.lang.Object[] objects = new java.lang.Object[] {
            "created_",
          };
          java.lang.String info =
              "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\t";
          return newMessageInfo(DEFAULT_INSTANCE, info, objects);
      }
      // fall through
      case GET_DEFAULT_INSTANCE: {
        return DEFAULT_INSTANCE;
      }
      case GET_PARSER: {
        com.google.protobuf.Parser<google.trillian.proto.InitMapResponse> parser = PARSER;
        if (parser == null) {
          synchronized (google.trillian.proto.InitMapResponse.class) {
            parser = PARSER;
            if (parser == null) {
              parser =
                  new DefaultInstanceBasedParser<google.trillian.proto.InitMapResponse>(
                      DEFAULT_INSTANCE);
              PARSER = parser;
            }
          }
        }
        return parser;
    }
    case GET_MEMOIZED_IS_INITIALIZED: {
      return (byte) 1;
    }
    case SET_MEMOIZED_IS_INITIALIZED: {
      return null;
    }
    }
    throw new UnsupportedOperationException();
  }


  // @@protoc_insertion_point(class_scope:trillian.InitMapResponse)
  private static final google.trillian.proto.InitMapResponse DEFAULT_INSTANCE;
  static {
    InitMapResponse defaultInstance = new InitMapResponse();
    // New instances are implicitly immutable so no need to make
    // immutable.
    DEFAULT_INSTANCE = defaultInstance;
    com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(
      InitMapResponse.class, defaultInstance);
  }

  public static google.trillian.proto.InitMapResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static volatile com.google.protobuf.Parser<InitMapResponse> PARSER;

  public static com.google.protobuf.Parser<InitMapResponse> parser() {
    return DEFAULT_INSTANCE.getParserForType();
  }
}

