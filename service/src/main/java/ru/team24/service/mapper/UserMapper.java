package ru.team24.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.team24.database.domain.general.entity.User;
import ru.team24.service.dto.user.UserDto;
import ru.team24.service.dto.user.UserDtoWithRoleDto;
import ru.team24.service.payload.request.AddNewUserRequest;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "role.roleId", target = "roleId")
    UserDto entityToDto(User user);

    @Mapping(source = "roleId", target = "role.roleId")
    User dtoToEntity(UserDto userDto);
    UserDtoWithRoleDto entityToDtoWithRole(User user);
    @Mapping(target = "roleId", ignore = true)
    UserDto addNewUserRequestToDto(AddNewUserRequest addNewUserRequest);
}
