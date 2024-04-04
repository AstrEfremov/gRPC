package org.example.grpsService;

import hello.HelloServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
public class HelloServiceGrpcImpl {

    @GrpcClient("hello")
    HelloServiceGrpc.HelloServiceBlockingStub stub;


    public String hello1(String name) {
        return stub.hello(generatedHelloRequest(name)).getHello();
    }

    private hello.HelloServiceOuterClass.HelloRequest generatedHelloRequest(String name) {
        return hello.HelloServiceOuterClass.HelloRequest.newBuilder()
                .setName(name)
                .build();
    }
}
