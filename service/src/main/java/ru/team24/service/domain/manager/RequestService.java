package ru.team24.service.domain.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.team24.service.dto.request.RequestWithCandidateAndManagerDto;
import ru.team24.service.observ.action.ActionCreateRequest;

import ru.team24.service.dto.request.RequestWithCandidateDto;
import ru.team24.service.payload.request.RequestCreationRequest;
import ru.team24.service.payload.request.RequestStatusRequest;
import ru.team24.service.payload.request.CandidateResponse;

public interface RequestService {
    RequestWithCandidateDto findByRequestId(long requestId);

    boolean isRequestPending(RequestStatusRequest statusRequest);

    void updateRequest(CandidateResponse updateRequest);

    Page<RequestWithCandidateDto> findRequests(long userId, String state, Pageable pageable);

    public Page<RequestWithCandidateAndManagerDto> findDeletedRequests(Pageable pageable);

    void createRequestWithTokenByClient(ActionCreateRequest action) throws JsonProcessingException;

    void hardDeleteRequest(long requestId);

    void createRequests(RequestCreationRequest createRequest, Long userId);

    void softDeleteRequest(long requestId);

    void hardDeleteByRequestId(long requestId);

    void hardDeleteAll();

}
