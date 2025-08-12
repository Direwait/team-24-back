package ru.team24.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ru.team24.service.dto.UserDto;

import java.util.List;

@Tag(name = "Users. Менеджер и Админ", description = "Операции связанные с менеджерами и админами")
public interface UserController {

    @Operation(
            summary = "получение пользователя по ID"
    )
    ResponseEntity<?> findByUserId(long id);

    @Operation(
            summary = "Проверка на наличие пользователя по почте",
            description = """
                            Я думаю почту лучше передавать через тело, либо хеш, но не
                            напрямую через url
                          """
    )
    ResponseEntity<?> existsByEmail(String mail);

    @Operation(
            summary = "Получение списка всех пользователей"
    )
    ResponseEntity<List<?>> findAllUsers();

    @Operation(
            summary = "Добавление пользователя"
    )
    ResponseEntity<?> addUser(@RequestBody UserDto userDto);

}
