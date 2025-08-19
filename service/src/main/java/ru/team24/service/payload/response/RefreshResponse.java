package ru.team24.service.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RefreshResponse {
    private String role;
    private String accessToken;
}
