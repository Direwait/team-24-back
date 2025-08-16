package ru.team24.service.interfaces;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.team24.service.payload.request.LogoutRequest;
import ru.team24.service.payload.request.RefreshRequest;
import ru.team24.service.payload.request.SignInRequest;
import ru.team24.service.payload.response.RefreshResponse;
import ru.team24.service.payload.response.SignInResponse;
import ru.team24.service.security.UserDetailsImpl;

import java.util.List;

public interface AuthService {
    SignInResponse signIn(SignInRequest signInRequest);

    void logout(LogoutRequest logoutRequest);

    RefreshResponse refresh(RefreshRequest refreshRequest);
}
