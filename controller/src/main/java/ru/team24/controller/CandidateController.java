package ru.team24.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

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
            summary = "Добавление нового кандидата"
    )
    ResponseEntity<?> addCandidate();
}
