package ru.team24.service.domain.manager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.team24.service.domain.manager.observ.action.ActionRegisterNewCandidate;
import ru.team24.service.dto.RequestDto;

import ru.team24.service.payload.request.RequestStatusRequest;
import ru.team24.service.payload.request.RequestUpdateRequest;
import java.util.List;

public interface RequestService {
    RequestDto findByRequestId(long requestId);

    List<RequestDto> getByUserId(long userId);

    List<RequestDto> getByRequestState(String state);

    void updateRequestByRequestId(long requestId, RequestDto request);

    boolean isRequestPending(RequestStatusRequest statusRequest);

    void updateRequest(RequestUpdateRequest updateRequest);

    //палидация запросов
    Page<RequestDto> getRequestsPage(Pageable pageable);

    void createRequestWithTokenByClint(ActionRegisterNewCandidate action);

}
