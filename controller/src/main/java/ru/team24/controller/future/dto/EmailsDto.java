package ru.team24.controller.future.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmailsDto {
    List<String> emails;
}
