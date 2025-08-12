package ru.team24.service.impl;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;
import ru.team24.service.dto.TemplateDto;
import ru.team24.database.entities.Template;
import ru.team24.database.repositories.TemplateRepository;
import ru.team24.service.interfaces.TemplateService;
import ru.team24.service.mapper.TemplateMapper;

@Service
@RequiredArgsConstructor
public class TemplateServiceImpl implements TemplateService {
    private final TemplateMapper templateMapper;
    private final TemplateRepository templateRepository;

    public TemplateDto findTemplateById(long templateId) {
        var template = templateRepository.findById(templateId).orElse(null);
        return templateMapper.entityToDto(template);
    }

    public void updateTemplateById(long templateId, TemplateDto templateDto) {
        var template = templateMapper.dtoToEntity(templateDto);
        template.setTemplateId(templateId);
        templateRepository.save(template);

    }

    public void addTemplate(TemplateDto templateDto) {
        var template = templateMapper.dtoToEntity(templateDto);
        template.setTemplateId(null); // для авто-генерации ID
        templateRepository.save(template);
    }
}
