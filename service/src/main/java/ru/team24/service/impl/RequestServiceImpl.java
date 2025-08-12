package ru.team24.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.team24.database.repositories.CandidateRepository;
import ru.team24.database.repositories.TemplateRepository;
import ru.team24.service.dto.RequestDto;
import ru.team24.database.entities.Request;
import ru.team24.database.enums.RequestState;
import ru.team24.database.repositories.RequestRepository;
import ru.team24.database.repositories.UserRepository;
import ru.team24.service.interfaces.EmailService;
import ru.team24.service.interfaces.RequestService;
import ru.team24.service.interfaces.UserService;
import ru.team24.service.mapper.RequestMapper;

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

    public void createRequest(RequestDto requestDto) {
        var candidate = candidateRepository.findByCandidateId(requestDto.getCandidateId()).orElseThrow(); //fix after updating mapper
        var template = templateRepository.findByTemplateId(requestDto.getTemplateId()).orElseThrow();
        try{
           // emailService.sendEmail(template.getTemplateSubject(), template.getTemplateBody(), candidate.getCandidateMail()); Раскомментировать по надобности
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        var request = requestMapper.dtoToEntity(requestDto);
        request.setRequestId(null); // для авто-генерации ID
        requestRepository.save(request);
    }
}
