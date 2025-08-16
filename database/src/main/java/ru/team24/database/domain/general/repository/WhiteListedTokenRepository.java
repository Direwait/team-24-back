package ru.team24.database.domain.general.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.team24.database.domain.general.entity.WhiteListedToken;

@Repository
public interface WhiteListedTokenRepository extends JpaRepository<WhiteListedToken, Long> {
    boolean existsByToken(String token);

    void deleteByToken(String token);
}
