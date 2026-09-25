package br.petroedge.oprs.api.grpc;

import br.com.petroedge.diagnostico.grpc.DiagnosticoRequest;
import br.com.petroedge.diagnostico.grpc.DiagnosticoResponse;
import br.com.petroedge.diagnostico.grpc.DiagnosticoServiceGrpc;
import br.petroedge.oprs.api.mapper.DiagnosticoMapper;
import br.petroedge.oprs.api.service.DiagnosticoService;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Component;

@Component
public class DiagnosticoServiceImpl extends DiagnosticoServiceGrpc.DiagnosticoServiceImplBase {
    private final DiagnosticoService diagnosticoService;

    public DiagnosticoServiceImpl(DiagnosticoService diagnosticoService) {
        this.diagnosticoService = diagnosticoService;
    }

    @Override
    public void registrarDiagnostico(DiagnosticoRequest request, StreamObserver<DiagnosticoResponse> responseObserver) {
        try {
            String id = diagnosticoService.salvarDiagnostico(DiagnosticoMapper.fromGrpcToEntity(request));

            DiagnosticoResponse response =
                    DiagnosticoResponse.newBuilder().setId(id).build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (RuntimeException e) {
            responseObserver.onError(GrpcErrorMapper.toStatusException(e, request.getFonteId()));
        }
    }
}
