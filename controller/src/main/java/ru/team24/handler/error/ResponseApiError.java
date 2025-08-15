package ru.team24.handler.error;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.time.LocalDateTime;

@Builder
@Setter
@Getter
public class ResponseApiError {

    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String error;
    private final String message;
    private final String path;

    //фабричный метод
    public static ResponseEntity<ResponseApiError> create(HttpStatus status,
                                                          String message,
                                                          String path) {
        return ResponseApiError.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .path(path)
                .build()
                .toResponseEntity();
    }

    public ResponseEntity<ResponseApiError> toResponseEntity() {
        return ResponseEntity.status(this.status).body(this);
    }
}
