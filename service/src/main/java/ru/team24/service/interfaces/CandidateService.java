package ru.team24.service.interfaces;

import ru.team24.service.dto.CandidateDto;

import java.util.Date;
import java.util.List;

public interface CandidateService {
    CandidateDto findCandidateById(long userId);
    List<CandidateDto> findAllCandidates();
    void addCandidate(CandidateDto candidateDto);
}
