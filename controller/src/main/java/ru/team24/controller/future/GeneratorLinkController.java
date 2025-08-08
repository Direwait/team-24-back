package ru.team24.controller.future;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "GENERATOR LINK. Генерация ссылок", description = "DONT WORK")
public interface GeneratorLinkController {
    ResponseEntity<String> generateLink();
}
