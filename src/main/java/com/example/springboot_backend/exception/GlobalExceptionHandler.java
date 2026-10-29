package com.example.springboot_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Map<String, Object>> handleApiException(ApiException ex) {
        Map<String, Object> body = buildErrorBody(ex.getMessage(), ex.getStatusCode());
        return new ResponseEntity<>(body, HttpStatus.valueOf(ex.getStatusCode()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        var errors = ex.getBindingResult().getAllErrors().stream()
                .map(err -> {
                    if (err instanceof FieldError fe) {
                        return Map.of("field", fe.getField(), "message", fe.getDefaultMessage());
                    }
                    return Map.of("message", err.getDefaultMessage());
                })
                .collect(Collectors.toList());

        Map<String, Object> body = buildErrorBody("Validation failed", 400);
        body.put("errors", errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    // Ловим всё остальное как 500 — чтобы не показывать стек пользователю
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        // В продакшене сюда можно добавить логирование и алертинг
        Map<String, Object> body = buildErrorBody("Internal server error", 500);
        // Не отдаём сообщение исключения наружу — это риск безопасности
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Map<String, Object> buildErrorBody(String message, int status) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", Instant.now());
        body.put("status", status);
        body.put("message", message);
        return body;
    }
}
