package ru.team24.service.payload.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignInResponse {
    private String role;
    private String accessToken;
    private String refreshToken;
}
