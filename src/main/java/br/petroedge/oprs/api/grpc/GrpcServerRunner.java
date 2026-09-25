package br.petroedge.oprs.api.grpc;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import jakarta.annotation.PreDestroy;
import io.grpc.protobuf.services.ProtoReflectionServiceV1;

@Component
public class GrpcServerRunner implements CommandLineRunner {
 
    private final List<BindableService> grpcServices;
 
    @Value("${grpc.server.port:9090}")
    private int grpcPort;
 
    private Server server;
 
    public GrpcServerRunner(List<BindableService> grpcServices) {
        this.grpcServices = grpcServices;
    }
 
    @Override
    public void run(String... args) throws IOException {
        ServerBuilder<?> builder = ServerBuilder.forPort(grpcPort);
        grpcServices.forEach(builder::addService);
        builder.addService(ProtoReflectionServiceV1.newInstance());
 
        server = builder.build().start();
        System.out.println("gRPC server started on port " + grpcPort);
 
        Runtime.getRuntime().addShutdownHook(new Thread(this::stopServer));
    }
 
    @PreDestroy
    public void stopServer() {
        if (server != null) {
            server.shutdown();
            try {
                if (!server.awaitTermination(5, TimeUnit.SECONDS)) {
                    server.shutdownNow();
                }
            } catch (InterruptedException e) {
                server.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }
}
