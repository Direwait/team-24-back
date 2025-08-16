package ru.team24.service.mapper;

import ru.team24.database.entities.User;
import ru.team24.service.dto.UserDto;

public interface UserMapper {

    //@Mapping(source = "roleId", target = "role.roleId")
    UserDto userToUserDto(User user);

    //@Mapping(source = "role.roleId", target = "roleId")
    User userDtoToUser(UserDto userDto);
}
