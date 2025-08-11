package ru.team24.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.team24.database.dto.TemplateDto;
import ru.team24.database.entities.Template;
import ru.team24.database.repositories.TemplateRepository;
import ru.team24.service.interfaces.TemplateService;
import ru.team24.service.mapper.TemplateMapper;

@Service
@RequiredArgsConstructor
public class TemplateServiceImpl implements TemplateService {
    private final TemplateMapper templateMapper;
    private final TemplateRepository repository;

    public Template findTemplateById(long templateId) {
        return repository.findByTemplateId(templateId).orElseThrow();
    }

    public void addTemplate(String templateName, String templateSubject, String templateBody, String templateText) {
        var template = new Template();
        template.setTemplateName(templateName);
        template.setTemplateSubject(templateSubject);
        template.setTemplateBody(templateBody);
        template.setTemplateText(templateText);
        repository.save(template);
    }
}
