// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: trillian.proto

package com.google.trillian.proto;

public final class TrillianProto {
  private TrillianProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_trillian_Tree_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_trillian_Tree_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_trillian_SignedEntryTimestamp_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_trillian_SignedEntryTimestamp_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_trillian_SignedLogRoot_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_trillian_SignedLogRoot_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_trillian_SignedMapRoot_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_trillian_SignedMapRoot_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_trillian_Proof_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_trillian_Proof_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\016trillian.proto\022\010trillian\032\032crypto/keysp" +
      "b/keyspb.proto\032\030crypto/sigpb/sigpb.proto" +
      "\032\031google/protobuf/any.proto\032\036google/prot" +
      "obuf/duration.proto\032\037google/protobuf/tim" +
      "estamp.proto\"\273\005\n\004Tree\022\017\n\007tree_id\030\001 \001(\003\022\'" +
      "\n\ntree_state\030\002 \001(\0162\023.trillian.TreeState\022" +
      "%\n\ttree_type\030\003 \001(\0162\022.trillian.TreeType\022-" +
      "\n\rhash_strategy\030\004 \001(\0162\026.trillian.HashStr" +
      "ategy\022<\n\016hash_algorithm\030\005 \001(\0162$.sigpb.Di" +
      "gitallySigned.HashAlgorithm\022F\n\023signature" +
      "_algorithm\030\006 \001(\0162).sigpb.DigitallySigned" +
      ".SignatureAlgorithm\022\024\n\014display_name\030\010 \001(" +
      "\t\022\023\n\013description\030\t \001(\t\022)\n\013private_key\030\014 " +
      "\001(\0132\024.google.protobuf.Any\022.\n\020storage_set" +
      "tings\030\r \001(\0132\024.google.protobuf.Any\022%\n\npub" +
      "lic_key\030\016 \001(\0132\021.keyspb.PublicKey\0224\n\021max_" +
      "root_duration\030\017 \001(\0132\031.google.protobuf.Du" +
      "ration\022/\n\013create_time\030\020 \001(\0132\032.google.pro" +
      "tobuf.Timestamp\022/\n\013update_time\030\021 \001(\0132\032.g" +
      "oogle.protobuf.Timestamp\022\017\n\007deleted\030\023 \001(" +
      "\010\022/\n\013delete_time\030\024 \001(\0132\032.google.protobuf" +
      ".TimestampJ\004\010\022\020\023J\004\010\007\020\010J\004\010\n\020\013J\004\010\013\020\014\"j\n\024Si" +
      "gnedEntryTimestamp\022\027\n\017timestamp_nanos\030\001 " +
      "\001(\003\022\016\n\006log_id\030\002 \001(\003\022)\n\tsignature\030\003 \001(\0132\026" +
      ".sigpb.DigitallySigned\"s\n\rSignedLogRoot\022" +
      "\020\n\010key_hint\030\007 \001(\014\022\020\n\010log_root\030\010 \001(\014\022\032\n\022l" +
      "og_root_signature\030\t \001(\014J\004\010\001\020\002J\004\010\002\020\003J\004\010\003\020" +
      "\004J\004\010\004\020\005J\004\010\005\020\006J\004\010\006\020\007\"^\n\rSignedMapRoot\022\020\n\010" +
      "map_root\030\t \001(\014\022\021\n\tsignature\030\004 \001(\014J\004\010\001\020\002J" +
      "\004\010\002\020\003J\004\010\003\020\004J\004\010\005\020\006J\004\010\006\020\007J\004\010\007\020\010J\004\010\010\020\t\"1\n\005P" +
      "roof\022\022\n\nleaf_index\030\001 \001(\003\022\016\n\006hashes\030\003 \003(\014" +
      "J\004\010\002\020\003*D\n\rLogRootFormat\022\033\n\027LOG_ROOT_FORM" +
      "AT_UNKNOWN\020\000\022\026\n\022LOG_ROOT_FORMAT_V1\020\001*D\n\r" +
      "MapRootFormat\022\033\n\027MAP_ROOT_FORMAT_UNKNOWN" +
      "\020\000\022\026\n\022MAP_ROOT_FORMAT_V1\020\001*\227\001\n\014HashStrat" +
      "egy\022\031\n\025UNKNOWN_HASH_STRATEGY\020\000\022\022\n\016RFC696" +
      "2_SHA256\020\001\022\023\n\017TEST_MAP_HASHER\020\002\022\031\n\025OBJEC" +
      "T_RFC6962_SHA256\020\003\022\025\n\021CONIKS_SHA512_256\020" +
      "\004\022\021\n\rCONIKS_SHA256\020\005*\213\001\n\tTreeState\022\026\n\022UN" +
      "KNOWN_TREE_STATE\020\000\022\n\n\006ACTIVE\020\001\022\n\n\006FROZEN" +
      "\020\002\022\037\n\027DEPRECATED_SOFT_DELETED\020\003\032\002\010\001\022\037\n\027D" +
      "EPRECATED_HARD_DELETED\020\004\032\002\010\001\022\014\n\010DRAINING" +
      "\020\005*G\n\010TreeType\022\025\n\021UNKNOWN_TREE_TYPE\020\000\022\007\n" +
      "\003LOG\020\001\022\007\n\003MAP\020\002\022\022\n\016PREORDERED_LOG\020\003BH\n\031c" +
      "om.google.trillian.protoB\rTrillianProtoP" +
      "\001Z\032github.com/google/trillianb\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          keyspb.Keyspb.getDescriptor(),
          sigpb.Sigpb.getDescriptor(),
          com.google.protobuf.AnyProto.getDescriptor(),
          com.google.protobuf.DurationProto.getDescriptor(),
          com.google.protobuf.TimestampProto.getDescriptor(),
        }, assigner);
    internal_static_trillian_Tree_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_trillian_Tree_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_trillian_Tree_descriptor,
        new java.lang.String[] { "TreeId", "TreeState", "TreeType", "HashStrategy", "HashAlgorithm", "SignatureAlgorithm", "DisplayName", "Description", "PrivateKey", "StorageSettings", "PublicKey", "MaxRootDuration", "CreateTime", "UpdateTime", "Deleted", "DeleteTime", });
    internal_static_trillian_SignedEntryTimestamp_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_trillian_SignedEntryTimestamp_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_trillian_SignedEntryTimestamp_descriptor,
        new java.lang.String[] { "TimestampNanos", "LogId", "Signature", });
    internal_static_trillian_SignedLogRoot_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_trillian_SignedLogRoot_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_trillian_SignedLogRoot_descriptor,
        new java.lang.String[] { "KeyHint", "LogRoot", "LogRootSignature", });
    internal_static_trillian_SignedMapRoot_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_trillian_SignedMapRoot_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_trillian_SignedMapRoot_descriptor,
        new java.lang.String[] { "MapRoot", "Signature", });
    internal_static_trillian_Proof_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_trillian_Proof_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_trillian_Proof_descriptor,
        new java.lang.String[] { "LeafIndex", "Hashes", });
    keyspb.Keyspb.getDescriptor();
    sigpb.Sigpb.getDescriptor();
    com.google.protobuf.AnyProto.getDescriptor();
    com.google.protobuf.DurationProto.getDescriptor();
    com.google.protobuf.TimestampProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}