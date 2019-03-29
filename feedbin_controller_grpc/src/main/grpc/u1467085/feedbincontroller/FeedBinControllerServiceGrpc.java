package u1467085.feedbincontroller;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.19.0)",
    comments = "Source: controller.proto")
public final class FeedBinControllerServiceGrpc {

  private FeedBinControllerServiceGrpc() {}

  public static final String SERVICE_NAME = "feedbins.FeedBinControllerService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      u1467085.feedbincontroller.BinStatusUpdate> getRegisterForUpdatesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "registerForUpdates",
      requestType = com.google.protobuf.Empty.class,
      responseType = u1467085.feedbincontroller.BinStatusUpdate.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      u1467085.feedbincontroller.BinStatusUpdate> getRegisterForUpdatesMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, u1467085.feedbincontroller.BinStatusUpdate> getRegisterForUpdatesMethod;
    if ((getRegisterForUpdatesMethod = FeedBinControllerServiceGrpc.getRegisterForUpdatesMethod) == null) {
      synchronized (FeedBinControllerServiceGrpc.class) {
        if ((getRegisterForUpdatesMethod = FeedBinControllerServiceGrpc.getRegisterForUpdatesMethod) == null) {
          FeedBinControllerServiceGrpc.getRegisterForUpdatesMethod = getRegisterForUpdatesMethod = 
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, u1467085.feedbincontroller.BinStatusUpdate>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "feedbins.FeedBinControllerService", "registerForUpdates"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  u1467085.feedbincontroller.BinStatusUpdate.getDefaultInstance()))
                  .setSchemaDescriptor(new FeedBinControllerServiceMethodDescriptorSupplier("registerForUpdates"))
                  .build();
          }
        }
     }
     return getRegisterForUpdatesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<u1467085.feedbincontroller.BinRecord,
      u1467085.feedbincontroller.OperationStatusResponse> getAddBinMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "addBin",
      requestType = u1467085.feedbincontroller.BinRecord.class,
      responseType = u1467085.feedbincontroller.OperationStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<u1467085.feedbincontroller.BinRecord,
      u1467085.feedbincontroller.OperationStatusResponse> getAddBinMethod() {
    io.grpc.MethodDescriptor<u1467085.feedbincontroller.BinRecord, u1467085.feedbincontroller.OperationStatusResponse> getAddBinMethod;
    if ((getAddBinMethod = FeedBinControllerServiceGrpc.getAddBinMethod) == null) {
      synchronized (FeedBinControllerServiceGrpc.class) {
        if ((getAddBinMethod = FeedBinControllerServiceGrpc.getAddBinMethod) == null) {
          FeedBinControllerServiceGrpc.getAddBinMethod = getAddBinMethod = 
              io.grpc.MethodDescriptor.<u1467085.feedbincontroller.BinRecord, u1467085.feedbincontroller.OperationStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "feedbins.FeedBinControllerService", "addBin"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  u1467085.feedbincontroller.BinRecord.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  u1467085.feedbincontroller.OperationStatusResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new FeedBinControllerServiceMethodDescriptorSupplier("addBin"))
                  .build();
          }
        }
     }
     return getAddBinMethod;
  }

  private static volatile io.grpc.MethodDescriptor<u1467085.feedbincontroller.ProductionLine,
      u1467085.feedbincontroller.OperationStatusResponse> getCreateProductionLineMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "createProductionLine",
      requestType = u1467085.feedbincontroller.ProductionLine.class,
      responseType = u1467085.feedbincontroller.OperationStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<u1467085.feedbincontroller.ProductionLine,
      u1467085.feedbincontroller.OperationStatusResponse> getCreateProductionLineMethod() {
    io.grpc.MethodDescriptor<u1467085.feedbincontroller.ProductionLine, u1467085.feedbincontroller.OperationStatusResponse> getCreateProductionLineMethod;
    if ((getCreateProductionLineMethod = FeedBinControllerServiceGrpc.getCreateProductionLineMethod) == null) {
      synchronized (FeedBinControllerServiceGrpc.class) {
        if ((getCreateProductionLineMethod = FeedBinControllerServiceGrpc.getCreateProductionLineMethod) == null) {
          FeedBinControllerServiceGrpc.getCreateProductionLineMethod = getCreateProductionLineMethod = 
              io.grpc.MethodDescriptor.<u1467085.feedbincontroller.ProductionLine, u1467085.feedbincontroller.OperationStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "feedbins.FeedBinControllerService", "createProductionLine"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  u1467085.feedbincontroller.ProductionLine.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  u1467085.feedbincontroller.OperationStatusResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new FeedBinControllerServiceMethodDescriptorSupplier("createProductionLine"))
                  .build();
          }
        }
     }
     return getCreateProductionLineMethod;
  }

  private static volatile io.grpc.MethodDescriptor<u1467085.feedbincontroller.BinId,
      u1467085.feedbincontroller.OperationStatusResponse> getFlushBinMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "flushBin",
      requestType = u1467085.feedbincontroller.BinId.class,
      responseType = u1467085.feedbincontroller.OperationStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<u1467085.feedbincontroller.BinId,
      u1467085.feedbincontroller.OperationStatusResponse> getFlushBinMethod() {
    io.grpc.MethodDescriptor<u1467085.feedbincontroller.BinId, u1467085.feedbincontroller.OperationStatusResponse> getFlushBinMethod;
    if ((getFlushBinMethod = FeedBinControllerServiceGrpc.getFlushBinMethod) == null) {
      synchronized (FeedBinControllerServiceGrpc.class) {
        if ((getFlushBinMethod = FeedBinControllerServiceGrpc.getFlushBinMethod) == null) {
          FeedBinControllerServiceGrpc.getFlushBinMethod = getFlushBinMethod = 
              io.grpc.MethodDescriptor.<u1467085.feedbincontroller.BinId, u1467085.feedbincontroller.OperationStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "feedbins.FeedBinControllerService", "flushBin"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  u1467085.feedbincontroller.BinId.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  u1467085.feedbincontroller.OperationStatusResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new FeedBinControllerServiceMethodDescriptorSupplier("flushBin"))
                  .build();
          }
        }
     }
     return getFlushBinMethod;
  }

  private static volatile io.grpc.MethodDescriptor<u1467085.feedbincontroller.BinId,
      u1467085.feedbincontroller.BinStatusUpdate> getInspectBinMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "inspectBin",
      requestType = u1467085.feedbincontroller.BinId.class,
      responseType = u1467085.feedbincontroller.BinStatusUpdate.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<u1467085.feedbincontroller.BinId,
      u1467085.feedbincontroller.BinStatusUpdate> getInspectBinMethod() {
    io.grpc.MethodDescriptor<u1467085.feedbincontroller.BinId, u1467085.feedbincontroller.BinStatusUpdate> getInspectBinMethod;
    if ((getInspectBinMethod = FeedBinControllerServiceGrpc.getInspectBinMethod) == null) {
      synchronized (FeedBinControllerServiceGrpc.class) {
        if ((getInspectBinMethod = FeedBinControllerServiceGrpc.getInspectBinMethod) == null) {
          FeedBinControllerServiceGrpc.getInspectBinMethod = getInspectBinMethod = 
              io.grpc.MethodDescriptor.<u1467085.feedbincontroller.BinId, u1467085.feedbincontroller.BinStatusUpdate>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "feedbins.FeedBinControllerService", "inspectBin"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  u1467085.feedbincontroller.BinId.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  u1467085.feedbincontroller.BinStatusUpdate.getDefaultInstance()))
                  .setSchemaDescriptor(new FeedBinControllerServiceMethodDescriptorSupplier("inspectBin"))
                  .build();
          }
        }
     }
     return getInspectBinMethod;
  }

  private static volatile io.grpc.MethodDescriptor<u1467085.feedbincontroller.FillBin,
      u1467085.feedbincontroller.OperationStatusResponse> getAddStuffMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "addStuff",
      requestType = u1467085.feedbincontroller.FillBin.class,
      responseType = u1467085.feedbincontroller.OperationStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<u1467085.feedbincontroller.FillBin,
      u1467085.feedbincontroller.OperationStatusResponse> getAddStuffMethod() {
    io.grpc.MethodDescriptor<u1467085.feedbincontroller.FillBin, u1467085.feedbincontroller.OperationStatusResponse> getAddStuffMethod;
    if ((getAddStuffMethod = FeedBinControllerServiceGrpc.getAddStuffMethod) == null) {
      synchronized (FeedBinControllerServiceGrpc.class) {
        if ((getAddStuffMethod = FeedBinControllerServiceGrpc.getAddStuffMethod) == null) {
          FeedBinControllerServiceGrpc.getAddStuffMethod = getAddStuffMethod = 
              io.grpc.MethodDescriptor.<u1467085.feedbincontroller.FillBin, u1467085.feedbincontroller.OperationStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "feedbins.FeedBinControllerService", "addStuff"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  u1467085.feedbincontroller.FillBin.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  u1467085.feedbincontroller.OperationStatusResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new FeedBinControllerServiceMethodDescriptorSupplier("addStuff"))
                  .build();
          }
        }
     }
     return getAddStuffMethod;
  }

  private static volatile io.grpc.MethodDescriptor<u1467085.feedbincontroller.ChangeBinStuff,
      u1467085.feedbincontroller.OperationStatusResponse> getChangeStuffMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "changeStuff",
      requestType = u1467085.feedbincontroller.ChangeBinStuff.class,
      responseType = u1467085.feedbincontroller.OperationStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<u1467085.feedbincontroller.ChangeBinStuff,
      u1467085.feedbincontroller.OperationStatusResponse> getChangeStuffMethod() {
    io.grpc.MethodDescriptor<u1467085.feedbincontroller.ChangeBinStuff, u1467085.feedbincontroller.OperationStatusResponse> getChangeStuffMethod;
    if ((getChangeStuffMethod = FeedBinControllerServiceGrpc.getChangeStuffMethod) == null) {
      synchronized (FeedBinControllerServiceGrpc.class) {
        if ((getChangeStuffMethod = FeedBinControllerServiceGrpc.getChangeStuffMethod) == null) {
          FeedBinControllerServiceGrpc.getChangeStuffMethod = getChangeStuffMethod = 
              io.grpc.MethodDescriptor.<u1467085.feedbincontroller.ChangeBinStuff, u1467085.feedbincontroller.OperationStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "feedbins.FeedBinControllerService", "changeStuff"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  u1467085.feedbincontroller.ChangeBinStuff.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  u1467085.feedbincontroller.OperationStatusResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new FeedBinControllerServiceMethodDescriptorSupplier("changeStuff"))
                  .build();
          }
        }
     }
     return getChangeStuffMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FeedBinControllerServiceStub newStub(io.grpc.Channel channel) {
    return new FeedBinControllerServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FeedBinControllerServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new FeedBinControllerServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FeedBinControllerServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new FeedBinControllerServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class FeedBinControllerServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void registerForUpdates(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<u1467085.feedbincontroller.BinStatusUpdate> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterForUpdatesMethod(), responseObserver);
    }

    /**
     */
    public void addBin(u1467085.feedbincontroller.BinRecord request,
        io.grpc.stub.StreamObserver<u1467085.feedbincontroller.OperationStatusResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddBinMethod(), responseObserver);
    }

    /**
     */
    public void createProductionLine(u1467085.feedbincontroller.ProductionLine request,
        io.grpc.stub.StreamObserver<u1467085.feedbincontroller.OperationStatusResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateProductionLineMethod(), responseObserver);
    }

    /**
     */
    public void flushBin(u1467085.feedbincontroller.BinId request,
        io.grpc.stub.StreamObserver<u1467085.feedbincontroller.OperationStatusResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getFlushBinMethod(), responseObserver);
    }

    /**
     */
    public void inspectBin(u1467085.feedbincontroller.BinId request,
        io.grpc.stub.StreamObserver<u1467085.feedbincontroller.BinStatusUpdate> responseObserver) {
      asyncUnimplementedUnaryCall(getInspectBinMethod(), responseObserver);
    }

    /**
     */
    public void addStuff(u1467085.feedbincontroller.FillBin request,
        io.grpc.stub.StreamObserver<u1467085.feedbincontroller.OperationStatusResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddStuffMethod(), responseObserver);
    }

    /**
     */
    public void changeStuff(u1467085.feedbincontroller.ChangeBinStuff request,
        io.grpc.stub.StreamObserver<u1467085.feedbincontroller.OperationStatusResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getChangeStuffMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRegisterForUpdatesMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                u1467085.feedbincontroller.BinStatusUpdate>(
                  this, METHODID_REGISTER_FOR_UPDATES)))
          .addMethod(
            getAddBinMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                u1467085.feedbincontroller.BinRecord,
                u1467085.feedbincontroller.OperationStatusResponse>(
                  this, METHODID_ADD_BIN)))
          .addMethod(
            getCreateProductionLineMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                u1467085.feedbincontroller.ProductionLine,
                u1467085.feedbincontroller.OperationStatusResponse>(
                  this, METHODID_CREATE_PRODUCTION_LINE)))
          .addMethod(
            getFlushBinMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                u1467085.feedbincontroller.BinId,
                u1467085.feedbincontroller.OperationStatusResponse>(
                  this, METHODID_FLUSH_BIN)))
          .addMethod(
            getInspectBinMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                u1467085.feedbincontroller.BinId,
                u1467085.feedbincontroller.BinStatusUpdate>(
                  this, METHODID_INSPECT_BIN)))
          .addMethod(
            getAddStuffMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                u1467085.feedbincontroller.FillBin,
                u1467085.feedbincontroller.OperationStatusResponse>(
                  this, METHODID_ADD_STUFF)))
          .addMethod(
            getChangeStuffMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                u1467085.feedbincontroller.ChangeBinStuff,
                u1467085.feedbincontroller.OperationStatusResponse>(
                  this, METHODID_CHANGE_STUFF)))
          .build();
    }
  }

  /**
   */
  public static final class FeedBinControllerServiceStub extends io.grpc.stub.AbstractStub<FeedBinControllerServiceStub> {
    private FeedBinControllerServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FeedBinControllerServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FeedBinControllerServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FeedBinControllerServiceStub(channel, callOptions);
    }

    /**
     */
    public void registerForUpdates(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<u1467085.feedbincontroller.BinStatusUpdate> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getRegisterForUpdatesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addBin(u1467085.feedbincontroller.BinRecord request,
        io.grpc.stub.StreamObserver<u1467085.feedbincontroller.OperationStatusResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddBinMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createProductionLine(u1467085.feedbincontroller.ProductionLine request,
        io.grpc.stub.StreamObserver<u1467085.feedbincontroller.OperationStatusResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateProductionLineMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void flushBin(u1467085.feedbincontroller.BinId request,
        io.grpc.stub.StreamObserver<u1467085.feedbincontroller.OperationStatusResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFlushBinMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void inspectBin(u1467085.feedbincontroller.BinId request,
        io.grpc.stub.StreamObserver<u1467085.feedbincontroller.BinStatusUpdate> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getInspectBinMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addStuff(u1467085.feedbincontroller.FillBin request,
        io.grpc.stub.StreamObserver<u1467085.feedbincontroller.OperationStatusResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddStuffMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void changeStuff(u1467085.feedbincontroller.ChangeBinStuff request,
        io.grpc.stub.StreamObserver<u1467085.feedbincontroller.OperationStatusResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getChangeStuffMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class FeedBinControllerServiceBlockingStub extends io.grpc.stub.AbstractStub<FeedBinControllerServiceBlockingStub> {
    private FeedBinControllerServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FeedBinControllerServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FeedBinControllerServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FeedBinControllerServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<u1467085.feedbincontroller.BinStatusUpdate> registerForUpdates(
        com.google.protobuf.Empty request) {
      return blockingServerStreamingCall(
          getChannel(), getRegisterForUpdatesMethod(), getCallOptions(), request);
    }

    /**
     */
    public u1467085.feedbincontroller.OperationStatusResponse addBin(u1467085.feedbincontroller.BinRecord request) {
      return blockingUnaryCall(
          getChannel(), getAddBinMethod(), getCallOptions(), request);
    }

    /**
     */
    public u1467085.feedbincontroller.OperationStatusResponse createProductionLine(u1467085.feedbincontroller.ProductionLine request) {
      return blockingUnaryCall(
          getChannel(), getCreateProductionLineMethod(), getCallOptions(), request);
    }

    /**
     */
    public u1467085.feedbincontroller.OperationStatusResponse flushBin(u1467085.feedbincontroller.BinId request) {
      return blockingUnaryCall(
          getChannel(), getFlushBinMethod(), getCallOptions(), request);
    }

    /**
     */
    public u1467085.feedbincontroller.BinStatusUpdate inspectBin(u1467085.feedbincontroller.BinId request) {
      return blockingUnaryCall(
          getChannel(), getInspectBinMethod(), getCallOptions(), request);
    }

    /**
     */
    public u1467085.feedbincontroller.OperationStatusResponse addStuff(u1467085.feedbincontroller.FillBin request) {
      return blockingUnaryCall(
          getChannel(), getAddStuffMethod(), getCallOptions(), request);
    }

    /**
     */
    public u1467085.feedbincontroller.OperationStatusResponse changeStuff(u1467085.feedbincontroller.ChangeBinStuff request) {
      return blockingUnaryCall(
          getChannel(), getChangeStuffMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class FeedBinControllerServiceFutureStub extends io.grpc.stub.AbstractStub<FeedBinControllerServiceFutureStub> {
    private FeedBinControllerServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FeedBinControllerServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FeedBinControllerServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FeedBinControllerServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<u1467085.feedbincontroller.OperationStatusResponse> addBin(
        u1467085.feedbincontroller.BinRecord request) {
      return futureUnaryCall(
          getChannel().newCall(getAddBinMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<u1467085.feedbincontroller.OperationStatusResponse> createProductionLine(
        u1467085.feedbincontroller.ProductionLine request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateProductionLineMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<u1467085.feedbincontroller.OperationStatusResponse> flushBin(
        u1467085.feedbincontroller.BinId request) {
      return futureUnaryCall(
          getChannel().newCall(getFlushBinMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<u1467085.feedbincontroller.BinStatusUpdate> inspectBin(
        u1467085.feedbincontroller.BinId request) {
      return futureUnaryCall(
          getChannel().newCall(getInspectBinMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<u1467085.feedbincontroller.OperationStatusResponse> addStuff(
        u1467085.feedbincontroller.FillBin request) {
      return futureUnaryCall(
          getChannel().newCall(getAddStuffMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<u1467085.feedbincontroller.OperationStatusResponse> changeStuff(
        u1467085.feedbincontroller.ChangeBinStuff request) {
      return futureUnaryCall(
          getChannel().newCall(getChangeStuffMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER_FOR_UPDATES = 0;
  private static final int METHODID_ADD_BIN = 1;
  private static final int METHODID_CREATE_PRODUCTION_LINE = 2;
  private static final int METHODID_FLUSH_BIN = 3;
  private static final int METHODID_INSPECT_BIN = 4;
  private static final int METHODID_ADD_STUFF = 5;
  private static final int METHODID_CHANGE_STUFF = 6;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final FeedBinControllerServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(FeedBinControllerServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REGISTER_FOR_UPDATES:
          serviceImpl.registerForUpdates((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<u1467085.feedbincontroller.BinStatusUpdate>) responseObserver);
          break;
        case METHODID_ADD_BIN:
          serviceImpl.addBin((u1467085.feedbincontroller.BinRecord) request,
              (io.grpc.stub.StreamObserver<u1467085.feedbincontroller.OperationStatusResponse>) responseObserver);
          break;
        case METHODID_CREATE_PRODUCTION_LINE:
          serviceImpl.createProductionLine((u1467085.feedbincontroller.ProductionLine) request,
              (io.grpc.stub.StreamObserver<u1467085.feedbincontroller.OperationStatusResponse>) responseObserver);
          break;
        case METHODID_FLUSH_BIN:
          serviceImpl.flushBin((u1467085.feedbincontroller.BinId) request,
              (io.grpc.stub.StreamObserver<u1467085.feedbincontroller.OperationStatusResponse>) responseObserver);
          break;
        case METHODID_INSPECT_BIN:
          serviceImpl.inspectBin((u1467085.feedbincontroller.BinId) request,
              (io.grpc.stub.StreamObserver<u1467085.feedbincontroller.BinStatusUpdate>) responseObserver);
          break;
        case METHODID_ADD_STUFF:
          serviceImpl.addStuff((u1467085.feedbincontroller.FillBin) request,
              (io.grpc.stub.StreamObserver<u1467085.feedbincontroller.OperationStatusResponse>) responseObserver);
          break;
        case METHODID_CHANGE_STUFF:
          serviceImpl.changeStuff((u1467085.feedbincontroller.ChangeBinStuff) request,
              (io.grpc.stub.StreamObserver<u1467085.feedbincontroller.OperationStatusResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class FeedBinControllerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FeedBinControllerServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return u1467085.feedbincontroller.FeedBinController.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FeedBinControllerService");
    }
  }

  private static final class FeedBinControllerServiceFileDescriptorSupplier
      extends FeedBinControllerServiceBaseDescriptorSupplier {
    FeedBinControllerServiceFileDescriptorSupplier() {}
  }

  private static final class FeedBinControllerServiceMethodDescriptorSupplier
      extends FeedBinControllerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    FeedBinControllerServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (FeedBinControllerServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FeedBinControllerServiceFileDescriptorSupplier())
              .addMethod(getRegisterForUpdatesMethod())
              .addMethod(getAddBinMethod())
              .addMethod(getCreateProductionLineMethod())
              .addMethod(getFlushBinMethod())
              .addMethod(getInspectBinMethod())
              .addMethod(getAddStuffMethod())
              .addMethod(getChangeStuffMethod())
              .build();
        }
      }
    }
    return result;
  }
}
