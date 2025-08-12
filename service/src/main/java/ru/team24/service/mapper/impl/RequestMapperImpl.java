package ru.team24.service.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.team24.database.entities.Request;
import ru.team24.database.repositories.CandidateRepository;
import ru.team24.database.repositories.TemplateRepository;
import ru.team24.database.repositories.UserRepository;
import ru.team24.service.dto.RequestDto;
import ru.team24.service.mapper.RequestMapper;

@Component
@RequiredArgsConstructor
public class RequestMapperImpl implements RequestMapper {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CandidateRepository candidateRepository;
    @Autowired
    TemplateRepository templateRepository;

    public Request dtoToEntity(RequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }

        Request request = new Request();

        request.setRequestId(requestDto.getRequestId());
        request.setUser(userRepository.findByUserId(requestDto.getUserId()).orElseThrow());
        request.setTemplate(templateRepository.findById(requestDto.getTemplateId()).orElseThrow());
        request.setCandidate(candidateRepository.findById(requestDto.getCandidateId()).orElseThrow());
        request.setRequestId(requestDto.getRequestId());
        request.setRequestState(requestDto.getRequestState());
        request.setRequestToken(requestDto.getRequestToken());

        return request;
    }

    public RequestDto entityToDto(Request request) {
        if (request == null) {
            return null;
        }

        RequestDto requestDto = new RequestDto();

        requestDto.setRequestId(request.getRequestId());
        requestDto.setRequestToken(request.getRequestToken());
        requestDto.setTemplateId(request.getTemplateId());
        requestDto.setUserId(request.getUserId());
        requestDto.setRequestDate(request.getRequestDate());
        requestDto.setCandidateId(request.getCandidateId());
        requestDto.setRequestState(request.getRequestState());

        return requestDto;
    }
}
