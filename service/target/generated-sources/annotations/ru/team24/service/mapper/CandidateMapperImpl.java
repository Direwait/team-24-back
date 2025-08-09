package ru.team24.service.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.team24.database.dto.CandidateDto;
import ru.team24.database.entities.Candidate;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-10T00:08:39+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class CandidateMapperImpl implements CandidateMapper {

    @Override
    public Candidate dtoToEntity(CandidateDto dto) {
        if ( dto == null ) {
            return null;
        }

        Candidate candidate = new Candidate();

        candidate.setCandidateId( dto.getCandidateId() );
        candidate.setCandidateFirstName( dto.getCandidateFirstName() );
        candidate.setCandidateLastName( dto.getCandidateLastName() );
        candidate.setCandidateFatherName( dto.getCandidateFatherName() );
        candidate.setCandidateMail( dto.getCandidateMail() );
        candidate.setCandidateBirthDate( dto.getCandidateBirthDate() );
        candidate.setCandidatePhone( dto.getCandidatePhone() );

        return candidate;
    }

    @Override
    public CandidateDto entityToDto(Candidate entity) {
        if ( entity == null ) {
            return null;
        }

        CandidateDto candidateDto = new CandidateDto();

        candidateDto.setCandidateCreatedAt( entity.getCandidateCreatedAt() );
        candidateDto.setCandidateId( entity.getCandidateId() );
        candidateDto.setCandidateFirstName( entity.getCandidateFirstName() );
        candidateDto.setCandidateLastName( entity.getCandidateLastName() );
        candidateDto.setCandidateFatherName( entity.getCandidateFatherName() );
        candidateDto.setCandidateMail( entity.getCandidateMail() );
        candidateDto.setCandidateBirthDate( entity.getCandidateBirthDate() );
        candidateDto.setCandidatePhone( entity.getCandidatePhone() );

        return candidateDto;
    }
}
