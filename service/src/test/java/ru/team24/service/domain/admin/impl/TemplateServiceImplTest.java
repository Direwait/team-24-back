package ru.team24.service.domain.admin.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.team24.database.domain.admin.entity.Template;
import ru.team24.database.domain.admin.repository.TemplateRepository;
import ru.team24.database.domain.general.entity.User;
import ru.team24.database.domain.general.repository.UserRepository;
import ru.team24.service.dto.TemplateDto;
import ru.team24.service.mapper.TemplateMapper;

import jakarta.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TemplateServiceImplTest {

    @Mock
    private TemplateMapper templateMapper;

    @Mock
    private TemplateRepository templateRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TemplateServiceImpl templateService;

    private Template testTemplate;
    private TemplateDto testTemplateDto;
    private User testUser;
    private final long TEST_TEMPLATE_ID = 1L;
    private final long TEST_USER_ID = 3L;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setUserId(TEST_USER_ID);

        testTemplate = new Template();
        testTemplate.setTemplateId(TEST_TEMPLATE_ID);
        testTemplate.setTemplateIsActive(true);
        testTemplate.setTemplateCreatedAt(new Date());
        testTemplate.setTemplateUpdatedAt(new Date());
        testTemplate.setUser(testUser);

        testTemplateDto = new TemplateDto();
        testTemplateDto.setTemplateId(TEST_TEMPLATE_ID);
        testTemplateDto.setTemplateIsActive(true);
        testTemplateDto.setTemplateCreatedAt(new Date());
        testTemplateDto.setTemplateUpdatedAt(new Date());
    }

    @Test
    void findTemplateById_shouldReturnTemplateDto_whenTemplateExistsAndActive() {
        when(templateRepository.findTopByOrderByTemplateIdDesc()).thenReturn(Optional.of(testTemplate));
        when(templateMapper.entityToDto(testTemplate)).thenReturn(testTemplateDto);


        TemplateDto result = templateService.findTemplateById(TEST_TEMPLATE_ID);


        assertNotNull(result);
        assertEquals(TEST_TEMPLATE_ID, result.getTemplateId());
        verify(templateRepository).findTopByOrderByTemplateIdDesc();
        verify(templateMapper).entityToDto(testTemplate);
    }

    @Test
    void findTemplateById_shouldThrowException_whenTemplateNotFound() {
        when(templateRepository.findTopByOrderByTemplateIdDesc()).thenReturn(Optional.empty());


        assertThrows(EntityNotFoundException.class, () -> templateService.findTemplateById(TEST_TEMPLATE_ID));
        verify(templateRepository).findTopByOrderByTemplateIdDesc();
        verify(templateMapper, never()).entityToDto(any());
    }

    @Test
    void findTemplateById_shouldThrowException_whenTemplateNotActive() {
        testTemplate.setTemplateIsActive(false);
        when(templateRepository.findTopByOrderByTemplateIdDesc()).thenReturn(Optional.of(testTemplate));


        assertThrows(EntityNotFoundException.class, () -> templateService.findTemplateById(TEST_TEMPLATE_ID));
        verify(templateRepository).findTopByOrderByTemplateIdDesc();
        verify(templateMapper, never()).entityToDto(any());
    }

    @Test
    void deleteTemplate_shouldSetTemplateInactive() {
        when(templateRepository.getReferenceById(TEST_TEMPLATE_ID)).thenReturn(testTemplate);


        templateService.deleteTemplate(TEST_TEMPLATE_ID);


        assertFalse(testTemplate.isTemplateIsActive());
        verify(templateRepository).getReferenceById(TEST_TEMPLATE_ID);
    }

    @Test
    void deleteTemplateReal_shouldDeleteTemplate() {

        templateService.deleteTemplateReal(TEST_TEMPLATE_ID);


        verify(templateRepository).deleteById(TEST_TEMPLATE_ID);
    }

    @Test
    void updateTemplateById_shouldUpdateTemplate() {
        TemplateDto updatedDto = new TemplateDto();

        Template updatedTemplate = new Template();
        updatedTemplate.setTemplateId(TEST_TEMPLATE_ID);

        when(templateMapper.dtoToEntity(updatedDto)).thenReturn(updatedTemplate);
        when(templateRepository.save(updatedTemplate)).thenReturn(updatedTemplate);


        templateService.updateTemplateById(TEST_TEMPLATE_ID, updatedDto);


        verify(templateMapper).dtoToEntity(updatedDto);
        verify(templateRepository).save(updatedTemplate);
        assertEquals(TEST_TEMPLATE_ID, updatedTemplate.getTemplateId()); // Проверяем, что ID установлен правильно
    }

    @Test
    void addTemplate_shouldCreateNewTemplate() {
        TemplateDto newTemplateDto = new TemplateDto();
        Template newTemplate = new Template();

        when(templateMapper.dtoToEntity(newTemplateDto)).thenReturn(newTemplate);
        when(userRepository.findById(TEST_USER_ID)).thenReturn(Optional.of(testUser));
        when(templateRepository.save(newTemplate)).thenReturn(newTemplate);


        templateService.addTemplate(newTemplateDto);


        verify(templateMapper).dtoToEntity(newTemplateDto);
        verify(userRepository).findById(TEST_USER_ID);
        verify(templateRepository).save(newTemplate);

        assertNull(newTemplate.getTemplateId());
        assertTrue(newTemplate.isTemplateIsActive());
        assertNotNull(newTemplate.getTemplateUpdatedAt());
        assertEquals(testUser, newTemplate.getUser());
    }

    @Test
    void addTemplate_shouldThrowException_whenUserNotFound() {
        TemplateDto newTemplateDto = new TemplateDto();
        Template newTemplate = new Template();

        when(templateMapper.dtoToEntity(newTemplateDto)).thenReturn(newTemplate);
        when(userRepository.findById(TEST_USER_ID)).thenReturn(Optional.empty());


        assertThrows(EntityNotFoundException.class, () -> templateService.addTemplate(newTemplateDto));
        verify(templateMapper).dtoToEntity(newTemplateDto);
        verify(userRepository).findById(TEST_USER_ID);
        verify(templateRepository, never()).save(any());
    }

    @Test
    void findRecentTemplate_shouldReturnMostRecentTemplate() {
        when(templateRepository.findTopByOrderByTemplateIdDesc()).thenReturn(Optional.of(testTemplate));
        when(templateMapper.entityToDto(testTemplate)).thenReturn(testTemplateDto);


        TemplateDto result = templateService.findRecentTemplate();


        assertNotNull(result);
        assertEquals(TEST_TEMPLATE_ID, result.getTemplateId());
        verify(templateRepository).findTopByOrderByTemplateIdDesc();
        verify(templateMapper).entityToDto(testTemplate);
    }

    @Test
    void findRecentTemplate_shouldThrowException_whenNoTemplatesExist() {
        when(templateRepository.findTopByOrderByTemplateIdDesc()).thenReturn(Optional.empty());


        assertThrows(EntityNotFoundException.class, () -> templateService.findRecentTemplate());
        verify(templateRepository).findTopByOrderByTemplateIdDesc();
        verify(templateMapper, never()).entityToDto(any());
    }
}