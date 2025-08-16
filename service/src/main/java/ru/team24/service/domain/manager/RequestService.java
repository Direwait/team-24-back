package ru.team24.service.domain.manager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.team24.service.dto.RequestDto;

import ru.team24.service.payload.request.RequestStatusRequest;
import ru.team24.service.payload.request.RequestUpdateRequest;
import java.util.List;

public interface RequestService {
    RequestDto findByRequestId(long requestId);

    List<RequestDto> getByUserId(long userId);

    List<RequestDto> getByRequestState(String state);

    void updateRequestByRequestId(long requestId, RequestDto request);


    //todo должен возвращать dto, в текущей реализации я не трогал
    @Deprecated
    void createRequest(RequestDto requestDto);

    boolean isRequestPending(RequestStatusRequest statusRequest);

    void updateRequest(RequestUpdateRequest updateRequest);

    //палидация запросов
    Page<RequestDto> getRequestsPage(Pageable pageable);

    //создание полного запроса со ссылкой ++
    RequestDto createRequestWithToken(long candidateId, long templateId, long managerId, long sopdId, String token);
}
