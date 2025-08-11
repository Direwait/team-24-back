package ru.team24.service.interfaces;

import ru.team24.database.dto.RequestDto;
import ru.team24.database.entities.Request;
import ru.team24.database.enums.RequestState;

import java.util.List;

public interface RequestService {
    RequestDto findByRequestId(long requestId);
    List<RequestDto> getByUserId(long userId);
    void UpdateRequestByRequestId(long requestId, RequestDto request);
    void createRequest(long userId, long candidateId, long templateId, String requestToken, RequestState requestState);
}
