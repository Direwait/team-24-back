package ru.team24.service.domain.manager.impl;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import ru.team24.database.domain.admin.entity.Sopd;
import ru.team24.database.domain.admin.entity.Template;
import ru.team24.database.domain.general.entity.User;
import ru.team24.database.domain.manager.entity.Candidate;
import ru.team24.database.domain.manager.entity.Request;
import ru.team24.database.domain.admin.repository.SopdRepository;
import ru.team24.database.domain.admin.repository.TemplateRepository;
import ru.team24.database.domain.general.repository.UserRepository;
import ru.team24.database.domain.manager.repository.CandidateRepository;
import ru.team24.database.domain.manager.repository.RequestRepository;
import ru.team24.database.enums.RequestState;
import ru.team24.service.dto.request.RequestDto;
import ru.team24.service.dto.request.RequestWithCandidateDto;
import ru.team24.service.mapper.CandidateMapper;
import ru.team24.service.mapper.RequestMapper;
import ru.team24.service.mapper.TemplateMapper;
import ru.team24.service.payload.request.CandidateResponse;
import ru.team24.service.payload.request.RequestStatusRequest;
import ru.team24.service.domain.manager.tokenLinkManager.TokenManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RequestServiceImplTest {

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Mock
    private TemplateMapper templateMapper;

    @Mock
    private CandidateMapper candidateMapper;

    @Mock
    private RequestMapper requestMapper;

    @Mock
    private RequestRepository requestRepository;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TemplateRepository templateRepository;

    @Mock
    private SopdRepository sopdRepository;

    @Mock
    private TokenManager tokenManager;

    @InjectMocks
    private RequestServiceImpl requestService;

    private Request testRequest;
    private Candidate testCandidate;
    private User testUser;
    private Template testTemplate;
    private Sopd testSopd;
    private RequestDto testRequestDto;
    private RequestWithCandidateDto testRequestWithCandidateDto;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setUserId(1L);
        testUser.setUserMail("manager@example.com");

        testCandidate = new Candidate();
        testCandidate.setCandidateId(1L);
        testCandidate.setCandidateMail("candidate@example.com");

        testRequest = new Request();
        testRequest.setRequestId(1L);
        testRequest.setRequestState(RequestState.PENDING);
        testRequest.setRequestIsActive(true);
        testRequest.setUser(testUser);
        testRequest.setCandidate(testCandidate);
        testRequest.setRequestToken("test-token");

        testTemplate = new Template();
        testTemplate.setTemplateId(1L);
        testTemplate.setTemplateSubject("Test Subject");
        testTemplate.setTemplateBody("Test Body");

        testSopd = new Sopd();
        testSopd.setSopdId(1L);

        testRequestDto = new RequestDto();
        testRequestDto.setRequestId(1L);
        testRequestDto.setRequestState(RequestState.PENDING);

        testRequestWithCandidateDto = new RequestWithCandidateDto();
        testRequestWithCandidateDto.setRequestId(1L);
    }

    @Test
    void findByRequestId_shouldReturnRequestWithCandidateDto() {
        when(requestRepository.findById(1L)).thenReturn(Optional.of(testRequest));
        when(requestMapper.entityToDtoWithCandidate(testRequest)).thenReturn(testRequestWithCandidateDto);

        RequestWithCandidateDto result = requestService.findByRequestId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getRequestId());
        verify(requestRepository).findById(1L);
    }

    @Test
    void findByRequestId_shouldThrowExceptionWhenNotFound() {
        when(requestRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> requestService.findByRequestId(1L));
        verify(requestRepository).findById(1L);
    }

    @Test
    void findRequests_shouldReturnAllRequestsWhenStateIsAll() {
        Pageable pageable = Pageable.ofSize(10);
        Page<Request> page = new PageImpl<>(List.of(testRequest));

        when(requestRepository.findAllByUser_UserIdAndRequestIsActiveOrderByRequestDate(1L, true, pageable))
                .thenReturn(page);
        when(requestMapper.entityToDtoWithCandidate(testRequest)).thenReturn(testRequestWithCandidateDto);

        Page<RequestWithCandidateDto> result = requestService.findRequests(1L, "all", pageable);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(requestRepository).findAllByUser_UserIdAndRequestIsActiveOrderByRequestDate(1L, true, pageable);
    }

    @Test
    void findRequests_shouldReturnFilteredRequestsByState() {
        Pageable pageable = Pageable.ofSize(10);
        Page<Request> page = new PageImpl<>(List.of(testRequest));

        when(requestRepository.findAllByUser_UserIdAndRequestStateAndRequestIsActiveOrderByRequestDate(
                1L, RequestState.PENDING, true, pageable))
                .thenReturn(page);
        when(requestMapper.entityToDtoWithCandidate(testRequest)).thenReturn(testRequestWithCandidateDto);

        Page<RequestWithCandidateDto> result = requestService.findRequests(1L, "pending", pageable);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(requestRepository).findAllByUser_UserIdAndRequestStateAndRequestIsActiveOrderByRequestDate(
                1L, RequestState.PENDING, true, pageable);
    }

    @Test
    void isRequestPending_shouldReturnTrueWhenRequestIsPending() {
        RequestStatusRequest statusRequest = new RequestStatusRequest();
        statusRequest.setToken("test-token");

        when(requestRepository.findByRequestToken("test-token")).thenReturn(Optional.of(testRequest));

        boolean result = requestService.isRequestPending(statusRequest);

        assertTrue(result);
        verify(requestRepository).findByRequestToken("test-token");
    }

    @Test
    void softDeleteRequest_shouldSetRequestInactive() {
        when(requestRepository.findByRequestId(1L)).thenReturn(Optional.of(testRequest));

        requestService.softDeleteRequest(1L);

        assertFalse(testRequest.isRequestIsActive());
        verify(requestRepository).save(testRequest);
    }

    @Test
    void softDeleteRequest_shouldDoNothingWhenRequestNotFound() {
        when(requestRepository.findByRequestId(1L)).thenReturn(Optional.empty());

        requestService.softDeleteRequest(1L);

        verify(requestRepository, never()).save(any());
    }

    @Test
    void deleteRequest_shouldDeleteById() {
        requestService.hardDeleteRequest(1L);

        verify(requestRepository, never()).save(any());
    }
}