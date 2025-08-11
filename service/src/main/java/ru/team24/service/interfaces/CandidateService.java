package ru.team24.service.interfaces;

import ru.team24.database.dto.CandidateDto;
import ru.team24.database.entities.Candidate;

import java.util.Date;
import java.util.List;

public interface CandidateService {
    CandidateDto findCandidateById(long userId);
    List<CandidateDto> findAllCandidates();
    void addCandidate(String candidateFirstName,
                      String candidateLastName,
                      String candidateFatherName,
                      String candidateMailDate,
                      Date candidateBirthDate,
                      String candidatePhone);
}
