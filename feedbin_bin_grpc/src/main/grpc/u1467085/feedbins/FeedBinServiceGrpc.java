package u1467085.feedbins;

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
    comments = "Source: bin.proto")
public final class FeedBinServiceGrpc {

  private FeedBinServiceGrpc() {}

  public static final String SERVICE_NAME = "feedbins.FeedBinService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      u1467085.feedbins.BinStatusUpdate> getRegisterForUpdatesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "registerForUpdates",
      requestType = com.google.protobuf.Empty.class,
      responseType = u1467085.feedbins.BinStatusUpdate.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      u1467085.feedbins.BinStatusUpdate> getRegisterForUpdatesMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, u1467085.feedbins.BinStatusUpdate> getRegisterForUpdatesMethod;
    if ((getRegisterForUpdatesMethod = FeedBinServiceGrpc.getRegisterForUpdatesMethod) == null) {
      synchronized (FeedBinServiceGrpc.class) {
        if ((getRegisterForUpdatesMethod = FeedBinServiceGrpc.getRegisterForUpdatesMethod) == null) {
          FeedBinServiceGrpc.getRegisterForUpdatesMethod = getRegisterForUpdatesMethod = 
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, u1467085.feedbins.BinStatusUpdate>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "feedbins.FeedBinService", "registerForUpdates"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  u1467085.feedbins.BinStatusUpdate.getDefaultInstance()))
                  .setSchemaDescriptor(new FeedBinServiceMethodDescriptorSupplier("registerForUpdates"))
                  .build();
          }
        }
     }
     return getRegisterForUpdatesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      u1467085.feedbins.OperationStatusResponse> getFlushBinMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "flushBin",
      requestType = com.google.protobuf.Empty.class,
      responseType = u1467085.feedbins.OperationStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      u1467085.feedbins.OperationStatusResponse> getFlushBinMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, u1467085.feedbins.OperationStatusResponse> getFlushBinMethod;
    if ((getFlushBinMethod = FeedBinServiceGrpc.getFlushBinMethod) == null) {
      synchronized (FeedBinServiceGrpc.class) {
        if ((getFlushBinMethod = FeedBinServiceGrpc.getFlushBinMethod) == null) {
          FeedBinServiceGrpc.getFlushBinMethod = getFlushBinMethod = 
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, u1467085.feedbins.OperationStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "feedbins.FeedBinService", "flushBin"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  u1467085.feedbins.OperationStatusResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new FeedBinServiceMethodDescriptorSupplier("flushBin"))
                  .build();
          }
        }
     }
     return getFlushBinMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      u1467085.feedbins.BinStatusUpdate> getInspectBinMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "inspectBin",
      requestType = com.google.protobuf.Empty.class,
      responseType = u1467085.feedbins.BinStatusUpdate.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      u1467085.feedbins.BinStatusUpdate> getInspectBinMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, u1467085.feedbins.BinStatusUpdate> getInspectBinMethod;
    if ((getInspectBinMethod = FeedBinServiceGrpc.getInspectBinMethod) == null) {
      synchronized (FeedBinServiceGrpc.class) {
        if ((getInspectBinMethod = FeedBinServiceGrpc.getInspectBinMethod) == null) {
          FeedBinServiceGrpc.getInspectBinMethod = getInspectBinMethod = 
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, u1467085.feedbins.BinStatusUpdate>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "feedbins.FeedBinService", "inspectBin"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  u1467085.feedbins.BinStatusUpdate.getDefaultInstance()))
                  .setSchemaDescriptor(new FeedBinServiceMethodDescriptorSupplier("inspectBin"))
                  .build();
          }
        }
     }
     return getInspectBinMethod;
  }

  private static volatile io.grpc.MethodDescriptor<u1467085.feedbins.StuffAmount,
      u1467085.feedbins.OperationStatusResponse> getAddStuffMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "addStuff",
      requestType = u1467085.feedbins.StuffAmount.class,
      responseType = u1467085.feedbins.OperationStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<u1467085.feedbins.StuffAmount,
      u1467085.feedbins.OperationStatusResponse> getAddStuffMethod() {
    io.grpc.MethodDescriptor<u1467085.feedbins.StuffAmount, u1467085.feedbins.OperationStatusResponse> getAddStuffMethod;
    if ((getAddStuffMethod = FeedBinServiceGrpc.getAddStuffMethod) == null) {
      synchronized (FeedBinServiceGrpc.class) {
        if ((getAddStuffMethod = FeedBinServiceGrpc.getAddStuffMethod) == null) {
          FeedBinServiceGrpc.getAddStuffMethod = getAddStuffMethod = 
              io.grpc.MethodDescriptor.<u1467085.feedbins.StuffAmount, u1467085.feedbins.OperationStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "feedbins.FeedBinService", "addStuff"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  u1467085.feedbins.StuffAmount.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  u1467085.feedbins.OperationStatusResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new FeedBinServiceMethodDescriptorSupplier("addStuff"))
                  .build();
          }
        }
     }
     return getAddStuffMethod;
  }

  private static volatile io.grpc.MethodDescriptor<u1467085.feedbins.Stuff,
      u1467085.feedbins.OperationStatusResponse> getChangeStuffMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "changeStuff",
      requestType = u1467085.feedbins.Stuff.class,
      responseType = u1467085.feedbins.OperationStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<u1467085.feedbins.Stuff,
      u1467085.feedbins.OperationStatusResponse> getChangeStuffMethod() {
    io.grpc.MethodDescriptor<u1467085.feedbins.Stuff, u1467085.feedbins.OperationStatusResponse> getChangeStuffMethod;
    if ((getChangeStuffMethod = FeedBinServiceGrpc.getChangeStuffMethod) == null) {
      synchronized (FeedBinServiceGrpc.class) {
        if ((getChangeStuffMethod = FeedBinServiceGrpc.getChangeStuffMethod) == null) {
          FeedBinServiceGrpc.getChangeStuffMethod = getChangeStuffMethod = 
              io.grpc.MethodDescriptor.<u1467085.feedbins.Stuff, u1467085.feedbins.OperationStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "feedbins.FeedBinService", "changeStuff"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  u1467085.feedbins.Stuff.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  u1467085.feedbins.OperationStatusResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new FeedBinServiceMethodDescriptorSupplier("changeStuff"))
                  .build();
          }
        }
     }
     return getChangeStuffMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FeedBinServiceStub newStub(io.grpc.Channel channel) {
    return new FeedBinServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FeedBinServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new FeedBinServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FeedBinServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new FeedBinServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class FeedBinServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void registerForUpdates(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<u1467085.feedbins.BinStatusUpdate> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterForUpdatesMethod(), responseObserver);
    }

    /**
     */
    public void flushBin(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<u1467085.feedbins.OperationStatusResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getFlushBinMethod(), responseObserver);
    }

    /**
     */
    public void inspectBin(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<u1467085.feedbins.BinStatusUpdate> responseObserver) {
      asyncUnimplementedUnaryCall(getInspectBinMethod(), responseObserver);
    }

    /**
     */
    public void addStuff(u1467085.feedbins.StuffAmount request,
        io.grpc.stub.StreamObserver<u1467085.feedbins.OperationStatusResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddStuffMethod(), responseObserver);
    }

    /**
     */
    public void changeStuff(u1467085.feedbins.Stuff request,
        io.grpc.stub.StreamObserver<u1467085.feedbins.OperationStatusResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getChangeStuffMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRegisterForUpdatesMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                u1467085.feedbins.BinStatusUpdate>(
                  this, METHODID_REGISTER_FOR_UPDATES)))
          .addMethod(
            getFlushBinMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                u1467085.feedbins.OperationStatusResponse>(
                  this, METHODID_FLUSH_BIN)))
          .addMethod(
            getInspectBinMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                u1467085.feedbins.BinStatusUpdate>(
                  this, METHODID_INSPECT_BIN)))
          .addMethod(
            getAddStuffMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                u1467085.feedbins.StuffAmount,
                u1467085.feedbins.OperationStatusResponse>(
                  this, METHODID_ADD_STUFF)))
          .addMethod(
            getChangeStuffMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                u1467085.feedbins.Stuff,
                u1467085.feedbins.OperationStatusResponse>(
                  this, METHODID_CHANGE_STUFF)))
          .build();
    }
  }

  /**
   */
  public static final class FeedBinServiceStub extends io.grpc.stub.AbstractStub<FeedBinServiceStub> {
    private FeedBinServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FeedBinServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FeedBinServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FeedBinServiceStub(channel, callOptions);
    }

    /**
     */
    public void registerForUpdates(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<u1467085.feedbins.BinStatusUpdate> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getRegisterForUpdatesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void flushBin(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<u1467085.feedbins.OperationStatusResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFlushBinMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void inspectBin(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<u1467085.feedbins.BinStatusUpdate> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getInspectBinMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addStuff(u1467085.feedbins.StuffAmount request,
        io.grpc.stub.StreamObserver<u1467085.feedbins.OperationStatusResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddStuffMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void changeStuff(u1467085.feedbins.Stuff request,
        io.grpc.stub.StreamObserver<u1467085.feedbins.OperationStatusResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getChangeStuffMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class FeedBinServiceBlockingStub extends io.grpc.stub.AbstractStub<FeedBinServiceBlockingStub> {
    private FeedBinServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FeedBinServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FeedBinServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FeedBinServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<u1467085.feedbins.BinStatusUpdate> registerForUpdates(
        com.google.protobuf.Empty request) {
      return blockingServerStreamingCall(
          getChannel(), getRegisterForUpdatesMethod(), getCallOptions(), request);
    }

    /**
     */
    public u1467085.feedbins.OperationStatusResponse flushBin(com.google.protobuf.Empty request) {
      return blockingUnaryCall(
          getChannel(), getFlushBinMethod(), getCallOptions(), request);
    }

    /**
     */
    public u1467085.feedbins.BinStatusUpdate inspectBin(com.google.protobuf.Empty request) {
      return blockingUnaryCall(
          getChannel(), getInspectBinMethod(), getCallOptions(), request);
    }

    /**
     */
    public u1467085.feedbins.OperationStatusResponse addStuff(u1467085.feedbins.StuffAmount request) {
      return blockingUnaryCall(
          getChannel(), getAddStuffMethod(), getCallOptions(), request);
    }

    /**
     */
    public u1467085.feedbins.OperationStatusResponse changeStuff(u1467085.feedbins.Stuff request) {
      return blockingUnaryCall(
          getChannel(), getChangeStuffMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class FeedBinServiceFutureStub extends io.grpc.stub.AbstractStub<FeedBinServiceFutureStub> {
    private FeedBinServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FeedBinServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FeedBinServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FeedBinServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<u1467085.feedbins.OperationStatusResponse> flushBin(
        com.google.protobuf.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(getFlushBinMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<u1467085.feedbins.BinStatusUpdate> inspectBin(
        com.google.protobuf.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(getInspectBinMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<u1467085.feedbins.OperationStatusResponse> addStuff(
        u1467085.feedbins.StuffAmount request) {
      return futureUnaryCall(
          getChannel().newCall(getAddStuffMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<u1467085.feedbins.OperationStatusResponse> changeStuff(
        u1467085.feedbins.Stuff request) {
      return futureUnaryCall(
          getChannel().newCall(getChangeStuffMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER_FOR_UPDATES = 0;
  private static final int METHODID_FLUSH_BIN = 1;
  private static final int METHODID_INSPECT_BIN = 2;
  private static final int METHODID_ADD_STUFF = 3;
  private static final int METHODID_CHANGE_STUFF = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final FeedBinServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(FeedBinServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REGISTER_FOR_UPDATES:
          serviceImpl.registerForUpdates((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<u1467085.feedbins.BinStatusUpdate>) responseObserver);
          break;
        case METHODID_FLUSH_BIN:
          serviceImpl.flushBin((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<u1467085.feedbins.OperationStatusResponse>) responseObserver);
          break;
        case METHODID_INSPECT_BIN:
          serviceImpl.inspectBin((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<u1467085.feedbins.BinStatusUpdate>) responseObserver);
          break;
        case METHODID_ADD_STUFF:
          serviceImpl.addStuff((u1467085.feedbins.StuffAmount) request,
              (io.grpc.stub.StreamObserver<u1467085.feedbins.OperationStatusResponse>) responseObserver);
          break;
        case METHODID_CHANGE_STUFF:
          serviceImpl.changeStuff((u1467085.feedbins.Stuff) request,
              (io.grpc.stub.StreamObserver<u1467085.feedbins.OperationStatusResponse>) responseObserver);
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

  private static abstract class FeedBinServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FeedBinServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return u1467085.feedbins.FeedBinController.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FeedBinService");
    }
  }

  private static final class FeedBinServiceFileDescriptorSupplier
      extends FeedBinServiceBaseDescriptorSupplier {
    FeedBinServiceFileDescriptorSupplier() {}
  }

  private static final class FeedBinServiceMethodDescriptorSupplier
      extends FeedBinServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    FeedBinServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (FeedBinServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FeedBinServiceFileDescriptorSupplier())
              .addMethod(getRegisterForUpdatesMethod())
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
