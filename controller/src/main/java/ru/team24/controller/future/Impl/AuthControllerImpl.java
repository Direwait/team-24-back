package ru.team24.controller.future.Impl;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.team24.controller.future.AuthController;

@RequestMapping("/api/v1/auth")
@RestController
public class AuthControllerImpl implements AuthController {


    @Override
    public ResponseEntity<?> login() {
        return null;
    }
}
