package ru.team24.controller.future.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.team24.controller.future.AuthController;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final AuthController authController;

    @Override
    public ResponseEntity<?> login() {
        return null;
    }
}
