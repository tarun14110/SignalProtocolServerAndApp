// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: trillian.proto

package com.google.trillian.proto;

/**
 * <pre>
 * MapRootFormat specifies the fields that are covered by the
 * SignedMapRoot signature, as well as their ordering and formats.
 * </pre>
 *
 * Protobuf enum {@code trillian.MapRootFormat}
 */
public enum MapRootFormat
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>MAP_ROOT_FORMAT_UNKNOWN = 0;</code>
   */
  MAP_ROOT_FORMAT_UNKNOWN(0),
  /**
   * <code>MAP_ROOT_FORMAT_V1 = 1;</code>
   */
  MAP_ROOT_FORMAT_V1(1),
  UNRECOGNIZED(-1),
  ;

  /**
   * <code>MAP_ROOT_FORMAT_UNKNOWN = 0;</code>
   */
  public static final int MAP_ROOT_FORMAT_UNKNOWN_VALUE = 0;
  /**
   * <code>MAP_ROOT_FORMAT_V1 = 1;</code>
   */
  public static final int MAP_ROOT_FORMAT_V1_VALUE = 1;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static MapRootFormat valueOf(int value) {
    return forNumber(value);
  }

  public static MapRootFormat forNumber(int value) {
    switch (value) {
      case 0: return MAP_ROOT_FORMAT_UNKNOWN;
      case 1: return MAP_ROOT_FORMAT_V1;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<MapRootFormat>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      MapRootFormat> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<MapRootFormat>() {
          public MapRootFormat findValueByNumber(int number) {
            return MapRootFormat.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return com.google.trillian.proto.TrillianProto.getDescriptor().getEnumTypes().get(1);
  }

  private static final MapRootFormat[] VALUES = values();

  public static MapRootFormat valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private MapRootFormat(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:trillian.MapRootFormat)
}

