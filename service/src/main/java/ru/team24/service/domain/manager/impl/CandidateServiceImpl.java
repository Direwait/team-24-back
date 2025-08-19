package ru.team24.service.domain.manager.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.team24.database.domain.manager.entity.Candidate;
import ru.team24.database.domain.manager.entity.Request;
import ru.team24.database.enums.RequestState;
import ru.team24.database.domain.manager.repository.RequestRepository;
import ru.team24.service.observ.action.ActionCreateRequest;
import ru.team24.service.observ.ActionType;
import ru.team24.service.dto.candidate.CandidateDto;
import ru.team24.database.domain.manager.repository.CandidateRepository;
import ru.team24.service.domain.manager.CandidateService;
import ru.team24.service.mapper.CandidateMapper;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {
    private final ApplicationEventPublisher publisher;
    private final CandidateMapper candidateMapper;
    private final CandidateRepository candidateRepository;
    private final RequestRepository requestRepository;

    public CandidateDto findCandidateById(long userId) {
        var candidate = candidateRepository.findById(userId).orElse(null);
        return  candidateMapper.entityToDto(candidate);
    }

    public List<CandidateDto> findAllCandidates() {
        return candidateRepository
                .findAll()
                .stream()
                .map(candidateMapper::entityToDto)
                .toList();
    }

    @Deprecated //тут нет менеджера из клиента, используйте нижний метод в своей цепи
    @Transactional
    @Override
    public void addCandidateByMail(List<String> mails) {
        if (mails == null || mails.isEmpty()) {
            return;
        }
        List<Candidate> candidates = mails.stream()
                .map(candidateMail -> {
                    Candidate candidate = new Candidate(); // Создаём вручную
                    candidateMapper.mailToEntity(candidateMail, candidate); // Заполняем
                    return candidate;
                })
                .toList();
        candidateRepository.saveAll(candidates);
    }

    @Transactional
    @Override
    public void addCandidateByMail(List<String> mails, long managerId) {
        if (mails == null || mails.isEmpty()) {
            return;
        }

        List<Candidate> candidates = mails.stream()
                .map(candidateMail -> {
                    Candidate candidate = new Candidate();
                    candidateMapper.mailToEntity(candidateMail, candidate);
                    return candidate;
                })
                .toList();
        candidateRepository.saveAll(candidates);

        candidates.forEach(candidate -> {
            ActionCreateRequest actionInfo = ActionCreateRequest.builder()
                    .userId(managerId)
                    .candidateId(candidate.getCandidateId())
                    .candidateMail(candidate.getCandidateMail())
                    .actionType(ActionType.CREATE)
                    .build();
            publisher.publishEvent(actionInfo);
        });
    }

    //пересмотреть
    //при обновлении кандидатом, отправить уведомление через почту/тг менеджеру
    // возможно можно удалить
    @Transactional
    public void updateCandidateData(String token, CandidateDto updatedCandidateDto) {
        Request request = requestRepository.findByRequestToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        Candidate candidate = request.getCandidate();
        candidateMapper.updateFromDto(updatedCandidateDto, candidate);

        candidateRepository.save(candidate);

        request.setRequestState(RequestState.APPROVED);
        requestRepository.save(request);
    }

}
