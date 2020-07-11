// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: trillian_admin_api.proto

package com.google.trillian.proto;

public interface CreateTreeRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:trillian.CreateTreeRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * Tree to be created. See Tree and CreateTree for more details.
   * </pre>
   *
   * <code>.trillian.Tree tree = 1;</code>
   */
  boolean hasTree();
  /**
   * <pre>
   * Tree to be created. See Tree and CreateTree for more details.
   * </pre>
   *
   * <code>.trillian.Tree tree = 1;</code>
   */
  com.google.trillian.proto.Tree getTree();
  /**
   * <pre>
   * Tree to be created. See Tree and CreateTree for more details.
   * </pre>
   *
   * <code>.trillian.Tree tree = 1;</code>
   */
  com.google.trillian.proto.TreeOrBuilder getTreeOrBuilder();

  /**
   * <pre>
   * Describes how the tree's private key should be generated.
   * Only needs to be set if tree.private_key is not set.
   * </pre>
   *
   * <code>.keyspb.Specification key_spec = 2;</code>
   */
  boolean hasKeySpec();
  /**
   * <pre>
   * Describes how the tree's private key should be generated.
   * Only needs to be set if tree.private_key is not set.
   * </pre>
   *
   * <code>.keyspb.Specification key_spec = 2;</code>
   */
  keyspb.Keyspb.Specification getKeySpec();
  /**
   * <pre>
   * Describes how the tree's private key should be generated.
   * Only needs to be set if tree.private_key is not set.
   * </pre>
   *
   * <code>.keyspb.Specification key_spec = 2;</code>
   */
  keyspb.Keyspb.SpecificationOrBuilder getKeySpecOrBuilder();
}