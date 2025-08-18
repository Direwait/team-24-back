package ru.team24.database.domain.manager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.team24.database.domain.manager.entity.Candidate;
import ru.team24.database.domain.manager.entity.Request;
import ru.team24.database.domain.general.entity.User;
import ru.team24.database.enums.RequestState;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    String FIND_ACTIVE_REQUESTS_QUERY = """
        SELECT r FROM Request r
        JOIN FETCH r.candidate
        ORDER BY r.requestDate DESC
        """;

    Optional<Request> findByRequestId(long requestId);

    List<Request> findAllByUser_UserId(Long userId);
    List<Request> getByRequestState(RequestState requestState);
    Optional<Request> findByRequestToken(String requestToken);

    Optional<Request> findByCandidate(Candidate candidate);


    @Query(FIND_ACTIVE_REQUESTS_QUERY)
    Page<Request> findAllActiveRequests(Pageable pageable);
}
