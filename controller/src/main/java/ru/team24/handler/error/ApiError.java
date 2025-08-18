package ru.team24.handler.error;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.time.LocalDateTime;

@Builder
@Getter
@ToString
public class ApiError {
    private int errorCode;
    private String message;
    private String path;

    @Builder.Default
    private final LocalDateTime timestamp = LocalDateTime.now();

    public static ResponseEntity<ApiError> create(HttpStatus status, String message, String path) {
        ApiError error = ApiError.builder()
                .errorCode(status.value())
                .message(message)
                .path(path)
                .build();
        return ResponseEntity.status(status).body(error);
    }
}
