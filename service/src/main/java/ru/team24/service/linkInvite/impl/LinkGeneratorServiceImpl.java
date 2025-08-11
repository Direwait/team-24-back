package ru.team24.service.linkInvite.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.team24.database.entities.Candidate;
import ru.team24.database.entities.Request;
import ru.team24.database.enums.RequestState;
import ru.team24.database.repositories.CandidateRepository;
import ru.team24.database.repositories.RequestRepository;
import ru.team24.service.interfaces.CandidateService;
import ru.team24.service.interfaces.TemplateService;
import ru.team24.service.interfaces.UserService;
import ru.team24.service.linkInvite.LinkGeneratorService;
import ru.team24.service.mapper.CandidateMapper;
import ru.team24.service.mapper.TemplateMapper;
import ru.team24.service.mapper.UserMapper;

import java.util.UUID;
@Slf4j
@Component
@RequiredArgsConstructor
public class LinkGeneratorServiceImpl implements LinkGeneratorService {

    private final RequestRepository requestRepository;
    private final CandidateService candidateService;
    private final CandidateRepository candidateRepository;
    private final TemplateService templateService;
    private final UserService userService;
    private final CandidateMapper candidateMapper;
    private final TemplateMapper templateMapper;
    private final UserMapper userMapper;


    @Override
    public String generateAccessToken() {
        // Генерируем и возвращаем токен без сохранения запроса
        return UUID.randomUUID().toString();
    }


    @Transactional
    public String createRequest(Long candidateId, Long templateId, Long userId) {
        Candidate candidate = candidateMapper.dtoToEntity(candidateService.findCandidateById(candidateId));
        var template = templateMapper.dtoToEntity(templateService.findTemplateById(templateId));
        var manager = userMapper.userDtoToUser(userService.findByUserId(userId));
        log.info("{}{}{}",candidateId,templateId,userId);

        String token = generateAccessToken();

        Request request = Request.builder()
                .candidate(candidate)
                .template(template)
                .user(manager)
                .requestToken(token)
                .requestState(RequestState.PENDING)
                .build();

        requestRepository.save(request);
        return token;
    }

    public boolean isTokenValid(String token) {
        return requestRepository.findByRequestToken(token)
                .filter(r -> !"EXPIRED".equals(r.getRequestToken()))
                .isPresent();
    }

    @Transactional
    public void updateCandidateData(String token, Candidate updatedCandidate) {
        Request request = requestRepository.findByRequestToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        Candidate candidate = request.getCandidate();

        //для таких моментов, можно будет прописать 1 метод в маппере, но пока так
        candidate.setCandidateFirstName(updatedCandidate.getCandidateFirstName());
        candidate.setCandidateLastName(updatedCandidate.getCandidateLastName());
        candidate.setCandidateFatherName(updatedCandidate.getCandidateFatherName());
        candidate.setCandidateMail(updatedCandidate.getCandidateMail());
        candidate.setCandidatePhone(updatedCandidate.getCandidatePhone());
        candidate.setCandidateBirthDate(updatedCandidate.getCandidateBirthDate());

        candidateRepository.save(candidate);

        //rejected
        request.setRequestState(RequestState.APPROVED);
        requestRepository.save(request);
    }
}



