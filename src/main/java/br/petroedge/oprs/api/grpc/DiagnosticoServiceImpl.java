package br.petroedge.oprs.api.grpc;

import br.com.petroedge.diagnostico.grpc.DiagnosticoRequest;
import br.com.petroedge.diagnostico.grpc.DiagnosticoResponse;
import br.com.petroedge.diagnostico.grpc.DiagnosticoServiceGrpc;
import br.petroedge.oprs.api.entity.Diagnostico;
import br.petroedge.oprs.api.mapper.DiagnosticoMapper;
import br.petroedge.oprs.api.service.DiagnosticoService;
import br.petroedge.oprs.common.service.EmailQueueService;
import io.grpc.stub.StreamObserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DiagnosticoServiceImpl extends DiagnosticoServiceGrpc.DiagnosticoServiceImplBase {
    private final int severidadeMinimaParaEnvio;

    private final String destinatarioEmail;

    private final DiagnosticoService diagnosticoService;

    private final EmailQueueService emailQueueService;

    public DiagnosticoServiceImpl(@Value("${app.email.severidade-minima}") int severidadeMinimaParaEnvio, @Value("${app.email.administrador}") String destinatarioEmail, DiagnosticoService diagnosticoService, EmailQueueService emailQueueService) {
        this.severidadeMinimaParaEnvio = severidadeMinimaParaEnvio;
        this.destinatarioEmail = destinatarioEmail;
        this.diagnosticoService = diagnosticoService;
        this.emailQueueService = emailQueueService;
    }

    @Override
    public void registrarDiagnostico(DiagnosticoRequest request, StreamObserver<DiagnosticoResponse> responseObserver) {
        try {
            Diagnostico diagnostico = diagnosticoService.salvarDiagnostico(DiagnosticoMapper.fromGrpcToEntity(request));

            DiagnosticoResponse response =
                    DiagnosticoResponse.newBuilder().setId(diagnostico.getId()).build();

            if (diagnostico.getSeveridade() >= severidadeMinimaParaEnvio) {
                emailQueueService.enfileirar(destinatarioEmail, "Diagnóstico com severidade encontrado", diagnostico.getDescricao(), diagnostico.getId());
            }

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (RuntimeException e) {
            responseObserver.onError(GrpcErrorMapper.toStatusException(e, request.getFonteId()));
        }
    }
}
