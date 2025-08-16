package ru.team24.service.domain.general;

import ru.team24.service.payload.request.LogoutRequest;
import ru.team24.service.payload.request.RefreshRequest;
import ru.team24.service.payload.request.SignInRequest;
import ru.team24.service.payload.response.RefreshResponse;
import ru.team24.service.payload.response.SignInResponse;

public interface AuthService {
    SignInResponse signIn(SignInRequest signInRequest);

    void logout(LogoutRequest logoutRequest);

    RefreshResponse refresh(RefreshRequest refreshRequest);
}
