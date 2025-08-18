package ru.team24.service.dto;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TemplateDto {

    private long templateId;

    private long userId;

    private String templateName;

    private String templateSubject;

    private String templateBody;

    private Date templateCreatedAt;

    private Date templateUpdatedAt;

    private boolean templateIsActive;
}
