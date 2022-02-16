package com.rpc;

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
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: Msg.proto")
public final class MsgServiceGrpc {

  private MsgServiceGrpc() {}

  public static final String SERVICE_NAME = "MsgService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.rpc.Msg.User,
      com.rpc.Msg.ChatMsgFromServer> getCreateUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "createUser",
      requestType = com.rpc.Msg.User.class,
      responseType = com.rpc.Msg.ChatMsgFromServer.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.rpc.Msg.User,
      com.rpc.Msg.ChatMsgFromServer> getCreateUserMethod() {
    io.grpc.MethodDescriptor<com.rpc.Msg.User, com.rpc.Msg.ChatMsgFromServer> getCreateUserMethod;
    if ((getCreateUserMethod = MsgServiceGrpc.getCreateUserMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getCreateUserMethod = MsgServiceGrpc.getCreateUserMethod) == null) {
          MsgServiceGrpc.getCreateUserMethod = getCreateUserMethod = 
              io.grpc.MethodDescriptor.<com.rpc.Msg.User, com.rpc.Msg.ChatMsgFromServer>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "MsgService", "createUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.rpc.Msg.User.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.rpc.Msg.ChatMsgFromServer.getDefaultInstance()))
                  .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("createUser"))
                  .build();
          }
        }
     }
     return getCreateUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.rpc.Msg.ChatMsg,
      com.rpc.Msg.ChatMsgFromServer> getSendMsgMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sendMsg",
      requestType = com.rpc.Msg.ChatMsg.class,
      responseType = com.rpc.Msg.ChatMsgFromServer.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.rpc.Msg.ChatMsg,
      com.rpc.Msg.ChatMsgFromServer> getSendMsgMethod() {
    io.grpc.MethodDescriptor<com.rpc.Msg.ChatMsg, com.rpc.Msg.ChatMsgFromServer> getSendMsgMethod;
    if ((getSendMsgMethod = MsgServiceGrpc.getSendMsgMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getSendMsgMethod = MsgServiceGrpc.getSendMsgMethod) == null) {
          MsgServiceGrpc.getSendMsgMethod = getSendMsgMethod = 
              io.grpc.MethodDescriptor.<com.rpc.Msg.ChatMsg, com.rpc.Msg.ChatMsgFromServer>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "MsgService", "sendMsg"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.rpc.Msg.ChatMsg.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.rpc.Msg.ChatMsgFromServer.getDefaultInstance()))
                  .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("sendMsg"))
                  .build();
          }
        }
     }
     return getSendMsgMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.rpc.Msg.User,
      com.rpc.Msg.UnreadMsg> getGetUnreadMsgMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getUnreadMsg",
      requestType = com.rpc.Msg.User.class,
      responseType = com.rpc.Msg.UnreadMsg.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.rpc.Msg.User,
      com.rpc.Msg.UnreadMsg> getGetUnreadMsgMethod() {
    io.grpc.MethodDescriptor<com.rpc.Msg.User, com.rpc.Msg.UnreadMsg> getGetUnreadMsgMethod;
    if ((getGetUnreadMsgMethod = MsgServiceGrpc.getGetUnreadMsgMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getGetUnreadMsgMethod = MsgServiceGrpc.getGetUnreadMsgMethod) == null) {
          MsgServiceGrpc.getGetUnreadMsgMethod = getGetUnreadMsgMethod = 
              io.grpc.MethodDescriptor.<com.rpc.Msg.User, com.rpc.Msg.UnreadMsg>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "MsgService", "getUnreadMsg"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.rpc.Msg.User.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.rpc.Msg.UnreadMsg.getDefaultInstance()))
                  .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("getUnreadMsg"))
                  .build();
          }
        }
     }
     return getGetUnreadMsgMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MsgServiceStub newStub(io.grpc.Channel channel) {
    return new MsgServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MsgServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new MsgServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MsgServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new MsgServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class MsgServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * assign a user
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.rpc.Msg.User> createUser(
        io.grpc.stub.StreamObserver<com.rpc.Msg.ChatMsgFromServer> responseObserver) {
      return asyncUnimplementedStreamingCall(getCreateUserMethod(), responseObserver);
    }

    /**
     * <pre>
     * send message
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.rpc.Msg.ChatMsg> sendMsg(
        io.grpc.stub.StreamObserver<com.rpc.Msg.ChatMsgFromServer> responseObserver) {
      return asyncUnimplementedStreamingCall(getSendMsgMethod(), responseObserver);
    }

    /**
     * <pre>
     * get all unread message
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.rpc.Msg.User> getUnreadMsg(
        io.grpc.stub.StreamObserver<com.rpc.Msg.UnreadMsg> responseObserver) {
      return asyncUnimplementedStreamingCall(getGetUnreadMsgMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateUserMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.rpc.Msg.User,
                com.rpc.Msg.ChatMsgFromServer>(
                  this, METHODID_CREATE_USER)))
          .addMethod(
            getSendMsgMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.rpc.Msg.ChatMsg,
                com.rpc.Msg.ChatMsgFromServer>(
                  this, METHODID_SEND_MSG)))
          .addMethod(
            getGetUnreadMsgMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.rpc.Msg.User,
                com.rpc.Msg.UnreadMsg>(
                  this, METHODID_GET_UNREAD_MSG)))
          .build();
    }
  }

  /**
   */
  public static final class MsgServiceStub extends io.grpc.stub.AbstractStub<MsgServiceStub> {
    private MsgServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MsgServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MsgServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * assign a user
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.rpc.Msg.User> createUser(
        io.grpc.stub.StreamObserver<com.rpc.Msg.ChatMsgFromServer> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getCreateUserMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     * send message
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.rpc.Msg.ChatMsg> sendMsg(
        io.grpc.stub.StreamObserver<com.rpc.Msg.ChatMsgFromServer> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getSendMsgMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     * get all unread message
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.rpc.Msg.User> getUnreadMsg(
        io.grpc.stub.StreamObserver<com.rpc.Msg.UnreadMsg> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getGetUnreadMsgMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class MsgServiceBlockingStub extends io.grpc.stub.AbstractStub<MsgServiceBlockingStub> {
    private MsgServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MsgServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MsgServiceBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class MsgServiceFutureStub extends io.grpc.stub.AbstractStub<MsgServiceFutureStub> {
    private MsgServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MsgServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MsgServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_CREATE_USER = 0;
  private static final int METHODID_SEND_MSG = 1;
  private static final int METHODID_GET_UNREAD_MSG = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MsgServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MsgServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_USER:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.createUser(
              (io.grpc.stub.StreamObserver<com.rpc.Msg.ChatMsgFromServer>) responseObserver);
        case METHODID_SEND_MSG:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.sendMsg(
              (io.grpc.stub.StreamObserver<com.rpc.Msg.ChatMsgFromServer>) responseObserver);
        case METHODID_GET_UNREAD_MSG:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.getUnreadMsg(
              (io.grpc.stub.StreamObserver<com.rpc.Msg.UnreadMsg>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class MsgServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.rpc.Msg.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MsgService");
    }
  }

  private static final class MsgServiceFileDescriptorSupplier
      extends MsgServiceBaseDescriptorSupplier {
    MsgServiceFileDescriptorSupplier() {}
  }

  private static final class MsgServiceMethodDescriptorSupplier
      extends MsgServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MsgServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (MsgServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MsgServiceFileDescriptorSupplier())
              .addMethod(getCreateUserMethod())
              .addMethod(getSendMsgMethod())
              .addMethod(getGetUnreadMsgMethod())
              .build();
        }
      }
    }
    return result;
  }
}
