package ru.team24.service.domain.manager.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import ru.team24.database.domain.manager.entity.Candidate;
import ru.team24.database.domain.manager.repository.CandidateRepository;
import ru.team24.database.domain.manager.repository.RequestRepository;
import ru.team24.service.dto.candidate.CandidateDto;
import ru.team24.service.mapper.CandidateMapper;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CandidateServiceImplTest {

    @Mock
    private ApplicationEventPublisher publisher;

    @Mock
    private CandidateMapper candidateMapper;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private RequestRepository requestRepository;

    @InjectMocks
    private CandidateServiceImpl candidateService;

    @Test
    void findCandidateById_shouldReturnCandidateDto() {
        Candidate candidate = new Candidate();
        candidate.setCandidateId(1L);
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setCandidateId(1L);

        when(candidateRepository.findById(1L)).thenReturn(Optional.of(candidate));
        when(candidateMapper.entityToDto(candidate)).thenReturn(candidateDto);


        CandidateDto result = candidateService.findCandidateById(1L);


        assertNotNull(result);
        assertEquals(1L, result.getCandidateId());
    }

    @Test
    void findCandidateById_shouldReturnNullWhenNotFound() {
        when(candidateRepository.findById(1L)).thenReturn(Optional.empty());

        CandidateDto result = candidateService.findCandidateById(1L);

        assertNull(result);
    }

    @Test
    void findAllCandidates_shouldReturnList() {
        Candidate candidate = new Candidate();
        CandidateDto candidateDto = new CandidateDto();

        when(candidateRepository.findAll()).thenReturn(List.of(candidate));
        when(candidateMapper.entityToDto(candidate)).thenReturn(candidateDto);

        List<CandidateDto> result = candidateService.findAllCandidates();

        assertEquals(1, result.size());
    }

    @Test
    void addCandidateByMail_shouldDoNothingWhenMailsNull() {
        candidateService.addCandidateByMail(null, 1L);

        verifyNoInteractions(candidateMapper, candidateRepository, publisher);
    }

    @Test
    void addCandidateByMail_shouldDoNothingWhenMailsEmpty() {
        candidateService.addCandidateByMail(List.of(), 1L);

        verifyNoInteractions(candidateMapper, candidateRepository, publisher);
    }
}