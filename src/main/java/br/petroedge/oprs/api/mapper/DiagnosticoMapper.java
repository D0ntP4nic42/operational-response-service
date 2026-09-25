package br.petroedge.oprs.api.mapper;

import br.com.petroedge.diagnostico.grpc.DiagnosticoRequest;
import br.petroedge.oprs.api.entity.Diagnostico;
import com.google.protobuf.Timestamp;
import java.time.Instant;

public class DiagnosticoMapper {
    public static Diagnostico fromGrpcToEntity(DiagnosticoRequest diagnosticoGrpc) {
        var diagnosticoDomain = new Diagnostico();

        diagnosticoDomain.setFonteId(diagnosticoGrpc.getFonteId());
        diagnosticoDomain.setDescricao(diagnosticoGrpc.getDescricao());

        var dtCriacao = diagnosticoGrpc.hasDtCriacao() ? toInstant(diagnosticoGrpc.getDtCriacao()) : Instant.now();
        diagnosticoDomain.setDtCriacao(dtCriacao);

        diagnosticoDomain.setSeveridade(diagnosticoGrpc.getSeveridade());

        return diagnosticoDomain;
    }

    private static Instant toInstant(Timestamp timestamp) {
        return Instant.ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos());
    }
}
