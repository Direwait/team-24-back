package ru.team24.controller.domain.general;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.hc.client5.http.auth.InvalidCredentialsException;
import org.springframework.http.ResponseEntity;
import ru.team24.service.payload.request.LogoutRequest;
import ru.team24.service.payload.request.RefreshRequest;
import ru.team24.service.payload.request.SignInRequest;

@Tag(name = "Auth. Авторизация", description = "Операции для Security")
public interface AuthController {
    ResponseEntity<?> signIn( SignInRequest signInRequest) throws InvalidCredentialsException;
    ResponseEntity<?> logout(LogoutRequest logoutRequest);
    ResponseEntity<?> refresh(RefreshRequest refreshRequest);
}
