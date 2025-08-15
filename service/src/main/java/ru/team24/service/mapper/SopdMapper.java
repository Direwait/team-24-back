package ru.team24.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.team24.database.entities.Sopd;
import ru.team24.service.dto.SopdDto;

@Mapper(componentModel = "spring")
public interface SopdMapper {

    @Mapping(target = "sopdCreatedAt", ignore = true)
    @Mapping(source = "userId", target = "user.userId")
    Sopd dtoToEntity(SopdDto dto);

    @Mapping(source = "sopdCreatedAt", target = "sopdCreatedAt")
    @Mapping(source = "user.userId", target = "userId")
    SopdDto entityToDto(Sopd entity);

    @Mapping(target = "sopdId", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateFromDto(SopdDto sopdDto, @MappingTarget Sopd sopd);
}
