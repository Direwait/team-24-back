package ru.team24.service.interfaces;

import ru.team24.service.dto.RequestDto;
import ru.team24.database.enums.RequestState;
import ru.team24.service.payload.request.RequestStatusRequest;
import ru.team24.service.payload.request.RequestUpdateRequest;

import java.util.List;

public interface RequestService {
    RequestDto findByRequestId(long requestId);

    List<RequestDto> getByUserId(long userId);

    List<RequestDto> getByRequestState(String state);

    void updateRequestByRequestId(long requestId, RequestDto request);

    void createRequest(RequestDto requestDto);

    boolean isRequestPending(RequestStatusRequest statusRequest);

    void updateRequest(RequestUpdateRequest updateRequest);


}
