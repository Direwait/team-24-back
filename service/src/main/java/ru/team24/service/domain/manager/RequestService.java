package ru.team24.service.domain.manager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.team24.service.observ.action.ActionCreateRequest;
import ru.team24.service.dto.request.RequestDto;

import ru.team24.service.dto.request.RequestWithCandidateDto;
import ru.team24.service.payload.request.RequestStatusRequest;
import ru.team24.service.payload.request.CandidateResponse;
import java.util.List;

public interface RequestService {
    RequestWithCandidateDto findByRequestId(long requestId);

    void updateRequestByRequestId(long requestId, RequestDto request);

    boolean isRequestPending(RequestStatusRequest statusRequest);

    void updateRequest(CandidateResponse updateRequest);

    Page<RequestWithCandidateDto> findRequests(long userId, String state, Pageable pageable);

    void createRequestWithTokenByClient(ActionCreateRequest action);

    void deleteRequest(long requestId);

    void deleteRequestReal(long requestId);

}
