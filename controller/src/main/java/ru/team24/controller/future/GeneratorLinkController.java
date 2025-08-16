package ru.team24.controller.future;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.team24.service.dto.CandidateDto;
import ru.team24.service.security.UserDetailsImpl;

import java.util.Map;

@Tag(name = "GENERATOR LINK. Генерация ссылок", description = "Endpoint для генерации ссылок на измение данных кандид")
public interface GeneratorLinkController {

    public ResponseEntity<Map<String, String>> generateLink(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam long candidateId,
            @RequestParam long templateId,
            @RequestParam long sopdId);

    @Operation(
            summary = "Обновление данных кандидата по временной ссылке",
            description = "Позволяет обновить данные кандидата с использованием временного токена из сгенерированной ссылки"
    )
    ResponseEntity<String> updateCandidate(
            @RequestParam String token,
            @RequestBody CandidateDto candidateData
    );
}
