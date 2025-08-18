package ru.team24.controller.future;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.team24.controller.future.dto.EmailsDto;
import ru.team24.service.dto.CandidateDto;
import ru.team24.service.security.UserDetailsImpl;

import java.util.List;
import java.util.Map;

@Tag(name = "GENERATOR LINK. Генерация ссылок", description = "Endpoint для генерации ссылок на измение данных кандид")
public interface GeneratorLinkController {

    public ResponseEntity<Map<String, String>> link(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody EmailsDto emails);

    @Operation(
            summary = "Обновление данных кандидата по временной ссылке",
            description = "Позволяет обновить данные кандидата с использованием временного токена из сгенерированной ссылки"
    )

    ResponseEntity<String> updateCandidate(
            @RequestParam String token,
            @RequestBody CandidateDto candidateData
    );
}
