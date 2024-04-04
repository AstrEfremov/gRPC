package org.example.grpc;

import hello.HelloServer;
import hello.HelloServiceGrpc;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class HelloGrpcService extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void hello(HelloServer.HelloRequest request,
                      StreamObserver<HelloServer.HelloResponse> responseObserver) {
        if (request.getName().isEmpty()) {
            responseObserver.onError(error());
        } else {
            responseObserver.onNext(next(request.getName()));
        }
        responseObserver.onCompleted();
    }

    private HelloServer.HelloResponse next(String name) {
        return HelloServer.HelloResponse.newBuilder()
                .setHello("Hello for " + name)
                .build();
    }

    private StatusRuntimeException error() {
        return Status.INVALID_ARGUMENT
                .withDescription("Name .")
                .asRuntimeException();
    }
}
