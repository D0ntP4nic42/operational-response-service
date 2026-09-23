package br.petroedge.oprs.api.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> implements Serializable {
    private final int status;
    private final String message;
    private final LocalDateTime timestamp;
    private final T data;

    public static <T> ApiResponse<T> of(HttpStatus status, String message) {
        return ApiResponse.<T>builder()
                .status(status.value())
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static <T> ApiResponse<T> of(HttpStatus status, String message, T data) {
        return ApiResponse.<T>builder()
                .status(status.value())
                .message(message)
                .timestamp(LocalDateTime.now())
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> success(T data) {
        return of(HttpStatus.OK, "Operação realizada com sucesso", data);
    }

    public static <T> ApiResponse<T> created(T data) {
        return of(HttpStatus.CREATED, "Recurso criado com sucesso", data);
    }

    public static ApiResponse<Void> error(HttpStatus status, String message) {
        return of(status, message);
    }

    public static <T> ApiResponse<T> error(HttpStatus status, String message, T data) {
        return of(status, message, data);
    }
}
