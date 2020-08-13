package google.keytransparency.v1;

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
 * <pre>
 * GetDirectory returns the information needed to verify the specified
 * directory.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.31.0)",
    comments = "Source: gkt.proto")
public final class KeyTransparencyGrpc {

  private KeyTransparencyGrpc() {}

  public static final String SERVICE_NAME = "google.keytransparency.v1.KeyTransparency";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<google.keytransparency.v1.Gkt.GetUserRequest,
      google.keytransparency.v1.Gkt.GetUserResponse> getGetUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetUser",
      requestType = google.keytransparency.v1.Gkt.GetUserRequest.class,
      responseType = google.keytransparency.v1.Gkt.GetUserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<google.keytransparency.v1.Gkt.GetUserRequest,
      google.keytransparency.v1.Gkt.GetUserResponse> getGetUserMethod() {
    io.grpc.MethodDescriptor<google.keytransparency.v1.Gkt.GetUserRequest, google.keytransparency.v1.Gkt.GetUserResponse> getGetUserMethod;
    if ((getGetUserMethod = KeyTransparencyGrpc.getGetUserMethod) == null) {
      synchronized (KeyTransparencyGrpc.class) {
        if ((getGetUserMethod = KeyTransparencyGrpc.getGetUserMethod) == null) {
          KeyTransparencyGrpc.getGetUserMethod = getGetUserMethod =
              io.grpc.MethodDescriptor.<google.keytransparency.v1.Gkt.GetUserRequest, google.keytransparency.v1.Gkt.GetUserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  google.keytransparency.v1.Gkt.GetUserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  google.keytransparency.v1.Gkt.GetUserResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getGetUserMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static KeyTransparencyStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KeyTransparencyStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KeyTransparencyStub>() {
        @java.lang.Override
        public KeyTransparencyStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KeyTransparencyStub(channel, callOptions);
        }
      };
    return KeyTransparencyStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static KeyTransparencyBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KeyTransparencyBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KeyTransparencyBlockingStub>() {
        @java.lang.Override
        public KeyTransparencyBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KeyTransparencyBlockingStub(channel, callOptions);
        }
      };
    return KeyTransparencyBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static KeyTransparencyFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KeyTransparencyFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KeyTransparencyFutureStub>() {
        @java.lang.Override
        public KeyTransparencyFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KeyTransparencyFutureStub(channel, callOptions);
        }
      };
    return KeyTransparencyFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * GetDirectory returns the information needed to verify the specified
   * directory.
   * </pre>
   */
  public static abstract class KeyTransparencyImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * GetUser returns a user's leaf entry in the Merkle Tree.
     * </pre>
     */
    public void getUser(google.keytransparency.v1.Gkt.GetUserRequest request,
        io.grpc.stub.StreamObserver<google.keytransparency.v1.Gkt.GetUserResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetUserMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetUserMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                google.keytransparency.v1.Gkt.GetUserRequest,
                google.keytransparency.v1.Gkt.GetUserResponse>(
                  this, METHODID_GET_USER)))
          .build();
    }
  }

  /**
   * <pre>
   * GetDirectory returns the information needed to verify the specified
   * directory.
   * </pre>
   */
  public static final class KeyTransparencyStub extends io.grpc.stub.AbstractAsyncStub<KeyTransparencyStub> {
    private KeyTransparencyStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KeyTransparencyStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KeyTransparencyStub(channel, callOptions);
    }

    /**
     * <pre>
     * GetUser returns a user's leaf entry in the Merkle Tree.
     * </pre>
     */
    public void getUser(google.keytransparency.v1.Gkt.GetUserRequest request,
        io.grpc.stub.StreamObserver<google.keytransparency.v1.Gkt.GetUserResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetUserMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * GetDirectory returns the information needed to verify the specified
   * directory.
   * </pre>
   */
  public static final class KeyTransparencyBlockingStub extends io.grpc.stub.AbstractBlockingStub<KeyTransparencyBlockingStub> {
    private KeyTransparencyBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KeyTransparencyBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KeyTransparencyBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * GetUser returns a user's leaf entry in the Merkle Tree.
     * </pre>
     */
    public google.keytransparency.v1.Gkt.GetUserResponse getUser(google.keytransparency.v1.Gkt.GetUserRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetUserMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * GetDirectory returns the information needed to verify the specified
   * directory.
   * </pre>
   */
  public static final class KeyTransparencyFutureStub extends io.grpc.stub.AbstractFutureStub<KeyTransparencyFutureStub> {
    private KeyTransparencyFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KeyTransparencyFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KeyTransparencyFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * GetUser returns a user's leaf entry in the Merkle Tree.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<google.keytransparency.v1.Gkt.GetUserResponse> getUser(
        google.keytransparency.v1.Gkt.GetUserRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetUserMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_USER = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final KeyTransparencyImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(KeyTransparencyImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_USER:
          serviceImpl.getUser((google.keytransparency.v1.Gkt.GetUserRequest) request,
              (io.grpc.stub.StreamObserver<google.keytransparency.v1.Gkt.GetUserResponse>) responseObserver);
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

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (KeyTransparencyGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .addMethod(getGetUserMethod())
              .build();
        }
      }
    }
    return result;
  }
}
