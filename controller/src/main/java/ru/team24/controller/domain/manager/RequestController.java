package ru.team24.controller.domain.manager;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.team24.service.dto.RequestDto;
import ru.team24.service.payload.request.RequestUpdateRequest;

import java.util.List;
import java.util.Map;

@Tag(name = "Request. Запросы кандидатам", description = "Операции связанные с запросами-СОПД")
public interface RequestController {

    @Operation(
            summary = "Получение кандидата по Id Запроса-СОПД"
    )
    ResponseEntity<?> findByRequestId(long id);

    @Operation(
            summary = "Получение кандидата по Id"
    )
    ResponseEntity<?> getByUserId(long id);

    @Operation(
            summary = "Получение списка запросов-СОПД по статусу. Request State"
    )
    ResponseEntity<List<?>> getByRequestState(String state);

    @Operation(
            summary = "Обновление запроса-СОПД по Id"
    )
    ResponseEntity<?> updateRequestByRequestId(long requestId, RequestDto requestDto);

    @Operation(
            summary = "Добавление нового кандидата"
    )
    ResponseEntity<?> createRequest(@RequestBody RequestDto requestDto);

    ResponseEntity<?> updateRequest(@RequestBody RequestUpdateRequest requestUpdateRequest);

    @Operation(
            summary = "Получение страницы запросов с пагинацией",
            description = "Возвращает список запросов с информацией о страницах"
    )

    ResponseEntity<Map<String, Object>> getRequestsPage(@PathVariable int page);

}
