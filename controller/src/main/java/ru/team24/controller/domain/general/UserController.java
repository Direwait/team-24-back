package ru.team24.controller.domain.general;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.team24.service.dto.user.UserDto;
import ru.team24.service.dto.user.UserDtoWithRoleDto;
import ru.team24.service.payload.request.AddNewUserRequest;


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
            summary = "Получение страницы с пользователями с возможной фильтрацией по ролям пользователей"
    )
    public ResponseEntity<PagedModel<EntityModel<UserDtoWithRoleDto>>> getUsers(
            @RequestParam(required = false) String role,
            @PageableDefault Pageable pageable,
            PagedResourcesAssembler<UserDtoWithRoleDto> assembler);

    @Operation(
            summary = "Добавление пользователя"
    )
    ResponseEntity<?> addUser(@RequestBody AddNewUserRequest userRequest);

    @Operation(
            summary = "Мягкое удаление пользователя"
    )
    ResponseEntity<?> softDeleteUserById(@PathVariable long userId);

}
