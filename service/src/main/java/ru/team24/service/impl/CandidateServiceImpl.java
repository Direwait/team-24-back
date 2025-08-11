package ru.team24.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.team24.database.dto.CandidateDto;
import ru.team24.database.entities.Candidate;
import ru.team24.database.repositories.CandidateRepository;
import ru.team24.service.interfaces.CandidateService;
import ru.team24.service.mapper.CandidateMapper;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {
    private final CandidateMapper candidateMapper;
    private final CandidateRepository candidateRepository;


    public Candidate findCandidateById(long userId) {
        return  candidateRepository.findByCandidateId(userId).orElseThrow();
    }

    public List<CandidateDto> findAllCandidates() {
        return candidateRepository
                .findAll()
                .stream()
                .map(candidateMapper::entityToDto)
                .toList();
    }

    public void addCandidate(String candidateFirstName,
                             String candidateLastName,
                             String candidateFatherName,
                             String candidateMailDate,
                             Date candidateBirthDate,
                             String candidatePhone) {
        var candidate = new Candidate();
        candidate.setCandidateFirstName(candidateFirstName);
        candidate.setCandidateLastName(candidateLastName);
        candidate.setCandidateFatherName(candidateFatherName);
        candidate.setCandidateBirthDate(candidateBirthDate);
        candidate.setCandidatePhone(candidatePhone);
        candidateRepository.save(candidate);

    }
}
