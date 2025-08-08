package ru.team24.service.interfaces;

import ru.team24.database.entities.Template;

public interface TemplateService {
    Template findTemplateById(long templateId);
    void addTemplate(String templateName,
                     String templateSubject,
                     String templateBody,
                     String templateText);
}
