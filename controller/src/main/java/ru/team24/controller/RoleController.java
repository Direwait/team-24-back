package ru.team24.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Roles. Роли в системе", description = "Операции связанные с ролями.")
public interface RoleController {

    @Operation(
            summary = "Получение роли по Id"
    )
    ResponseEntity<?> findByRoleId(long id);
}
