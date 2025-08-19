package ru.team24.service.domain.admin.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.team24.database.domain.admin.entity.Template;
import ru.team24.service.dto.TemplateDto;
import ru.team24.database.domain.admin.repository.TemplateRepository;
import ru.team24.service.domain.admin.TemplateService;
import ru.team24.service.mapper.TemplateMapper;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class TemplateServiceImpl implements TemplateService {
    private final TemplateMapper templateMapper;
    private final TemplateRepository templateRepository;

    @Override
    public TemplateDto findTemplateById(long templateId) {
        var template = templateRepository.findTopByOrderByTemplateIdDesc()
                .filter(Template::isTemplateIsActive)
                .orElseThrow(() -> {
            return new EntityNotFoundException("Template by Id"+ templateId + " + not found");
        });
        return templateMapper.entityToDto(template);
    }

    @Override
    public void deleteTemplate(long templateId) {
        templateRepository.getReferenceById(templateId).setTemplateIsActive(false);
    }

    @Override
    public void deleteTemplateReal(long templateId) {
        templateRepository.deleteById(templateId);
    }

    @Override
    public void updateTemplateById(long templateId, TemplateDto templateDto) {
        var template = templateMapper.dtoToEntity(templateDto);
        template.setTemplateId(templateId);
        templateRepository.save(template);
    }

    @Override
    public void addTemplate(TemplateDto templateDto) {
        var template = templateMapper.dtoToEntity(templateDto);
        template.setTemplateUpdatedAt(new Date());
        templateRepository.save(template);
    }

    @Override
    public TemplateDto findRecentTemplate() {
        var template = templateRepository.findTopByOrderByTemplateIdDesc().orElseThrow(() -> {
            return new EntityNotFoundException("Recent Template not found");
        });
        return templateMapper.entityToDto(template);
    }

}
