package ru.team24.service.domain.admin;

import ru.team24.service.dto.TemplateDto;

public interface
TemplateService {
    TemplateDto findRecentTemplate();
    TemplateDto findTemplateById(long templateId);
    void updateTemplateById(long templateId, TemplateDto templateDto);
    void addTemplate(TemplateDto templateDto);

    void deleteTemplate(long id);

    void deleteTemplateReal(long id);
}
