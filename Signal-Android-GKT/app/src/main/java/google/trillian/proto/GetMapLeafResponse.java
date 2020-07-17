// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: trillian_map_api.proto

package google.trillian.proto;

/**
 * Protobuf type {@code trillian.GetMapLeafResponse}
 */
public  final class GetMapLeafResponse extends
    com.google.protobuf.GeneratedMessageLite<
        GetMapLeafResponse, GetMapLeafResponse.Builder> implements
    // @@protoc_insertion_point(message_implements:trillian.GetMapLeafResponse)
    GetMapLeafResponseOrBuilder {
  private GetMapLeafResponse() {
  }
  public static final int MAP_LEAF_INCLUSION_FIELD_NUMBER = 1;
  private google.trillian.proto.MapLeafInclusion mapLeafInclusion_;
  /**
   * <code>.trillian.MapLeafInclusion map_leaf_inclusion = 1;</code>
   */
  @java.lang.Override
  public boolean hasMapLeafInclusion() {
    return mapLeafInclusion_ != null;
  }
  /**
   * <code>.trillian.MapLeafInclusion map_leaf_inclusion = 1;</code>
   */
  @java.lang.Override
  public google.trillian.proto.MapLeafInclusion getMapLeafInclusion() {
    return mapLeafInclusion_ == null ? google.trillian.proto.MapLeafInclusion.getDefaultInstance() : mapLeafInclusion_;
  }
  /**
   * <code>.trillian.MapLeafInclusion map_leaf_inclusion = 1;</code>
   */
  private void setMapLeafInclusion(google.trillian.proto.MapLeafInclusion value) {
    value.getClass();
  mapLeafInclusion_ = value;
    
    }
  /**
   * <code>.trillian.MapLeafInclusion map_leaf_inclusion = 1;</code>
   */
  @java.lang.SuppressWarnings({"ReferenceEquality"})
  private void mergeMapLeafInclusion(google.trillian.proto.MapLeafInclusion value) {
    value.getClass();
  if (mapLeafInclusion_ != null &&
        mapLeafInclusion_ != google.trillian.proto.MapLeafInclusion.getDefaultInstance()) {
      mapLeafInclusion_ =
        google.trillian.proto.MapLeafInclusion.newBuilder(mapLeafInclusion_).mergeFrom(value).buildPartial();
    } else {
      mapLeafInclusion_ = value;
    }
    
  }
  /**
   * <code>.trillian.MapLeafInclusion map_leaf_inclusion = 1;</code>
   */
  private void clearMapLeafInclusion() {  mapLeafInclusion_ = null;
    
  }

  public static final int MAP_ROOT_FIELD_NUMBER = 2;
  private google.trillian.proto.SignedMapRoot mapRoot_;
  /**
   * <code>.trillian.SignedMapRoot map_root = 2;</code>
   */
  @java.lang.Override
  public boolean hasMapRoot() {
    return mapRoot_ != null;
  }
  /**
   * <code>.trillian.SignedMapRoot map_root = 2;</code>
   */
  @java.lang.Override
  public google.trillian.proto.SignedMapRoot getMapRoot() {
    return mapRoot_ == null ? google.trillian.proto.SignedMapRoot.getDefaultInstance() : mapRoot_;
  }
  /**
   * <code>.trillian.SignedMapRoot map_root = 2;</code>
   */
  private void setMapRoot(google.trillian.proto.SignedMapRoot value) {
    value.getClass();
  mapRoot_ = value;
    
    }
  /**
   * <code>.trillian.SignedMapRoot map_root = 2;</code>
   */
  @java.lang.SuppressWarnings({"ReferenceEquality"})
  private void mergeMapRoot(google.trillian.proto.SignedMapRoot value) {
    value.getClass();
  if (mapRoot_ != null &&
        mapRoot_ != google.trillian.proto.SignedMapRoot.getDefaultInstance()) {
      mapRoot_ =
        google.trillian.proto.SignedMapRoot.newBuilder(mapRoot_).mergeFrom(value).buildPartial();
    } else {
      mapRoot_ = value;
    }
    
  }
  /**
   * <code>.trillian.SignedMapRoot map_root = 2;</code>
   */
  private void clearMapRoot() {  mapRoot_ = null;
    
  }

  public static google.trillian.proto.GetMapLeafResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static google.trillian.proto.GetMapLeafResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static google.trillian.proto.GetMapLeafResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static google.trillian.proto.GetMapLeafResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static google.trillian.proto.GetMapLeafResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static google.trillian.proto.GetMapLeafResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static google.trillian.proto.GetMapLeafResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input);
  }
  public static google.trillian.proto.GetMapLeafResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input, extensionRegistry);
  }
  public static google.trillian.proto.GetMapLeafResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return parseDelimitedFrom(DEFAULT_INSTANCE, input);
  }
  public static google.trillian.proto.GetMapLeafResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
  }
  public static google.trillian.proto.GetMapLeafResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input);
  }
  public static google.trillian.proto.GetMapLeafResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input, extensionRegistry);
  }

  public static Builder newBuilder() {
    return (Builder) DEFAULT_INSTANCE.createBuilder();
  }
  public static Builder newBuilder(google.trillian.proto.GetMapLeafResponse prototype) {
    return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
  }

  /**
   * Protobuf type {@code trillian.GetMapLeafResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageLite.Builder<
        google.trillian.proto.GetMapLeafResponse, Builder> implements
      // @@protoc_insertion_point(builder_implements:trillian.GetMapLeafResponse)
      google.trillian.proto.GetMapLeafResponseOrBuilder {
    // Construct using google.trillian.proto.GetMapLeafResponse.newBuilder()
    private Builder() {
      super(DEFAULT_INSTANCE);
    }


    /**
     * <code>.trillian.MapLeafInclusion map_leaf_inclusion = 1;</code>
     */
    @java.lang.Override
    public boolean hasMapLeafInclusion() {
      return instance.hasMapLeafInclusion();
    }
    /**
     * <code>.trillian.MapLeafInclusion map_leaf_inclusion = 1;</code>
     */
    @java.lang.Override
    public google.trillian.proto.MapLeafInclusion getMapLeafInclusion() {
      return instance.getMapLeafInclusion();
    }
    /**
     * <code>.trillian.MapLeafInclusion map_leaf_inclusion = 1;</code>
     */
    public Builder setMapLeafInclusion(google.trillian.proto.MapLeafInclusion value) {
      copyOnWrite();
      instance.setMapLeafInclusion(value);
      return this;
      }
    /**
     * <code>.trillian.MapLeafInclusion map_leaf_inclusion = 1;</code>
     */
    public Builder setMapLeafInclusion(
        google.trillian.proto.MapLeafInclusion.Builder builderForValue) {
      copyOnWrite();
      instance.setMapLeafInclusion(builderForValue.build());
      return this;
    }
    /**
     * <code>.trillian.MapLeafInclusion map_leaf_inclusion = 1;</code>
     */
    public Builder mergeMapLeafInclusion(google.trillian.proto.MapLeafInclusion value) {
      copyOnWrite();
      instance.mergeMapLeafInclusion(value);
      return this;
    }
    /**
     * <code>.trillian.MapLeafInclusion map_leaf_inclusion = 1;</code>
     */
    public Builder clearMapLeafInclusion() {  copyOnWrite();
      instance.clearMapLeafInclusion();
      return this;
    }

    /**
     * <code>.trillian.SignedMapRoot map_root = 2;</code>
     */
    @java.lang.Override
    public boolean hasMapRoot() {
      return instance.hasMapRoot();
    }
    /**
     * <code>.trillian.SignedMapRoot map_root = 2;</code>
     */
    @java.lang.Override
    public google.trillian.proto.SignedMapRoot getMapRoot() {
      return instance.getMapRoot();
    }
    /**
     * <code>.trillian.SignedMapRoot map_root = 2;</code>
     */
    public Builder setMapRoot(google.trillian.proto.SignedMapRoot value) {
      copyOnWrite();
      instance.setMapRoot(value);
      return this;
      }
    /**
     * <code>.trillian.SignedMapRoot map_root = 2;</code>
     */
    public Builder setMapRoot(
        google.trillian.proto.SignedMapRoot.Builder builderForValue) {
      copyOnWrite();
      instance.setMapRoot(builderForValue.build());
      return this;
    }
    /**
     * <code>.trillian.SignedMapRoot map_root = 2;</code>
     */
    public Builder mergeMapRoot(google.trillian.proto.SignedMapRoot value) {
      copyOnWrite();
      instance.mergeMapRoot(value);
      return this;
    }
    /**
     * <code>.trillian.SignedMapRoot map_root = 2;</code>
     */
    public Builder clearMapRoot() {  copyOnWrite();
      instance.clearMapRoot();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:trillian.GetMapLeafResponse)
  }
  @java.lang.Override
  @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
  protected final java.lang.Object dynamicMethod(
      com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
      java.lang.Object arg0, java.lang.Object arg1) {
    switch (method) {
      case NEW_MUTABLE_INSTANCE: {
        return new google.trillian.proto.GetMapLeafResponse();
      }
      case NEW_BUILDER: {
        return new Builder();
      }
      case BUILD_MESSAGE_INFO: {
          java.lang.Object[] objects = new java.lang.Object[] {
            "mapLeafInclusion_",
            "mapRoot_",
          };
          java.lang.String info =
              "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t";
          return newMessageInfo(DEFAULT_INSTANCE, info, objects);
      }
      // fall through
      case GET_DEFAULT_INSTANCE: {
        return DEFAULT_INSTANCE;
      }
      case GET_PARSER: {
        com.google.protobuf.Parser<google.trillian.proto.GetMapLeafResponse> parser = PARSER;
        if (parser == null) {
          synchronized (google.trillian.proto.GetMapLeafResponse.class) {
            parser = PARSER;
            if (parser == null) {
              parser =
                  new DefaultInstanceBasedParser<google.trillian.proto.GetMapLeafResponse>(
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


  // @@protoc_insertion_point(class_scope:trillian.GetMapLeafResponse)
  private static final google.trillian.proto.GetMapLeafResponse DEFAULT_INSTANCE;
  static {
    GetMapLeafResponse defaultInstance = new GetMapLeafResponse();
    // New instances are implicitly immutable so no need to make
    // immutable.
    DEFAULT_INSTANCE = defaultInstance;
    com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(
      GetMapLeafResponse.class, defaultInstance);
  }

  public static google.trillian.proto.GetMapLeafResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static volatile com.google.protobuf.Parser<GetMapLeafResponse> PARSER;

  public static com.google.protobuf.Parser<GetMapLeafResponse> parser() {
    return DEFAULT_INSTANCE.getParserForType();
  }
}

