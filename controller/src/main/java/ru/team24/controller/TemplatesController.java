package ru.team24.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Templates. Шаблоны писем на согласие СОПД", description = "Операции связанные с шаблонами писем-СОПД")
public interface TemplatesController {

    @Operation(
            summary = "Получение шаблона по Id"
    )
    ResponseEntity<?> findTemplateById(long id);
}
