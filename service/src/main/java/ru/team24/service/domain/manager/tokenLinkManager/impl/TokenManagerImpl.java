package ru.team24.service.domain.manager.tokenLinkManager.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.team24.database.domain.manager.repository.RequestRepository;
import ru.team24.service.domain.manager.tokenLinkManager.TokenManager;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenManagerImpl implements TokenManager {
    private final RequestRepository requestRepository;

    public String generateAccessToken() {
        log.info("уникальная ссылка");
        return UUID.randomUUID().toString();
    }

    public boolean isTokenValid(String token) {
        return requestRepository.findByRequestToken(token)
                .filter(r -> !"EXPIRED".equals(r.getRequestToken()))
                .isPresent();
    }
}
