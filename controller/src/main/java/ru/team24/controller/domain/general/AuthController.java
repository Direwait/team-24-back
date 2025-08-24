package ru.team24.controller.domain.general;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import ru.team24.handler.exception.InvalidCredentialsException;
import ru.team24.service.payload.request.SignInRequest;

@Tag(name = "Auth. Авторизация", description = "Операции для Security")
public interface AuthController {
    ResponseEntity<?> signIn( SignInRequest signInRequest, HttpServletResponse response) throws InvalidCredentialsException;
    ResponseEntity<?> logout(String accessToken, String refreshToken);
    ResponseEntity<?> refresh(String accessToken, String refreshToken);
}
