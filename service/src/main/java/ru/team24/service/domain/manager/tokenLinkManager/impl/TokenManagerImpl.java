package ru.team24.service.domain.manager.tokenLinkManager.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.team24.service.domain.manager.tokenLinkManager.TokenManager;

import java.util.UUID;

@Slf4j
@Component
public class TokenManagerImpl implements TokenManager {

    public String generateAccessToken() {
        return UUID.randomUUID().toString();
    }

}
