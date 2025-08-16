package ru.team24.database.domain.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.team24.database.domain.manager.entity.Candidate;
import ru.team24.database.domain.manager.entity.Request;
import ru.team24.database.domain.general.entity.User;
import ru.team24.database.enums.RequestState;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    Optional<Request> findByRequestId(long requestId);
    List<Request> getByUser(User userId);
    List<Request> getByRequestState(RequestState requestState);
    Optional<Request> findByRequestToken(String requestToken);

    Optional<Request> findByCandidate(Candidate candidate);
}
