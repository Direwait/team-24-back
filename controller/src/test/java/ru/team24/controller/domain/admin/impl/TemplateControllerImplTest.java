package ru.team24.controller.domain.admin.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.team24.service.domain.admin.TemplateService;
import ru.team24.service.dto.TemplateDto;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TemplateControllerImplTest {

    @Mock
    private TemplateService templateService;

    @InjectMocks
    private TemplateControllerImpl templateController;

    @Test
    void findRecentTemplate_WithAdminAuthority_ShouldReturnRecentTemplate() {
        setupWithAdminAuthority();
        TemplateDto expectedTemplate = TemplateDto.builder()
                .templateId(1L)
                .templateBody("Test Template")
                .build();
        when(templateService.findRecentTemplate()).thenReturn(expectedTemplate);


        ResponseEntity<TemplateDto> response = templateController.findRecentTemplate();


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedTemplate, response.getBody());
        verify(templateService, times(1)).findRecentTemplate();
    }

    @Test
    void findTemplateById_WithAdminAuthority_ShouldReturnTemplate() {
        setupWithAdminAuthority();
        TemplateDto expectedTemplate = TemplateDto.builder()
                .templateId(1L)
                .templateBody("Specific Template")
                .build();
        when(templateService.findTemplateById(1L)).thenReturn(expectedTemplate);


        ResponseEntity<?> response = templateController.findTemplateById(1L);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedTemplate, response.getBody());
        verify(templateService, times(1)).findTemplateById(1L);
    }

    @Test
    void updateTemplateById_WithAdminAuthority_ShouldUpdateTemplate() {
        setupWithAdminAuthority();
        TemplateDto templateDto = TemplateDto.builder()
                .templateBody("Updated Template Text")
                .build();

        doNothing().when(templateService).updateTemplateById(anyLong(), any(TemplateDto.class));


        ResponseEntity<?> response = templateController.updateTemplateById(1L, templateDto);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
        verify(templateService, times(1)).updateTemplateById(eq(1L), eq(templateDto));
    }

    @Test
    void createTemplate_WithAdminAuthority_ShouldCreateTemplate() {
        setupWithAdminAuthority();

        TemplateDto templateDto = TemplateDto.builder()
                .templateBody("New Template")
                .build();

        doNothing().when(templateService).addTemplate(any(TemplateDto.class));


        ResponseEntity<?> response = templateController.createTemplate(templateDto);


        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNull(response.getBody());
        verify(templateService, times(1)).addTemplate(eq(templateDto));
    }

    @Test
    void deleteTemplate_WithAdminAuthority_ShouldDeleteTemplate() {
        setupWithAdminAuthority();
        doNothing().when(templateService).deleteTemplate(anyLong());


        ResponseEntity<Void> response = templateController.deleteTemplate(1L);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
        verify(templateService, times(1)).deleteTemplate(1L);
    }

    @Test
    void deleteTemplateReal_WithAdminAuthority_ShouldPermanentlyDeleteTemplate() {
        setupWithAdminAuthority();
        doNothing().when(templateService).deleteTemplateReal(anyLong());


        ResponseEntity<Void> response = templateController.deleteTemplateReal(1L);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
        verify(templateService, times(1)).deleteTemplateReal(1L);
    }


    @Test
    void updateTemplateById_ShouldPassCorrectParameters() {
        setupWithAdminAuthority();
        TemplateDto templateDto = TemplateDto.builder()
                .templateId(1L)
                .templateBody("Specific Update")
                .build();
        doNothing().when(templateService).updateTemplateById(anyLong(), any(TemplateDto.class));


        ResponseEntity<?> response = templateController.updateTemplateById(5L, templateDto);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(templateService).updateTemplateById(eq(5L), eq(templateDto));
    }

    @Test
    void createTemplate_ShouldPassCorrectTemplateDto() {

        setupWithAdminAuthority();

        TemplateDto templateDto = TemplateDto.builder()
                .templateBody("New Template Content")
                .build();

        doNothing().when(templateService).addTemplate(any(TemplateDto.class));


        ResponseEntity<?> response = templateController.createTemplate(templateDto);


        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(templateService).addTemplate(eq(templateDto));
    }


    private void setupWithAdminAuthority() {
        TestingAuthenticationToken authentication = new TestingAuthenticationToken(
                "admin",
                "password",
                Collections.singletonList(new SimpleGrantedAuthority("ADMIN"))
        );
        authentication.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}