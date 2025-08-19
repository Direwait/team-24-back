package ru.team24.handler.exception;

import org.springframework.http.HttpStatus;
import ru.team24.handler.error.BaseException;

public class NotFoundException extends BaseException {
    public NotFoundException(String message, String path) {
        super(HttpStatus.NOT_FOUND, message, path);
    }
}
