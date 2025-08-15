package ru.team24.service.mapper;

import ru.team24.database.entities.Request;
import ru.team24.service.dto.RequestDto;

//@Mapper(componentModel = "spring")
public interface RequestMapper {

   // @Mapping(target = "user.userId", source = "user.userId") // Игнорируем автоматический маппинг
   // @Mapping(target = "candidate.candidateId", source = "user.userId")
   // @Mapping(target = "template", ignore = true)
    Request dtoToEntity(RequestDto requestDto);

   // @Mapping(target = "user.userId", source = "user.userId") // Игнорируем автоматический маппинг
   // @Mapping(target = "candidate", ignore = true)
   // @Mapping(target = "template", ignore = true)
    RequestDto entityToDto(Request request);
}
