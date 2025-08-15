package ru.team24.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.team24.database.entities.WhiteListedToken;

@Repository
public interface WhiteListedTokenRepository extends JpaRepository<WhiteListedToken, Long> {
    boolean existsByToken(String token);

    void deleteByToken(String token);
}
