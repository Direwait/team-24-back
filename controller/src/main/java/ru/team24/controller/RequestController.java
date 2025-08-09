package ru.team24.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

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
    ResponseEntity<List<?>> getByRequestState(Enum state);

    @Operation(
            summary = "Обновление запроса-СОПД по Id"
    )
    ResponseEntity<?> updateRequestByRequestId(long id);

    @Operation(
            summary = "Добавление нового кандидата"
    )
    ResponseEntity<?> createRequest();
}
