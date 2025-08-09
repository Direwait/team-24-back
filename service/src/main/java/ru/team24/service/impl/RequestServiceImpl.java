package ru.team24.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.team24.database.entities.Request;
import ru.team24.database.enums.RequestState;
import ru.team24.database.repositories.RequestRepository;
import ru.team24.service.interfaces.RequestService;
import ru.team24.service.mapper.RequestMapper;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final RequestMapper requestMapper;
    private final RequestRepository requestRepository;

    public Request findByRequestId(long requestId) {
        return null;
    }

    public Request findByUserId(long userId) {
        return null;
    }

    public void UpdateRequestByRequestId(long requestId, Request request) {

    }

    public void createRequest(long userId, long candidateId, long templateId, String requestToken, RequestState requestState) {

    }
}
