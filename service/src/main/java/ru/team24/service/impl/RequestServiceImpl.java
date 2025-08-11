package ru.team24.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.team24.database.dto.RequestDto;
import ru.team24.database.entities.Request;
import ru.team24.database.enums.RequestState;
import ru.team24.database.repositories.RequestRepository;
import ru.team24.database.repositories.UserRepository;
import ru.team24.service.interfaces.RequestService;
import ru.team24.service.mapper.RequestMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final RequestMapper requestMapper;
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;

    public RequestDto findByRequestId(long requestId) {
        var request = requestRepository.findById(requestId).orElse(null);
        return requestMapper.entityToDto(request);
    }

    public List<RequestDto> getByUserId(long userId) {
        var requestList = requestRepository.getByUser(userRepository.findByUserId(userId).orElse(null));
        return requestList.stream().map(requestMapper::entityToDto).toList();
    }

    public void UpdateRequestByRequestId(long requestId, RequestDto request) {
        request.setRequestId(requestId);
        requestRepository.save(requestMapper.dtoToEntity(request));
    }

    public void createRequest(long userId, long candidateId, long templateId, String requestToken, RequestState requestState) {
        var request = new Request();
        request.setUser(userRepository.findById(userId).orElse(null));
        request.setCandidateId(candidateId);
        request.setRequestToken(requestToken);
        request.setRequestState(requestState);
        requestRepository.save(request);
    }
}
