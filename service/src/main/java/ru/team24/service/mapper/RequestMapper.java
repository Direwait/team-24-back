package ru.team24.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.team24.database.domain.manager.entity.Request;
import ru.team24.service.dto.request.RequestDto;
import ru.team24.service.dto.request.RequestWithCandidateAndManagerDto;
import ru.team24.service.dto.request.RequestWithCandidateDto;

@Mapper(componentModel = "spring", uses = CandidateMapper.class)
public interface RequestMapper {

   @Mapping(source = "userId", target = "user.userId")
   @Mapping(source = "candidateId", target = "candidate.candidateId")
   @Mapping(source = "templateId", target = "template.templateId")
   @Mapping(source = "sopdId", target = "sopd.sopdId")
   Request dtoToEntity(RequestDto requestDto);


   @Mapping(source = "user.userId", target = "userId")
   @Mapping(source = "candidate.candidateId", target = "candidateId")
   @Mapping(source = "template.templateId", target = "templateId")
   @Mapping(source = "sopd.sopdId", target = "sopdId")
   RequestDto entityToDto(Request request);


   @Mapping(source = "user.userId", target = "userId")
   @Mapping(source = "template.templateId", target = "templateId")
   @Mapping(source = "sopd.sopdId", target = "sopdId")
   RequestWithCandidateDto entityToDtoWithCandidate(Request request);

   @Mapping(source = "template.templateId", target = "templateId")
   @Mapping(source = "sopd.sopdId", target = "sopdId")
   RequestWithCandidateAndManagerDto entityToDtoWithCandidateAndUser(Request request);
}
