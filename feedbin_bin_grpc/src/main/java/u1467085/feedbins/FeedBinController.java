// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: bin.proto

package u1467085.feedbins;

public final class FeedBinController {
  private FeedBinController() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_feedbins_OperationStatusResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_feedbins_OperationStatusResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_feedbins_Stuff_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_feedbins_Stuff_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_feedbins_StuffAmount_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_feedbins_StuffAmount_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_feedbins_BinStatusUpdate_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_feedbins_BinStatusUpdate_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\tbin.proto\022\010feedbins\032\033google/protobuf/e" +
      "mpty.proto\"\227\001\n\027OperationStatusResponse\022A" +
      "\n\006result\030\001 \001(\01621.feedbins.OperationStatu" +
      "sResponse.OperationStatus\022\017\n\007message\030\002 \001" +
      "(\t\"(\n\017OperationStatus\022\013\n\007SUCCESS\020\000\022\010\n\004FA" +
      "IL\020\001\"\032\n\005Stuff\022\021\n\tstuffName\030\001 \001(\t\"\"\n\013Stuf" +
      "fAmount\022\023\n\013stuffAmount\030\001 \001(\005\"X\n\017BinStatu" +
      "sUpdate\022\036\n\005stuff\030\001 \001(\0132\017.feedbins.Stuff\022" +
      "%\n\006amount\030\002 \001(\0132\025.feedbins.StuffAmount2\366" +
      "\002\n\016FeedBinService\022K\n\022registerForUpdates\022" +
      "\026.google.protobuf.Empty\032\031.feedbins.BinSt" +
      "atusUpdate\"\0000\001\022G\n\010flushBin\022\026.google.prot" +
      "obuf.Empty\032!.feedbins.OperationStatusRes" +
      "ponse\"\000\022A\n\ninspectBin\022\026.google.protobuf." +
      "Empty\032\031.feedbins.BinStatusUpdate\"\000\022F\n\010ad" +
      "dStuff\022\025.feedbins.StuffAmount\032!.feedbins" +
      ".OperationStatusResponse\"\000\022C\n\013changeStuf" +
      "f\022\017.feedbins.Stuff\032!.feedbins.OperationS" +
      "tatusResponse\"\000B-\n\021u1467085.feedbinsB\021Fe" +
      "edBinControllerP\001\242\002\002FBb\006proto3"
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
          com.google.protobuf.EmptyProto.getDescriptor(),
        }, assigner);
    internal_static_feedbins_OperationStatusResponse_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_feedbins_OperationStatusResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_feedbins_OperationStatusResponse_descriptor,
        new java.lang.String[] { "Result", "Message", });
    internal_static_feedbins_Stuff_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_feedbins_Stuff_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_feedbins_Stuff_descriptor,
        new java.lang.String[] { "StuffName", });
    internal_static_feedbins_StuffAmount_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_feedbins_StuffAmount_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_feedbins_StuffAmount_descriptor,
        new java.lang.String[] { "StuffAmount", });
    internal_static_feedbins_BinStatusUpdate_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_feedbins_BinStatusUpdate_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_feedbins_BinStatusUpdate_descriptor,
        new java.lang.String[] { "Stuff", "Amount", });
    com.google.protobuf.EmptyProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
