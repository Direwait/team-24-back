package ru.team24.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.team24.database.entities.Request;
import ru.team24.database.entities.User;
import ru.team24.database.enums.RequestState;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    Optional<Request> findByRequestId(long requestId);
    List<Request> getByUser(User userId);
    List<Request> getByRequestState(RequestState requestState);
}
