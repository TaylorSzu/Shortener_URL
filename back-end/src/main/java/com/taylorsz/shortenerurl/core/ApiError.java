package com.taylorsz.shortenerurl.core;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@Schema(description = "Estrutura padrão de erros da API")
public record ApiError(
        @Schema(description = "Título geral do erro", example = "Requisição inválida")
        String title,
        @Schema(description = "Código HTTP do erro", example = "400")
        int status,
        @Schema(description = "Descrição detalhada do erro", example = "Erro de validação nos campos da requisição")
        String detail,
        @Schema(description = "Endpoint que gerou o erro", example = "/url")
        String instance,
        @Schema(description = "Momento em que o erro ocorreu", example = "2025-09-11T18:15:37.802Z")
        OffsetDateTime timestamp,
        @Schema(description = "Detalhes adicionais, como erros de validação")
        Object message
) {
    public static ApiError from(MethodArgumentNotValidException e, HttpServletRequest req) {
        var errors = e.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(
                                DefaultMessageSourceResolvable::getDefaultMessage,
                                Collectors.toList()
                        )
                ));
        var title = e.getBody().getTitle();
        var status = e.getBody().getStatus();
        var detail = e.getBody().getDetail();
        var instance = req.getRequestURI();
        var timestamp = OffsetDateTime.now();
        var message = Map.copyOf(errors);

        return new ApiError(title, status, detail, instance, timestamp, message);
    }
}
