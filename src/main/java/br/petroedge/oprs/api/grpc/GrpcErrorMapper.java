package br.petroedge.oprs.api.grpc;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.ConstraintViolationException.ConstraintKind;
import org.springframework.dao.DataIntegrityViolationException;

@Slf4j
public final class GrpcErrorMapper {

    private GrpcErrorMapper() {}

    public static StatusRuntimeException toStatusException(RuntimeException e, Object context) {
        if (e instanceof StatusRuntimeException sre) {
            return sre;
        }
        if (e instanceof DataIntegrityViolationException && isUniqueViolation(e)) {
            return Status.ALREADY_EXISTS
                    .withDescription("Registro já existe: " + context)
                    .withCause(e)
                    .asRuntimeException();
        }
        if (e instanceof DataIntegrityViolationException) {
            return Status.INVALID_ARGUMENT
                    .withDescription("Dados inválidos para persistência.")
                    .withCause(e)
                    .asRuntimeException();
        }
        if (e instanceof IllegalArgumentException) {
            return Status.INVALID_ARGUMENT
                    .withDescription(e.getMessage())
                    .withCause(e)
                    .asRuntimeException();
        }

        log.error("Erro inesperado durante chamada gRPC", e);
        return Status.INTERNAL
                .withDescription("Erro interno ao processar a requisição.")
                .asRuntimeException();
    }

    private static boolean isUniqueViolation(Throwable e) {
        for (Throwable cause = e; cause != null; cause = cause.getCause()) {
            if (cause instanceof ConstraintViolationException cve) {
                return cve.getKind() == ConstraintKind.UNIQUE;
            }
        }
        return false;
    }
}
