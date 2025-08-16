package ru.team24.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.team24.database.entities.Candidate;
import ru.team24.service.dto.CandidateDto;

@Mapper(componentModel = "spring")
public interface CandidateMapper {

    @Mapping(target = "candidateCreatedAt", ignore = true)
    Candidate dtoToEntity(CandidateDto dto);

    @Mapping(source = "candidateCreatedAt", target = "candidateCreatedAt")
    CandidateDto entityToDto(Candidate entity);
}
