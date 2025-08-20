package ru.team24.controller.domain.manager;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.team24.service.dto.request.RequestDto;
import ru.team24.service.dto.request.RequestWithCandidateDto;
import ru.team24.service.payload.request.CandidateResponse;
import ru.team24.service.security.UserDetailsImpl;

import java.util.List;

@Tag(name = "Request. Запросы кандидатам", description = "Операции связанные с запросами-СОПД")
public interface RequestController {

    @Operation(
            summary = "Получение кандидата по Id Запроса-СОПД"
    )
    ResponseEntity<?> findByRequestId(long id);


    @Operation(
            summary = "Получение списка запросов-СОПД по статусу. Request State"
    )
    ResponseEntity<List<RequestWithCandidateDto>> getByRequestState(String state);

    @Operation(
            summary = "Обновление запроса-СОПД по Id"
    )
    ResponseEntity<?> updateRequestByRequestId(long requestId, RequestDto requestDto);

    ResponseEntity<?> updateRequest(@RequestBody CandidateResponse requestUpdateRequest);

    @Operation(
            summary = "Получение страницы запросов с пагинацией",
            description = "Возвращает список запросов с информацией о страницах"
    )

    ResponseEntity<PagedModel<EntityModel<RequestWithCandidateDto>>> getRequests(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam(required = false) String state,
            @PageableDefault Pageable pageable,
            PagedResourcesAssembler<RequestWithCandidateDto> assembler);

}
