package ru.team24.controller.future;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Auth. Авторизация", description = "DONT WORK")
public interface AuthController {
    ResponseEntity<?> login();
}
