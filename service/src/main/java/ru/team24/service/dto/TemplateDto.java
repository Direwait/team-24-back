package ru.team24.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TemplateDto {

    private long templateId;

    private String templateName;

    private String templateSubject;

    private String templateBody;

    private Date templateCreatedAt;

    private Date templateUpdatedAt;

    private boolean templateIsActive;
}
