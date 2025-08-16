package ru.team24.service.payload.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LogoutRequest {
    private String accessToken;
    private String refreshToken;
}
