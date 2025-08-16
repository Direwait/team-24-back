package ru.team24.database.domain.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.team24.database.domain.manager.entity.Candidate;

import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Optional<Candidate> findByCandidateId(long candidateId);

    Optional<Candidate> findByCandidateMail(String candidateMail);
}
