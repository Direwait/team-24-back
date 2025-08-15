package ru.team24.service.interfaces;

import ru.team24.service.dto.RequestDto;

import java.util.List;

public interface RequestService {
    RequestDto findByRequestId(long requestId);
    List<RequestDto> getByUserId(long userId);
    List<RequestDto> getByRequestState(String state);
    void updateRequestByRequestId(long requestId, RequestDto request);
    void createRequest(RequestDto requestDto);
}
