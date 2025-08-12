package ru.team24.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.team24.service.dto.CandidateDto;
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

    public void addCandidate(CandidateDto candidateDto) {
        var candidate = candidateMapper.dtoToEntity(candidateDto);
        candidate.setCandidateId(null);// для авто-генерации ID
        candidateRepository.save(candidate);
    }
}
