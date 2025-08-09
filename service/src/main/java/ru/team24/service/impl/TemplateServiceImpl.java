package ru.team24.service.impl;

import org.springframework.stereotype.Service;
import ru.team24.database.entities.Template;
import ru.team24.service.interfaces.TemplateService;

@Service
public class TemplateServiceImpl implements TemplateService {
    public Template findTemplateById(long templateId) {
        return null;
    }

    public void addTemplate(String templateName, String templateSubject, String templateBody, String templateText) {

    }
}
