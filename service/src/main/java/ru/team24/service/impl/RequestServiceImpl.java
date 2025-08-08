package ru.team24.service.impl;

import org.springframework.stereotype.Service;
import ru.team24.database.entities.Request;
import ru.team24.database.enums.RequestState;
import ru.team24.service.interfaces.RequestService;

@Service
public class RequestServiceImpl implements RequestService {
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
