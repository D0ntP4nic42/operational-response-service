package br.petroedge.oprs.api.grpc;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

import br.com.petroedge.diagnostico.grpc.DiagnosticoRequest;
import br.com.petroedge.diagnostico.grpc.DiagnosticoResponse;
import br.com.petroedge.diagnostico.grpc.DiagnosticoServiceGrpc;
import br.com.petroedge.diagnostico.grpc.EnviarDiagnosticosResponse;
import io.grpc.stub.StreamObserver;

@Component
public class DiagnosticoServiceImpl extends DiagnosticoServiceGrpc.DiagnosticoServiceImplBase {
 
    @Override
    public void enviarDiagnostico(DiagnosticoRequest request, StreamObserver<DiagnosticoResponse> responseObserver) {
        // TODO: replace with real persistence/business logic
        String id = UUID.randomUUID().toString();
 
        DiagnosticoResponse response = DiagnosticoResponse.newBuilder()
                .setId(id)
                .build();
 
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
 
    @Override
    public StreamObserver<DiagnosticoRequest> enviarDiagnosticos(
            StreamObserver<EnviarDiagnosticosResponse> responseObserver) {
        AtomicInteger total = new AtomicInteger(0);
 
        return new StreamObserver<DiagnosticoRequest>() {
            @Override
            public void onNext(DiagnosticoRequest request) {
                // TODO: replace with real persistence/business logic
                total.incrementAndGet();
            }
 
            @Override
            public void onError(Throwable t) {
                // TODO: log the error appropriately
            }
 
            @Override
            public void onCompleted() {
                EnviarDiagnosticosResponse response = EnviarDiagnosticosResponse.newBuilder()
                        .setTotalRecebidos(total.get())
                        .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        };
    }
}
