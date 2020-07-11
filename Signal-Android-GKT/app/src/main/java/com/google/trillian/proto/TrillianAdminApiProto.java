// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: trillian_admin_api.proto

package com.google.trillian.proto;

public final class TrillianAdminApiProto {
  private TrillianAdminApiProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_trillian_ListTreesRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_trillian_ListTreesRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_trillian_ListTreesResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_trillian_ListTreesResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_trillian_GetTreeRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_trillian_GetTreeRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_trillian_CreateTreeRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_trillian_CreateTreeRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_trillian_UpdateTreeRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_trillian_UpdateTreeRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_trillian_DeleteTreeRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_trillian_DeleteTreeRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_trillian_UndeleteTreeRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_trillian_UndeleteTreeRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\030trillian_admin_api.proto\022\010trillian\032\016tr" +
      "illian.proto\032\032crypto/keyspb/keyspb.proto" +
      "\032\034google/api/annotations.proto\032 google/p" +
      "rotobuf/field_mask.proto\"(\n\020ListTreesReq" +
      "uest\022\024\n\014show_deleted\030\001 \001(\010\"1\n\021ListTreesR" +
      "esponse\022\034\n\004tree\030\001 \003(\0132\016.trillian.Tree\"!\n" +
      "\016GetTreeRequest\022\017\n\007tree_id\030\001 \001(\003\"Z\n\021Crea" +
      "teTreeRequest\022\034\n\004tree\030\001 \001(\0132\016.trillian.T" +
      "ree\022\'\n\010key_spec\030\002 \001(\0132\025.keyspb.Specifica" +
      "tion\"b\n\021UpdateTreeRequest\022\034\n\004tree\030\001 \001(\0132" +
      "\016.trillian.Tree\022/\n\013update_mask\030\002 \001(\0132\032.g" +
      "oogle.protobuf.FieldMask\"$\n\021DeleteTreeRe" +
      "quest\022\017\n\007tree_id\030\001 \001(\003\"&\n\023UndeleteTreeRe" +
      "quest\022\017\n\007tree_id\030\001 \001(\0032\270\004\n\rTrillianAdmin" +
      "\022F\n\tListTrees\022\032.trillian.ListTreesReques" +
      "t\032\033.trillian.ListTreesResponse\"\000\022W\n\007GetT" +
      "ree\022\030.trillian.GetTreeRequest\032\016.trillian" +
      ".Tree\"\"\202\323\344\223\002\034\022\032/v1beta1/trees/{tree_id=*" +
      "}\022T\n\nCreateTree\022\033.trillian.CreateTreeReq" +
      "uest\032\016.trillian.Tree\"\031\202\323\344\223\002\023\"\016/v1beta1/t" +
      "rees:\001*\022e\n\nUpdateTree\022\033.trillian.UpdateT" +
      "reeRequest\032\016.trillian.Tree\"*\202\323\344\223\002$2\037/v1b" +
      "eta1/trees/{tree.tree_id=*}:\001*\022]\n\nDelete" +
      "Tree\022\033.trillian.DeleteTreeRequest\032\016.tril" +
      "lian.Tree\"\"\202\323\344\223\002\034*\032/v1beta1/trees/{tree_" +
      "id=*}\022j\n\014UndeleteTree\022\035.trillian.Undelet" +
      "eTreeRequest\032\016.trillian.Tree\"+\202\323\344\223\002%*#/v" +
      "1beta1/trees/{tree_id=*}:undeleteBP\n\031com" +
      ".google.trillian.protoB\025TrillianAdminApi" +
      "ProtoP\001Z\032github.com/google/trillianb\006pro" +
      "to3"
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
          com.google.trillian.proto.TrillianProto.getDescriptor(),
          keyspb.Keyspb.getDescriptor(),
          com.google.api.AnnotationsProto.getDescriptor(),
          com.google.protobuf.FieldMaskProto.getDescriptor(),
        }, assigner);
    internal_static_trillian_ListTreesRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_trillian_ListTreesRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_trillian_ListTreesRequest_descriptor,
        new java.lang.String[] { "ShowDeleted", });
    internal_static_trillian_ListTreesResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_trillian_ListTreesResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_trillian_ListTreesResponse_descriptor,
        new java.lang.String[] { "Tree", });
    internal_static_trillian_GetTreeRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_trillian_GetTreeRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_trillian_GetTreeRequest_descriptor,
        new java.lang.String[] { "TreeId", });
    internal_static_trillian_CreateTreeRequest_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_trillian_CreateTreeRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_trillian_CreateTreeRequest_descriptor,
        new java.lang.String[] { "Tree", "KeySpec", });
    internal_static_trillian_UpdateTreeRequest_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_trillian_UpdateTreeRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_trillian_UpdateTreeRequest_descriptor,
        new java.lang.String[] { "Tree", "UpdateMask", });
    internal_static_trillian_DeleteTreeRequest_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_trillian_DeleteTreeRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_trillian_DeleteTreeRequest_descriptor,
        new java.lang.String[] { "TreeId", });
    internal_static_trillian_UndeleteTreeRequest_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_trillian_UndeleteTreeRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_trillian_UndeleteTreeRequest_descriptor,
        new java.lang.String[] { "TreeId", });
    com.google.protobuf.ExtensionRegistry registry =
        com.google.protobuf.ExtensionRegistry.newInstance();
    registry.add(com.google.api.AnnotationsProto.http);
    com.google.protobuf.Descriptors.FileDescriptor
        .internalUpdateFileDescriptor(descriptor, registry);
    com.google.trillian.proto.TrillianProto.getDescriptor();
    keyspb.Keyspb.getDescriptor();
    com.google.api.AnnotationsProto.getDescriptor();
    com.google.protobuf.FieldMaskProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
