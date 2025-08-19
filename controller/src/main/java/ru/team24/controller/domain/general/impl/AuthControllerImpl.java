package ru.team24.controller.domain.general.impl;



import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import ru.team24.controller.domain.general.AuthController;
import ru.team24.service.domain.general.AuthService;
import ru.team24.service.payload.request.LogoutRequest;
import ru.team24.service.payload.request.RefreshRequest;
import ru.team24.service.payload.request.SignInRequest;

@RequestMapping("/api/v1/auth")
@RestController
@AllArgsConstructor
public class AuthControllerImpl implements AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping()
    public ResponseEntity<?> signIn(@Valid @RequestBody SignInRequest signInRequest) throws BadCredentialsException {
        return new ResponseEntity<>(authService.signIn(signInRequest), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<?> logout(@RequestBody LogoutRequest logoutRequest) {
        authService.logout(logoutRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping()
    public ResponseEntity<?> refresh(@RequestBody RefreshRequest refreshRequest) {
        return new ResponseEntity<>(authService.refresh(refreshRequest), HttpStatus.OK);
    }
}
