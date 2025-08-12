package ru.team24.service.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.team24.database.entities.User;
import ru.team24.database.repositories.RoleRepository;
import ru.team24.service.dto.UserDto;
import ru.team24.service.mapper.UserMapper;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {
    @Autowired
    RoleRepository roleRepository;

    public UserDto userToUserDto(User user) {
        if (user == null) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setUserId(user.getUserId());
        userDto.setUserMail(user.getUserMail());
        userDto.setUserPassword(user.getUserPassword());
        userDto.setUserFirstName(user.getUserFirstName());
        userDto.setUserLastName(user.getUserLastName());
        userDto.setUserCreatedAt(user.getUserCreatedAt());
        userDto.setRoleId(user.getRole().getRoleId());

        return userDto;
    }

    public User userDtoToUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        User user = new User();

        user.setUserId(userDto.getUserId());
        user.setRole(roleRepository.findById(userDto.getRoleId()).orElseThrow());
        user.setUserMail(userDto.getUserMail());
        user.setUserPassword(userDto.getUserPassword());
        user.setUserFirstName(userDto.getUserFirstName());
        user.setUserLastName(userDto.getUserLastName());
        return user;
    }
}
