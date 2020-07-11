// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: trillian_map_api.proto

package com.google.trillian.proto;

public interface MapLeafInclusionOrBuilder extends
    // @@protoc_insertion_point(interface_extends:trillian.MapLeafInclusion)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.trillian.MapLeaf leaf = 1;</code>
   */
  boolean hasLeaf();
  /**
   * <code>.trillian.MapLeaf leaf = 1;</code>
   */
  com.google.trillian.proto.MapLeaf getLeaf();
  /**
   * <code>.trillian.MapLeaf leaf = 1;</code>
   */
  com.google.trillian.proto.MapLeafOrBuilder getLeafOrBuilder();

  /**
   * <pre>
   * inclusion holds the inclusion proof for this leaf in the map root. It
   * holds one entry for each level of the tree; combining each of these in
   * turn with the leaf's hash (according to the tree's hash strategy)
   * reproduces the root hash.  A nil entry for a particular level indicates
   * that the node in question has an empty subtree beneath it (and so its
   * associated hash value is hasher.HashEmpty(index, height) rather than
   * hasher.HashChildren(l_hash, r_hash)).
   * </pre>
   *
   * <code>repeated bytes inclusion = 2;</code>
   */
  java.util.List<com.google.protobuf.ByteString> getInclusionList();
  /**
   * <pre>
   * inclusion holds the inclusion proof for this leaf in the map root. It
   * holds one entry for each level of the tree; combining each of these in
   * turn with the leaf's hash (according to the tree's hash strategy)
   * reproduces the root hash.  A nil entry for a particular level indicates
   * that the node in question has an empty subtree beneath it (and so its
   * associated hash value is hasher.HashEmpty(index, height) rather than
   * hasher.HashChildren(l_hash, r_hash)).
   * </pre>
   *
   * <code>repeated bytes inclusion = 2;</code>
   */
  int getInclusionCount();
  /**
   * <pre>
   * inclusion holds the inclusion proof for this leaf in the map root. It
   * holds one entry for each level of the tree; combining each of these in
   * turn with the leaf's hash (according to the tree's hash strategy)
   * reproduces the root hash.  A nil entry for a particular level indicates
   * that the node in question has an empty subtree beneath it (and so its
   * associated hash value is hasher.HashEmpty(index, height) rather than
   * hasher.HashChildren(l_hash, r_hash)).
   * </pre>
   *
   * <code>repeated bytes inclusion = 2;</code>
   */
  com.google.protobuf.ByteString getInclusion(int index);
}