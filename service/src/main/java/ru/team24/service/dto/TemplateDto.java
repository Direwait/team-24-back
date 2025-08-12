package ru.team24.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TemplateDto {

    private long templateId;

    private String templateName;

    private String templateSubject;

    private String templateBody;

    private String templateText;
}
