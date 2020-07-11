// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: trillian_log_api.proto

package com.google.trillian.proto;

public interface GetLatestSignedLogRootRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:trillian.GetLatestSignedLogRootRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 log_id = 1;</code>
   */
  long getLogId();

  /**
   * <code>.trillian.ChargeTo charge_to = 2;</code>
   */
  boolean hasChargeTo();
  /**
   * <code>.trillian.ChargeTo charge_to = 2;</code>
   */
  com.google.trillian.proto.ChargeTo getChargeTo();
  /**
   * <code>.trillian.ChargeTo charge_to = 2;</code>
   */
  com.google.trillian.proto.ChargeToOrBuilder getChargeToOrBuilder();

  /**
   * <pre>
   * If first_tree_size is non-zero, the response will include a consistency
   * proof between first_tree_size and the new tree size (if not smaller).
   * </pre>
   *
   * <code>int64 first_tree_size = 3;</code>
   */
  long getFirstTreeSize();
}
