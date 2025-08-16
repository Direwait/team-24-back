package ru.team24.service.domain.manager.impl;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.team24.database.domain.admin.repository.TemplateRepository;
import ru.team24.database.domain.manager.repository.CandidateRepository;
import ru.team24.database.domain.general.repository.UserRepository;
import ru.team24.database.domain.manager.repository.RequestRepository;
import ru.team24.service.dto.RequestDto;
import ru.team24.database.enums.RequestState;
import ru.team24.service.domain.manager.RequestService;
import ru.team24.service.domain.general.EmailService;
import ru.team24.service.domain.manager.tokenLinkManager.tokenManager;
import ru.team24.service.mapper.RequestMapper;
import ru.team24.service.payload.request.RequestStatusRequest;
import ru.team24.service.payload.request.RequestUpdateRequest;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestMapper requestMapper;
    private final RequestRepository requestRepository;

    private final CandidateRepository candidateRepository;
    private final UserRepository userRepository;
    private final TemplateRepository templateRepository;
    private final EmailService emailService;

    private final tokenManager linkService;

    public RequestDto findByRequestId(long requestId) {
        var request = requestRepository.findById(requestId).orElse(null);
        return requestMapper.entityToDto(request);
    }

    public List<RequestDto> getByUserId(long userId) {
        var requestList = requestRepository.getByUser(userRepository.findByUserId(userId).orElse(null));
        return requestList.stream().map(requestMapper::entityToDto).toList();
    }

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

    //обновляем реквест при
    //надо пересмотреть
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

    //
    //создание токена и создание запроса к кандидату
    @Transactional
    @Override
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

    //todo
    //а что он должен делать по бизнес требованиям?
    //пересмотреть отправку с методом выше
    public void createRequest(RequestDto requestDto) {
        var candidate = candidateRepository.findByCandidateId(requestDto.getCandidateId()).orElseThrow(); //fix after updating mapper
        var template = templateRepository.findByTemplateId(requestDto.getTemplateId()).orElseThrow();

        try {
            // emailService.sendEmail(template.getTemplateSubject(), template.getTemplateBody(), candidate.getCandidateMail()); Раскомментировать по надобности
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        var request = requestMapper.dtoToEntity(requestDto);

        requestRepository.save(request);
    }

}
