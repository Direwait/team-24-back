package ru.team24.controller.domain.manager.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import ru.team24.service.domain.manager.CandidateService;
import ru.team24.service.dto.candidate.CandidateDto;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CandidateControllerImplTest {

    @Mock
    private CandidateService candidateService;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @InjectMocks
    private CandidateControllerImpl candidateController;

    @Test
    void findCandidateId_ShouldReturnCandidateAndOkStatus() {
        // Arrange
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setCandidateId(1L);
        when(candidateService.findCandidateById(anyLong())).thenReturn(candidateDto);

        // Act
        ResponseEntity<?> response = candidateController.findCandidateId(1L);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(candidateDto, response.getBody());
        verify(candidateService).findCandidateById(1L);
    }

    @Test
    void findCandidateId_WithNonExistentId_ShouldReturnOkWithNull() {
        // Arrange
        when(candidateService.findCandidateById(anyLong())).thenReturn(null);

        // Act
        ResponseEntity<?> response = candidateController.findCandidateId(999L);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
        verify(candidateService).findCandidateById(999L);
    }

    @Test
    void findAllCandidates_ShouldReturnListOfCandidatesAndOkStatus() {
        // Arrange
        CandidateDto candidateDto = new CandidateDto();
        List<CandidateDto> candidates = List.of(candidateDto);
        when(candidateService.findAllCandidates()).thenReturn(candidates);

        // Act
        ResponseEntity<List<?>> response = candidateController.findAllCandidates();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(candidates, response.getBody());
        verify(candidateService).findAllCandidates();
    }

    @Test
    void findAllCandidates_WhenNoCandidates_ShouldReturnEmptyListAndOkStatus() {
        // Arrange
        List<CandidateDto> emptyList = List.of();
        when(candidateService.findAllCandidates()).thenReturn(emptyList);

        // Act
        ResponseEntity<List<?>> response = candidateController.findAllCandidates();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
        verify(candidateService).findAllCandidates();
    }
}