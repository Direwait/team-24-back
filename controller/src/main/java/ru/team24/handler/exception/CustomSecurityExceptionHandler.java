package ru.team24.handler.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import ru.team24.handler.error.ApiError;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomSecurityExceptionHandler implements
        AuthenticationEntryPoint, AccessDeniedHandler {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        String errorMessage = "Требуется авторизация";
        if (authException instanceof BadCredentialsException) {
            errorMessage = "Неверный email или пароль";
        }
        sendError(response,
                HttpStatus.UNAUTHORIZED.value(),
                errorMessage,
                request.getRequestURI());
    }
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response,
//                         AuthenticationException authException) throws IOException {
//        throw request; // отдаём дальше в @ControllerAdvice
//    }
//    @Override
//    public void handle(HttpServletRequest request, HttpServletResponse response,
//                       AccessDeniedException accessDeniedException) throws IOException {
//        throw accessDeniedException; // отдаём дальше в @ControllerAdvice
//    }

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        sendError(response,
                HttpStatus.FORBIDDEN.value(),
                "Доступ запрещён",
                request.getRequestURI());
    }

    private void sendError(HttpServletResponse response,
                           int status,
                           String message,
                           String path) throws IOException {
        ApiError error = ApiError.builder()
                .errorCode(status)
                .message(message)
                .path(path)
                .build();

        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(status);
        response.getWriter().write(objectMapper.writeValueAsString(error));
    }

}
