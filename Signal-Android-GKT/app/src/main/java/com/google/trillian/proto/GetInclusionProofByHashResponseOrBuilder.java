// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: trillian_log_api.proto

package com.google.trillian.proto;

public interface GetInclusionProofByHashResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:trillian.GetInclusionProofByHashResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * Logs can potentially contain leaves with duplicate hashes so it's possible
   * for this to return multiple proofs.  If the leaf index for a particular
   * instance of the requested Merkle leaf hash is beyond the requested tree
   * size, the corresponding proof entry will be missing.
   * </pre>
   *
   * <code>repeated .trillian.Proof proof = 2;</code>
   */
  java.util.List<com.google.trillian.proto.Proof> 
      getProofList();
  /**
   * <pre>
   * Logs can potentially contain leaves with duplicate hashes so it's possible
   * for this to return multiple proofs.  If the leaf index for a particular
   * instance of the requested Merkle leaf hash is beyond the requested tree
   * size, the corresponding proof entry will be missing.
   * </pre>
   *
   * <code>repeated .trillian.Proof proof = 2;</code>
   */
  com.google.trillian.proto.Proof getProof(int index);
  /**
   * <pre>
   * Logs can potentially contain leaves with duplicate hashes so it's possible
   * for this to return multiple proofs.  If the leaf index for a particular
   * instance of the requested Merkle leaf hash is beyond the requested tree
   * size, the corresponding proof entry will be missing.
   * </pre>
   *
   * <code>repeated .trillian.Proof proof = 2;</code>
   */
  int getProofCount();
  /**
   * <pre>
   * Logs can potentially contain leaves with duplicate hashes so it's possible
   * for this to return multiple proofs.  If the leaf index for a particular
   * instance of the requested Merkle leaf hash is beyond the requested tree
   * size, the corresponding proof entry will be missing.
   * </pre>
   *
   * <code>repeated .trillian.Proof proof = 2;</code>
   */
  java.util.List<? extends com.google.trillian.proto.ProofOrBuilder> 
      getProofOrBuilderList();
  /**
   * <pre>
   * Logs can potentially contain leaves with duplicate hashes so it's possible
   * for this to return multiple proofs.  If the leaf index for a particular
   * instance of the requested Merkle leaf hash is beyond the requested tree
   * size, the corresponding proof entry will be missing.
   * </pre>
   *
   * <code>repeated .trillian.Proof proof = 2;</code>
   */
  com.google.trillian.proto.ProofOrBuilder getProofOrBuilder(
      int index);

  /**
   * <code>.trillian.SignedLogRoot signed_log_root = 3;</code>
   */
  boolean hasSignedLogRoot();
  /**
   * <code>.trillian.SignedLogRoot signed_log_root = 3;</code>
   */
  com.google.trillian.proto.SignedLogRoot getSignedLogRoot();
  /**
   * <code>.trillian.SignedLogRoot signed_log_root = 3;</code>
   */
  com.google.trillian.proto.SignedLogRootOrBuilder getSignedLogRootOrBuilder();
}
