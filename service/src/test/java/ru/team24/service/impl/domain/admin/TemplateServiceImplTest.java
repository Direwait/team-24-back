package ru.team24.service.impl.domain.admin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.team24.database.entities.Template;
import ru.team24.database.repositories.TemplateRepository;
import ru.team24.service.dto.TemplateDto;
import ru.team24.service.mapper.TemplateMapper;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TemplateServiceImplTest {

    @Mock
    private TemplateRepository templateRepository;

    @Mock
    private TemplateMapper templateMapper;

    @InjectMocks
    private TemplateServiceImpl templateService;

    private Template template;
    private TemplateDto templateDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        template = new Template();
        template.setTemplateId(1L);
        template.setTemplateIsActive(true);

        templateDto = new TemplateDto();
        templateDto.setTemplateId(1L);
    }

    @Test
    void findTemplateById_shouldReturnTemplateDto() {
        when(templateRepository.findTopByOrderByTemplateIdDesc()).thenReturn(Optional.of(template));
        when(templateMapper.entityToDto(template)).thenReturn(templateDto);

        TemplateDto result = templateService.findTemplateById(1L);

        assertEquals(templateDto, result);
    }

    @Test
    void findTemplateById_shouldThrowException_whenNotFound() {
        when(templateRepository.findTopByOrderByTemplateIdDesc()).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> templateService.findTemplateById(1L));
    }

    @Test
    void deleteTemplate_shouldSetIsActiveFalseAndSave() {
        when(templateRepository.findTopByOrderByTemplateIdDesc()).thenReturn(Optional.of(template));

        templateService.deleteTemplate(1L);

        assertFalse(template.isTemplateIsActive());
        verify(templateRepository, times(1)).save(template);
    }

    @Test
    void deleteTemplateReal_shouldCallDeleteById() {
        templateService.deleteTemplateReal(1L);
        verify(templateRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateTemplateById_shouldSaveTemplate() {
        when(templateMapper.dtoToEntity(templateDto)).thenReturn(template);

        templateService.updateTemplateById(1L, templateDto);

        assertEquals(1L, template.getTemplateId());
        verify(templateRepository, times(1)).save(template);
    }

    @Test
    void addTemplate_shouldSetUpdatedAtAndSave() {
        when(templateMapper.dtoToEntity(templateDto)).thenReturn(template);

        templateService.addTemplate(templateDto);

        assertNotNull(template.getTemplateUpdatedAt());
        verify(templateRepository, times(1)).save(template);
    }

    @Test
    void findRecentTemplate_shouldReturnTemplateDto() {
        when(templateRepository.findTopByOrderByTemplateIdDesc()).thenReturn(Optional.of(template));
        when(templateMapper.entityToDto(template)).thenReturn(templateDto);

        TemplateDto result = templateService.findRecentTemplate();

        assertEquals(templateDto, result);
    }

    @Test
    void findRecentTemplate_shouldThrowException_whenNotFound() {
        when(templateRepository.findTopByOrderByTemplateIdDesc()).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> templateService.findRecentTemplate());
    }
}
