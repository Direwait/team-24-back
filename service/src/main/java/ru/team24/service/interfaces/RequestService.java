package ru.team24.service.interfaces;

import ru.team24.database.entities.Request;
import ru.team24.database.enums.RequestState;

public interface RequestService {
    Request findByRequestId(long requestId);
    Request findByUserId(long userId);
    void UpdateRequestByRequestId(long requestId, Request request);
    void createRequest(long userId, long candidateId, long templateId, String requestToken, RequestState requestState);
}
