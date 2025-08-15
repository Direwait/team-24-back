package ru.team24.controller.impl;



import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.team24.controller.AuthController;
import ru.team24.service.impl.AuthService;
import ru.team24.service.payload.request.SignInRequest;

@RequestMapping("/api/auth")
@RestController
@AllArgsConstructor
public class AuthControllerImpl implements AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping()
    public ResponseEntity<?> signIn(@RequestBody SignInRequest signInRequest) {
        return new ResponseEntity<>(authService.signIn(signInRequest), HttpStatus.OK);
    }

    public ResponseEntity<?> logout() {
        return null;
    }

    public ResponseEntity<?> refresh() {
        return null;
    }
}
