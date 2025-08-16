package ru.team24.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.team24.database.entities.RefreshToken;
import ru.team24.database.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findRefreshTokenByRefreshToken(String refreshToken);

    boolean existsByUser(User user);

    Optional<RefreshToken> findByUser(User user);

    boolean existsByRefreshToken(String refreshToken);

    void deleteByRefreshToken(String refreshToken);
}
