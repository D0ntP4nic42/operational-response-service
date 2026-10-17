package br.petroedge.oprs.api.grpc;

import br.com.petroedge.diagnostico.grpc.DiagnosticoRequest;
import br.com.petroedge.diagnostico.grpc.DiagnosticoResponse;
import br.com.petroedge.diagnostico.grpc.DiagnosticoServiceGrpc;
import br.petroedge.oprs.api.entity.Diagnostico;
import br.petroedge.oprs.api.mapper.DiagnosticoMapper;
import br.petroedge.oprs.api.service.DiagnosticoService;
import io.grpc.stub.StreamObserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DiagnosticoServiceImpl extends DiagnosticoServiceGrpc.DiagnosticoServiceImplBase {
    private final int severidadeMinimaParaEnvio;

    private final DiagnosticoService diagnosticoService;

    public DiagnosticoServiceImpl(DiagnosticoService diagnosticoService, @Value("${app.email.severidade-minima}") int severidadeMinimaParaEnvio) {
        this.diagnosticoService = diagnosticoService;
        this.severidadeMinimaParaEnvio = severidadeMinimaParaEnvio;
    }

    @Override
    public void registrarDiagnostico(DiagnosticoRequest request, StreamObserver<DiagnosticoResponse> responseObserver) {
        try {
            Diagnostico diagnostico = diagnosticoService.salvarDiagnostico(DiagnosticoMapper.fromGrpcToEntity(request));

            DiagnosticoResponse response =
                    DiagnosticoResponse.newBuilder().setId(diagnostico.getId()).build();

            if (diagnostico.getSeveridade() >= severidadeMinimaParaEnvio) {
                //envio de e-mail
            }

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (RuntimeException e) {
            responseObserver.onError(GrpcErrorMapper.toStatusException(e, request.getFonteId()));
        }
    }
}
