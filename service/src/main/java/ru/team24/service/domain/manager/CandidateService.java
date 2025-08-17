package ru.team24.service.domain.manager;

import ru.team24.service.dto.CandidateDto;

import java.util.List;

public interface CandidateService {
    CandidateDto findCandidateById(long userId);
    List<CandidateDto> findAllCandidates();

    void addCandidateByMail(List<String> mails);

    void addCandidateByMail(List<String> mails, long managerId);

    //обновить кандидата по токену из реквестов
    void updateCandidateData(String token, CandidateDto updatedCandidateDto);
}
