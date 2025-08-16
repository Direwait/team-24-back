package ru.team24.service.domain.manager.tokenLinkManager;

public interface tokenManager {
    String generateAccessToken();

    boolean isTokenValid (String token);
}
