package ru.team24.service.domain.manager.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.team24.database.domain.admin.repository.SopdRepository;
import ru.team24.database.domain.admin.repository.TemplateRepository;
import ru.team24.database.domain.manager.entity.Request;
import ru.team24.database.domain.manager.repository.CandidateRepository;
import ru.team24.database.domain.general.repository.UserRepository;
import ru.team24.database.domain.manager.repository.RequestRepository;
import ru.team24.service.observ.ActionType;
import ru.team24.service.observ.action.ActionCreateRequest;
import ru.team24.service.observ.action.ActionSendLetterCandidate;
import ru.team24.service.observ.action.ActionSendLetterToManager;
import ru.team24.service.dto.request.RequestDto;
import ru.team24.database.enums.RequestState;
import ru.team24.service.domain.manager.RequestService;
import ru.team24.service.domain.manager.tokenLinkManager.TokenManager;
import ru.team24.service.dto.request.RequestWithCandidateDto;
import ru.team24.service.mapper.CandidateMapper;
import ru.team24.service.mapper.RequestMapper;
import ru.team24.service.mapper.TemplateMapper;
import ru.team24.service.payload.request.RequestStatusRequest;
import ru.team24.service.payload.request.CandidateResponse;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final ApplicationEventPublisher publisher;

    private final TemplateMapper templateMapper;
    private final CandidateMapper candidateMapper;
    private final RequestMapper requestMapper;

    private final RequestRepository requestRepository;
    private final CandidateRepository candidateRepository;
    private final UserRepository userRepository;
    private final TemplateRepository templateRepository;
    private final SopdRepository sopdRepository;

    private final TokenManager linkService;

    @Override
    public RequestWithCandidateDto findByRequestId(long requestId) {
        return requestRepository.findById(requestId)
                .map(requestMapper::entityToDtoWithCandidate)
                .orElseThrow(() -> new EntityNotFoundException("Request not found with id: " + requestId));
    }


    @Override
    public Page<RequestWithCandidateDto> findRequests(
            long userId,
            String state,
            Pageable pageable) {
        Page<Request> page;
        if (state == null || state.isEmpty() || state.equalsIgnoreCase("all")) {
            page = requestRepository.findAllByUser_UserIdAndRequestIsActiveOrderByRequestDate(
                    userId,
                    true,
                    pageable);
        } else {
            RequestState requestState;
            try {
                requestState = RequestState.valueOf(state.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid request state: " + state +
                        ". Valid values: " + Arrays.toString(RequestState.values()));
            }
            page = requestRepository
                    .findAllByUser_UserIdAndRequestStateAndRequestIsActiveOrderByRequestDateDesc(
                            userId,
                            requestState,
                            true,
                            pageable
                    );
        }
        return page.map(requestMapper::entityToDtoWithCandidate);
    }

    @Override
    public void deleteRequest(long requestId) {
        requestRepository.deleteById(requestId);
    }

    @Override
    public void deleteRequestReal(long requestId) {
        requestRepository.getReferenceById(requestId).setRequestIsActive(false);
    }

    @Override
    public void updateRequestByRequestId(long requestId, RequestDto request) {
        request.setRequestId(requestId);
        requestRepository.save(requestMapper.dtoToEntity(request));
    }

    @Override
    public boolean isRequestPending(RequestStatusRequest statusRequest) {
        var request = requestRepository.findByRequestToken(statusRequest.getToken()).orElseThrow();
        return request.getRequestState() == RequestState.PENDING;
    }

    @Override
    public void updateRequest(CandidateResponse updateRequest) {

        var candidate = candidateRepository.findByCandidateMail(updateRequest.getCandidateMail())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Candidate with email %s not found", updateRequest.getCandidateMail())
                ));

        candidateMapper.updateCandidateFromResponse(updateRequest, candidate);
        candidateRepository.save(candidate);

        var request = requestRepository.findByCandidate(candidate)
                .orElseThrow(() -> new EntityNotFoundException(
                String.format("Request with candidate %s not found", updateRequest.getCandidateMail())
        ));

        request.setRequestState(updateRequest.getRequestState());
        requestRepository.save(request);

        var user = userRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException(
                String.format("User with Id %s not found", request.getRequestId())
        ));

        ActionSendLetterToManager letterManager = ActionSendLetterToManager.builder()
                .managerMail(user.getUserMail())
                .candidateMail(updateRequest.getCandidateMail())
                .requestState(String.valueOf(updateRequest.getRequestState()))
                .actionType(ActionType.SEND)
                .build();

        publisher.publishEvent(letterManager);
    }

    @EventListener
    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createRequestWithTokenByClient(ActionCreateRequest action) {

        var oneTimeToken = linkService.generateAccessToken();
        var recentSopdId = sopdRepository.getRecentSopdId();
        var template = templateRepository.findTopByOrderByTemplateIdDesc()
                .map(templateMapper::entityToDto)
                .orElseThrow(() -> new EntityNotFoundException("Template not found in DB"));

        var build = RequestDto.builder()
                .requestDate(new Date())
                .requestState(RequestState.PENDING)
                .userId(action.getUserId())
                .sopdId(recentSopdId)
                .candidateId(action.getCandidateId())
                .templateId(template.getTemplateId())
                .requestToken(oneTimeToken)
                .build();

        var request = requestMapper.dtoToEntity(build);
        requestRepository.save(request);

        ActionSendLetterCandidate letterCandidate = ActionSendLetterCandidate.builder()
                .token(oneTimeToken)
                .templateBody(template.getTemplateBody())
                .templateSubject(template.getTemplateSubject())
                .candidateMail(action.getCandidateMail())
                .actionType(ActionType.SEND)
                .build();
        publisher.publishEvent(letterCandidate);
    }
}
