package ru.team24.service.security;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.team24.database.domain.general.entity.RefreshToken;
import ru.team24.database.domain.general.entity.User;
import ru.team24.database.domain.general.repository.RefreshTokenRepository;
import ru.team24.database.domain.general.repository.UserRepository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    @Value("${jwt.configuration.refreshExpirationTime}")
    private Long refreshTokenExpirationTime;

    private final RefreshTokenRepository refreshTokenRepository;

    private final UserRepository userRepository;


    public Optional<RefreshToken> findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findRefreshTokenByRefreshToken(refreshToken);
    }

    public RefreshToken generateRefreshToken(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        if (refreshTokenRepository.existsByUser(user)) {
            refreshTokenRepository.delete(refreshTokenRepository.findByUser(user).orElseThrow());
        }

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setExpiresAt(Instant.now().plusMillis(refreshTokenExpirationTime));
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken checkExpired(RefreshToken refreshToken) {
        if (refreshToken.getExpiresAt().isBefore(Instant.now())) {
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException("Refresh token has expired");
        }
        return refreshToken;
    }

    @Transactional
    public void deleteRefreshToken(String refreshToken) {
        if(refreshTokenRepository.existsByRefreshToken(refreshToken)){
            refreshTokenRepository.deleteByRefreshToken(refreshToken);
        }
    }
}
