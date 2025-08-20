package ru.team24.service.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequest {

    @NotBlank(message = "Email не может быть пустым")
    @NotNull(message = "Email обязателен для заполнения")
    @Email(message = "Некорректный формат email")
    @Size(max = 100, message = "Email не может быть длиннее 100 символов")
    private String email;

    @NotBlank(message = "Пароль не может быть пустым")
    @NotNull(message = "Пароль обязателен для заполнения")
    @Size(min = 4, max = 50, message = "Пароль должен быть от 6 до 50 символов")
    private String password;
}
