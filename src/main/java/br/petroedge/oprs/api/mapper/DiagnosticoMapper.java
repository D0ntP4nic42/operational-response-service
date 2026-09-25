package br.petroedge.oprs.api.mapper;

import br.com.petroedge.diagnostico.grpc.DiagnosticoRequest;
import br.petroedge.oprs.api.entity.Diagnostico;
import com.google.protobuf.Timestamp;
import java.time.Instant;

public class DiagnosticoMapper {
    public static Diagnostico fromGrpcToEntity(DiagnosticoRequest diagnosticoGrpc) {
        validarRequest(diagnosticoGrpc);
        var diagnosticoDomain = new Diagnostico();

        diagnosticoDomain.setFonteId(diagnosticoGrpc.getFonteId());
        diagnosticoDomain.setDescricao(diagnosticoGrpc.getDescricao());

        var dtCriacao = diagnosticoGrpc.hasDtCriacao() ? toInstant(diagnosticoGrpc.getDtCriacao()) : Instant.now();
        diagnosticoDomain.setDtCriacao(dtCriacao);

        diagnosticoDomain.setSeveridade(diagnosticoGrpc.getSeveridade());

        return diagnosticoDomain;
    }

    private static void validarRequest(DiagnosticoRequest diagnosticoGrpc) {
        if (diagnosticoGrpc.getFonteId() <= 0) {
            throw new IllegalArgumentException("O campo 'fonteId' deve ser maior que 0.");
        }
        if (diagnosticoGrpc.getDescricao() == null
                || diagnosticoGrpc.getDescricao().isEmpty()) {
            throw new IllegalArgumentException("O campo 'descricao' é obrigatório.");
        }
        if (diagnosticoGrpc.getSeveridade() <= 0) {
            throw new IllegalArgumentException("O campo 'severidade' deve ser maior que 0.");
        }
        if (diagnosticoGrpc.hasDtCriacao()) {
            try {
                Instant dtCriacao = toInstant(diagnosticoGrpc.getDtCriacao());

                if (dtCriacao.equals(Instant.EPOCH)) {
                    throw new IllegalArgumentException("O campo 'dtCriacao' não pode ser 1970-01-01T00:00:00Z.");
                }
            } catch (RuntimeException e) {
                throw new IllegalArgumentException("O campo 'dtCriacao' deve ser um timestamp válido.", e);
            }
        }
    }

    private static Instant toInstant(Timestamp timestamp) {
        return Instant.ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos());
    }
}
