// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: trillian_log_api.proto

package com.google.trillian.proto;

public interface GetInclusionProofRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:trillian.GetInclusionProofRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 log_id = 1;</code>
   */
  long getLogId();

  /**
   * <code>int64 leaf_index = 2;</code>
   */
  long getLeafIndex();

  /**
   * <code>int64 tree_size = 3;</code>
   */
  long getTreeSize();

  /**
   * <code>.trillian.ChargeTo charge_to = 4;</code>
   */
  boolean hasChargeTo();
  /**
   * <code>.trillian.ChargeTo charge_to = 4;</code>
   */
  com.google.trillian.proto.ChargeTo getChargeTo();
  /**
   * <code>.trillian.ChargeTo charge_to = 4;</code>
   */
  com.google.trillian.proto.ChargeToOrBuilder getChargeToOrBuilder();
}