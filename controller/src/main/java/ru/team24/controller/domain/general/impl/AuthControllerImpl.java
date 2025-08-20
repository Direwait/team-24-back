package ru.team24.controller.domain.general.impl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.team24.controller.domain.general.AuthController;
import ru.team24.service.domain.general.AuthService;
import ru.team24.service.payload.request.SignInRequest;
import ru.team24.service.payload.response.SignInResponse;
import ru.team24.service.security.UserDetailsImpl;

@RequestMapping("/api/v1/auth")
@RestController
@AllArgsConstructor
public class AuthControllerImpl implements AuthController {
    @Autowired
    private final AuthService authService;

    @PostMapping()
    public ResponseEntity<?> signIn(@RequestBody SignInRequest signInRequest, HttpServletResponse response) throws BadCredentialsException {
        SignInResponse signInResponse = authService.signIn(signInRequest);
        var cookie = new Cookie("refreshToken", signInResponse.getRefreshToken());
        response.addCookie(cookie);
        return new ResponseEntity<>(signInResponse, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<?> logout(@RequestHeader(name = "Authorization") String accessToken,
                                    @CookieValue String refreshToken) {
        authService.logout(accessToken, refreshToken);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping()
    public ResponseEntity<?> refresh(@RequestHeader(name = "Authorization") String accessToken,
                                     @CookieValue String refreshToken) {
        return new ResponseEntity<>(authService.refresh(accessToken, refreshToken), HttpStatus.OK);
    }
}
