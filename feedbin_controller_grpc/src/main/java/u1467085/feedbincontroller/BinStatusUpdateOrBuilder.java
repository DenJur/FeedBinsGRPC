// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: controller.proto

package u1467085.feedbincontroller;

public interface BinStatusUpdateOrBuilder extends
    // @@protoc_insertion_point(interface_extends:feedbins.BinStatusUpdate)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.feedbins.BinRecord record = 1;</code>
   */
  boolean hasRecord();
  /**
   * <code>.feedbins.BinRecord record = 1;</code>
   */
  u1467085.feedbincontroller.BinRecord getRecord();
  /**
   * <code>.feedbins.BinRecord record = 1;</code>
   */
  u1467085.feedbincontroller.BinRecordOrBuilder getRecordOrBuilder();

  /**
   * <code>.feedbins.Stuff stuff = 2;</code>
   */
  boolean hasStuff();
  /**
   * <code>.feedbins.Stuff stuff = 2;</code>
   */
  u1467085.feedbincontroller.Stuff getStuff();
  /**
   * <code>.feedbins.Stuff stuff = 2;</code>
   */
  u1467085.feedbincontroller.StuffOrBuilder getStuffOrBuilder();

  /**
   * <code>int32 amount = 3;</code>
   */
  int getAmount();

  /**
   * <code>int32 maxAmount = 4;</code>
   */
  int getMaxAmount();
}