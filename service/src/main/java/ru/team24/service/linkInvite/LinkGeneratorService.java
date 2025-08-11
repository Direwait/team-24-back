package ru.team24.service.linkInvite;

import ru.team24.database.entities.Candidate;

public interface LinkGeneratorService {
    String generateAccessToken();
    void updateCandidateData(String token, Candidate updatedCandidate);
    boolean isTokenValid (String token);
    String createRequest(Long candidateId, Long templateId, Long userId);
}
