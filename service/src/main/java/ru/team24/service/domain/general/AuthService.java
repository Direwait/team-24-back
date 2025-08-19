package ru.team24.service.domain.general;


import org.springframework.security.authentication.BadCredentialsException;
import ru.team24.service.payload.request.SignInRequest;
import ru.team24.service.payload.response.RefreshResponse;
import ru.team24.service.payload.response.SignInResponse;

public interface AuthService {
    SignInResponse signIn(SignInRequest signInRequest) throws BadCredentialsException;

    void logout(String accessToken, String refreshToken);

    RefreshResponse refresh(String accessToken, String refreshToken);
}
