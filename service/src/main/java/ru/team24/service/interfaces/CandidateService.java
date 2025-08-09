package ru.team24.service.interfaces;

import ru.team24.database.entities.Candidate;

import java.util.Date;
import java.util.List;

public interface CandidateService {
    Candidate findCandidateById(long userId);
    List<Candidate> findAllCandidates();
    void addCandidate(String candidateFirstName,
                      String candidateLastName,
                      String candidateFatherName,
                      String candidateMailDate,
                      Date candidateBirthDate,
                      String candidatePhone);
}
