package ru.team24.service.domain.manager;

import ru.team24.service.dto.candidate.CandidateDto;

import java.util.List;

public interface CandidateService {
    CandidateDto findCandidateById(long userId);
    List<CandidateDto> findAllCandidates();

    void addCandidateByMail(List<String> mails, long managerId);

}
