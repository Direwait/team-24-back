package ru.team24.database.domain.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.team24.database.domain.admin.entity.Sopd;

import java.util.Optional;

@Repository
public interface SopdRepository extends JpaRepository<Sopd, Long> {
    Optional<Sopd> findTopByOrderBySopdIdDesc();

    @Query("SELECT MAX(s.sopdId) FROM Sopd s")
    long getRecentSopdId();
}
