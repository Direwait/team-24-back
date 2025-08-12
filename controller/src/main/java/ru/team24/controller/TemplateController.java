package ru.team24.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ru.team24.service.dto.TemplateDto;

@Tag(name = "Templates. Шаблоны писем на согласие СОПД", description = "Операции связанные с шаблонами писем-СОПД")
public interface TemplateController {

    @Operation(
            summary = "Получение шаблона по Id"
    )
    ResponseEntity<?> findTemplateById(long id);

    @Operation(
            summary = "Изменение шаблона по Id"
    )
    ResponseEntity<?> updateTemplateById(long templateId, TemplateDto templateDto);

    @Operation(
            summary = "Добавление нового шаблона"
    )
    ResponseEntity<?> createTemplate(TemplateDto templateDto);
}
