package ru.team24.service.domain.manager.impl;

import jakarta.mail.MessagingException;
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
import ru.team24.database.domain.manager.repository.CandidateRepository;
import ru.team24.database.domain.general.repository.UserRepository;
import ru.team24.database.domain.manager.repository.RequestRepository;
import ru.team24.service.domain.manager.observ.ActionType;
import ru.team24.service.domain.manager.observ.action.ActionRegisterNewCandidate;
import ru.team24.service.domain.manager.observ.action.ActionSendLetterCandidate;
import ru.team24.service.dto.RequestDto;
import ru.team24.database.enums.RequestState;
import ru.team24.service.domain.manager.RequestService;
import ru.team24.service.domain.general.EmailService;
import ru.team24.service.domain.manager.tokenLinkManager.TokenManager;
import ru.team24.service.dto.TemplateDto;
import ru.team24.service.mapper.RequestMapper;
import ru.team24.service.mapper.TemplateMapper;
import ru.team24.service.payload.request.RequestStatusRequest;
import ru.team24.service.payload.request.RequestUpdateRequest;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final ApplicationEventPublisher publisher;

    private final RequestMapper requestMapper;
    private final RequestRepository requestRepository;

    private final CandidateRepository candidateRepository;
    private final UserRepository userRepository;

    private final TemplateRepository templateRepository;
    private final SopdRepository sopdRepository;

    private final EmailService emailService;
    private final TemplateMapper templateM;

    private final TokenManager linkService;


    public RequestDto findByRequestId(long requestId) {
        var request = requestRepository.findById(requestId).orElse(null);
        return requestMapper.entityToDto(request);
    }

    public List<RequestDto> getByUserId(long userId) {
        return requestRepository.findAllByUser_UserId(userId).stream()
                .map(requestMapper::entityToDto)
                .toList();
    }

    //переделать у нас Enum RequestState
    public List<RequestDto> getByRequestState(String state) {
        var requests = requestRepository.getByRequestState(RequestState.valueOf(state));
        return requests.stream().map(requestMapper::entityToDto).toList();
    }

    public void updateRequestByRequestId(long requestId, RequestDto request) {
        request.setRequestId(requestId);
        requestRepository.save(requestMapper.dtoToEntity(request));
    }

    public boolean isRequestPending(RequestStatusRequest statusRequest) {
        var request = requestRepository.findByRequestToken(statusRequest.getToken()).orElseThrow();
        return request.getRequestState() == RequestState.PENDING;
    }

    @Override
    public Page<RequestDto> getRequestsPage(Pageable pageable) {
        return requestRepository.findAll(pageable)
                .map(requestMapper::entityToDto);
    }




    ////todo
    //надо пересмотреть
    @Deprecated
    public void updateRequest(RequestUpdateRequest updateRequest) {
        //заменить на маппер
        var candidate = candidateRepository.findByCandidateMail(updateRequest.getCandidateMail()).orElseThrow();
        candidate.setCandidatePhone(updateRequest.getCandidatePhone());
        candidate.setCandidateBirthDate(updateRequest.getCandidateBirthDate());
        candidate.setCandidateFirstName(updateRequest.getCandidateFirstName());
        candidate.setCandidateLastName(updateRequest.getCandidateLastName());
        candidate.setCandidateFatherName(updateRequest.getCandidateFatherName());
        candidateRepository.save(candidate);
        var request = requestRepository.findByCandidate(candidate).orElseThrow();
        request.setRequestState(updateRequest.getRequestState());

        requestRepository.save(request);

        var user = userRepository.findByUserId(request.getUserId()).orElseThrow();
        var template = templateRepository.findByTemplateId(request.getTemplateId()).orElseThrow();
        try {
            emailService.sendEmail(template.getTemplateSubject(), template.getTemplateBody(), user.getUserMail());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    //создание через клиент
    //создание токена и создание запроса к кандидату
    @EventListener
    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW) // каждая асинхронка в своей транзакции
    public void createRequestWithTokenByClint(ActionRegisterNewCandidate action) {

        log.info("внутри реквест сервиса");

        // Всё сразу вытаскиваем из базы и превращаем в DTO/примитивы
        var oneTimeToken = linkService.generateAccessToken();
        var recentSopdId = sopdRepository.getRecentSopdId();

        var template = templateRepository.findTopByOrderByTemplateIdDesc()
                .map(templateM::entityToDto)
                .orElseThrow(() -> new EntityNotFoundException("Template not found in DB"));

        log.info("после загрузки и маппинга");

        // Собираем DTO → потом маппер превратит в Entity, но уже в новом EntityManager-е
        var build = RequestDto.builder()
                .requestDate(new Date())
                .requestState(RequestState.PENDING)
                .userId(action.getUserId())
                .sopdId(recentSopdId)
                .candidateId(action.getCandidateId())
                .templateId(template.getTemplateId())
                .requestToken(oneTimeToken)
                .build();

        log.info("после билда реквеста");

        var request = requestMapper.dtoToEntity(build);
        requestRepository.save(request);
        log.info("после сохранения");

        // Создаем отдельное событие — чистый объект без ссылок на Entity
        ActionSendLetterCandidate letterCandidate = ActionSendLetterCandidate.builder()
                .token(oneTimeToken)
                .templateBody(template.getTemplateBody())
                .templateName(template.getTemplateName())
                .templateSubject(template.getTemplateSubject())
                .candidateMail(action.getCandidateMail())
                .actionType(ActionType.SEND)
                .build();

        publisher.publishEvent(letterCandidate);
    }

    @Deprecated
    @Transactional
    public RequestDto createRequestWithToken(long candidateId, long templateId, long userId, long sopdId, String token) {

        var build = RequestDto.builder()
                .requestDate(new Date())
                .requestState(RequestState.PENDING)
                .userId(userId)
                .sopdId(sopdId)
                .candidateId(candidateId)
                .templateId(templateId)
                .requestToken(token)
                .build();

        var request = requestMapper.dtoToEntity(build);
        requestRepository.save(request);

        return build;
    }

}
