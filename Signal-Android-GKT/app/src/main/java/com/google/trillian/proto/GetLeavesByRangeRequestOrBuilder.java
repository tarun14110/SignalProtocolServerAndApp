// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: trillian_log_api.proto

package com.google.trillian.proto;

public interface GetLeavesByRangeRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:trillian.GetLeavesByRangeRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 log_id = 1;</code>
   */
  long getLogId();

  /**
   * <code>int64 start_index = 2;</code>
   */
  long getStartIndex();

  /**
   * <code>int64 count = 3;</code>
   */
  long getCount();

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
