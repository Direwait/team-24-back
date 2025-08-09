package ru.team24.service.impl;


import org.springframework.stereotype.Service;
import ru.team24.database.entities.Candidate;
import ru.team24.service.interfaces.CandidateService;

import java.util.Date;
import java.util.List;
@Service
public class CandidateServiceImpl implements CandidateService {
    public Candidate findCandidateById(long userId) {
        return null;
    }

    public List<Candidate> findAllCandidates() {
        return List.of();
    }

    public void addCandidate(String candidateFirstName, String candidateLastName, String candidateFatherName, String candidateMailDate, Date candidateBirthDate, String candidatePhone) {

    }
}
