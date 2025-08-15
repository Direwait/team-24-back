package ru.team24.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ru.team24.service.payload.request.LogoutRequest;
import ru.team24.service.payload.request.RefreshRequest;
import ru.team24.service.payload.request.SignInRequest;

@Tag(name = "Auth. Авторизация", description = "Операции для Security")
public interface AuthController {
    ResponseEntity<?> signIn( SignInRequest signInRequest);
    ResponseEntity<?> logout(LogoutRequest logoutRequest);
    ResponseEntity<?> refresh(RefreshRequest refreshRequest);
}
