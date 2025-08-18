package ru.team24.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.team24.database.domain.manager.entity.Candidate;
import ru.team24.service.dto.candidate.CandidateDto;
import ru.team24.service.payload.request.CandidateResponse;

@Mapper(componentModel = "spring")
public interface CandidateMapper {

    @Mapping(target = "candidateCreatedAt", ignore = true)
    Candidate dtoToEntity(CandidateDto dto);

    @Mapping(source = "candidateCreatedAt", target = "candidateCreatedAt")
    CandidateDto entityToDto(Candidate entity);

    @Mapping(target = "candidateId", ignore = true)
    void updateFromDto(CandidateDto candidateDto, @MappingTarget Candidate candidate);

    @Mapping(target = "candidateId", ignore = true)
    @Mapping(target = "candidateFirstName", ignore = true)
    @Mapping(target = "candidateLastName", ignore = true)
    @Mapping(target = "candidateFatherName", ignore = true)
    @Mapping(target = "candidateBirthDate", ignore = true)
    @Mapping(target = "candidatePhone", ignore = true)
    @Mapping(target = "candidateCreatedAt", ignore = true)
    Candidate mailToEntity(String candidateMail, @MappingTarget Candidate candidate);


    @Mapping(target = "candidateId", ignore = true)
    @Mapping(target = "candidateCreatedAt", ignore = true)
    void updateCandidateFromResponse(CandidateResponse dto, @MappingTarget Candidate candidate);
}
