// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: trillian_log_api.proto

package com.google.trillian.proto;

public interface QueueLeafRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:trillian.QueueLeafRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 log_id = 1;</code>
   */
  long getLogId();

  /**
   * <code>.trillian.LogLeaf leaf = 2;</code>
   */
  boolean hasLeaf();
  /**
   * <code>.trillian.LogLeaf leaf = 2;</code>
   */
  com.google.trillian.proto.LogLeaf getLeaf();
  /**
   * <code>.trillian.LogLeaf leaf = 2;</code>
   */
  com.google.trillian.proto.LogLeafOrBuilder getLeafOrBuilder();

  /**
   * <code>.trillian.ChargeTo charge_to = 3;</code>
   */
  boolean hasChargeTo();
  /**
   * <code>.trillian.ChargeTo charge_to = 3;</code>
   */
  com.google.trillian.proto.ChargeTo getChargeTo();
  /**
   * <code>.trillian.ChargeTo charge_to = 3;</code>
   */
  com.google.trillian.proto.ChargeToOrBuilder getChargeToOrBuilder();
}
