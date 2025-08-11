package ru.team24.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.team24.database.entities.Candidate;
import ru.team24.database.repositories.CandidateRepository;
import ru.team24.service.interfaces.CandidateService;
import ru.team24.service.mapper.CandidateMapper;

import java.util.Date;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {
    private final CandidateMapper candidateMapper;
    private final CandidateRepository candidateRepository;

    public Candidate findCandidateById(long userId) {
        return  candidateRepository.findByCandidateId(userId).orElseThrow();
    }

    public List<Candidate> findAllCandidates() {
        return List.of();
    }

    public void addCandidate(String candidateFirstName, String candidateLastName, String candidateFatherName, String candidateMailDate, Date candidateBirthDate, String candidatePhone) {

    }
}
