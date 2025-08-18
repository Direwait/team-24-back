package ru.team24.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.team24.database.domain.general.entity.User;
import ru.team24.service.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "role.roleId", target = "roleId")
    UserDto userToUserDto(User user);

    @Mapping(source = "roleId", target = "role.roleId")
    User userDtoToUser(UserDto userDto);
}
