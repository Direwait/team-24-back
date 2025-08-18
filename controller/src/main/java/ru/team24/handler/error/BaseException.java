package ru.team24.handler.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public abstract class BaseException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final ApiError apiError;

    public BaseException(HttpStatus httpStatus, ApiError apiError) {
        super(apiError.getMessage());
        this.httpStatus = httpStatus;
        this.apiError = apiError;
    }

    public BaseException(HttpStatus httpStatus, String message, String path) {
        this(httpStatus,
                ApiError.builder()
                        .errorCode(httpStatus.value())
                        .message(message)
                        .path(path)
                        .build());
    }
}
