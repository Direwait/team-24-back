package ru.team24.controller.domain.manager;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import ru.team24.service.dto.candidate.EmailsDto;
import ru.team24.service.security.UserDetailsImpl;

import java.util.List;
import java.util.Map;

@Tag(name = "Candidates. Кандидаты", description = "Операции связанные с кандидатами")
public interface CandidateController {

    @Operation(
            summary = "Получение кандидата по Id"
    )
    ResponseEntity<?> findCandidateId(long id);

    @Operation(
            summary = "Список всех кандидатов"
    )
    ResponseEntity<List<?>> findAllCandidates();

    @Operation(
            summary = "Версия 2. Добавление новых кандидатов по почте"
    )
    ResponseEntity<Map<String, String>> link(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody EmailsDto emails
    );
}
