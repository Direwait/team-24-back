package ru.team24.service.interfaces;

import ru.team24.service.dto.TemplateDto;

public interface TemplateService {
    TemplateDto findTemplateById(long templateId);
    void updateTemplateById(long templateId, TemplateDto templateDto);
    void addTemplate(TemplateDto templateDto);
}
