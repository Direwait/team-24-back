package ru.team24.service.dto.candidate;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class EmailsDto {

    @NotEmpty(message = "Список emails не может быть пустым")
    List<String> emails;
}
