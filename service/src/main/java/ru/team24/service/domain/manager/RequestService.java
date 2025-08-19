package ru.team24.service.domain.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.team24.service.observ.action.ActionCreateRequest;
import ru.team24.service.dto.request.RequestDto;

import ru.team24.service.dto.request.RequestWithCandidateDto;
import ru.team24.service.payload.request.RequestCreationRequest;
import ru.team24.service.payload.request.RequestStatusRequest;
import ru.team24.service.payload.request.CandidateResponse;
import java.util.List;

public interface RequestService {
    RequestWithCandidateDto findByRequestId(long requestId);

    List<RequestWithCandidateDto> getByUserId(long userId);

    List<RequestWithCandidateDto> getByRequestState(String state);

    void updateRequestByRequestId(long requestId, RequestDto request);

    boolean isRequestPending(RequestStatusRequest statusRequest);

    void updateRequest(CandidateResponse updateRequest);

    Page<RequestWithCandidateDto> findRequests(Long userId, String state, Pageable pageable);

    void createRequestWithTokenByClient(ActionCreateRequest action) throws JsonProcessingException;

    void createRequests(RequestCreationRequest createRequest, Long userId) throws JsonProcessingException;

    void softDeleteRequest(long requestId);


}
