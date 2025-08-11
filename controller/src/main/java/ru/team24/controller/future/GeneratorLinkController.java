package ru.team24.controller.future;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.team24.database.entities.Candidate;

import java.util.Map;

@Tag(name = "GENERATOR LINK. Генерация ссылок", description = "Endpoint для генерации ссылок на измение данных кандид")
public interface GeneratorLinkController {

    @Operation(
            summary = "Генерация ссылки для обновления данных",
            description = """
                    Генерирует уникальную ссылку с токеном для безопасного обновления данных кандидата
                    Ccылка вида "http://localhost:8989/api/v1/link/update-form?token= + token
                    
                    токен передаются в url, а это плохо
                    """,
            parameters = {
                    @Parameter(name = "candidateId", description = "ID кандидата", required = true, example = "1"),
                    @Parameter(name = "templateId", description = "ID шаблона формы", required = true, example = "1"),
                    @Parameter(name = "userId", description = "ID пользователя-инициатора", required = true, example = "1")
            }
    )
    ResponseEntity<Map<String, String>> generateLink(
            @RequestParam Long candidateId,
            @RequestParam Long templateId,
            @RequestParam Long userId);

    @Operation(
            summary = "Обновление данных кандидата по временной ссылке",
            description = "Позволяет обновить данные кандидата с использованием временного токена из сгенерированной ссылки"
    )
    ResponseEntity<String> updateCandidate(
            @RequestParam String token,
            @RequestBody Candidate candidateData
    );
}
