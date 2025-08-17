package ru.team24.service.domain.manager.tokenLinkManager;

public interface TokenManager {
    String generateAccessToken();

    boolean isTokenValid (String token);
}
