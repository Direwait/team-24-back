package ru.team24.database.domain.manager.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.team24.database.domain.manager.entity.Candidate;
import ru.team24.database.domain.manager.entity.Request;
import ru.team24.database.enums.RequestState;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {


    Optional<Request> findByRequestId(long requestId);

    Optional<Request> findByRequestToken(String requestToken);

    Optional<Request> findByCandidate(Candidate candidate);


    @EntityGraph(attributePaths = {"candidate"})
    Page<Request> findAllByUser_UserIdAndRequestStateAndRequestIsActive(
            Long userId,
            RequestState requestState,
            boolean requestIsActive,
            Pageable pageable
    );

    @EntityGraph(attributePaths = {"candidate"})
    Page<Request> findAllByUser_UserId(Long userId, Pageable pageable);

    @EntityGraph(attributePaths = {"candidate"})
    List<Request> getByRequestState(RequestState requestState);
}
