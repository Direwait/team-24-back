package ru.team24.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.team24.database.entities.Sopd;

import java.util.Optional;

@Repository
public interface SopdRepository extends JpaRepository<Sopd, Long> {
    Optional<Sopd> findTopByOrderBySopdIdDesc();
}
