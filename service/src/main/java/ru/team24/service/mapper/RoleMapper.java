package ru.team24.service.mapper;

import org.mapstruct.Mapper;
import ru.team24.database.entities.Role;
import ru.team24.service.dto.RoleDto;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role dtoToEntity(RoleDto roleDto);

    RoleDto entityToDto(Role role);

}
