package ru.team24.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;
import ru.team24.handler.error.ResponseApiError;

@RestController
public class CustomErrorController implements ErrorController {


    // Обработчик для всех остальных ошибок (включая 404, когда исключение не выбрасывается)
    @RequestMapping("/error")
    public ResponseEntity<ResponseApiError> handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String message = (String) request.getAttribute("javax.servlet.error.message");

        if (message == null) {
            message = "Unexpected error occurred";
        }

        return ResponseApiError.create(
                HttpStatus.valueOf(statusCode),
                "Error " + statusCode,
                message
        );
    }
}

