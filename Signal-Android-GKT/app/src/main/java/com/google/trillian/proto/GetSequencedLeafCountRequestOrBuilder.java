// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: trillian_log_api.proto

package com.google.trillian.proto;

public interface GetSequencedLeafCountRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:trillian.GetSequencedLeafCountRequest)
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
}