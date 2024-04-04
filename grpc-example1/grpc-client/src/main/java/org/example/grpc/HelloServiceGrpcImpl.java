package org.example.grpc;

import hello.HelloServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceGrpcImpl {

    @GrpcClient("GLOBAL")
    HelloServiceGrpc.HelloServiceBlockingStub helloServiceBlockingStub;

    public String hello(String name) {
        System.out.println(helloServiceBlockingStub.hello(generatedHelloRequest(name)).getHello());
        return helloServiceBlockingStub.hello(generatedHelloRequest(name)).getHello();
    }

    private hello.HelloServiceOuterClass.HelloRequest generatedHelloRequest(String name) {
        return hello.HelloServiceOuterClass.HelloRequest.newBuilder()
                .setName(name)
                .build();
    }
}
