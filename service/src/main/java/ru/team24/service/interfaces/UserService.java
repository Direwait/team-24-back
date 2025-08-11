package ru.team24.service.interfaces;

import ru.team24.database.dto.UserDto;
import ru.team24.database.entities.Role;
import ru.team24.database.entities.User;

import java.util.List;

public interface UserService {
    UserDto findByUserId(long userId);
    List<UserDto> findAllUsers();
    void addUser(long roleId, String userMail, String userPassword, String userFirstName, String userLastName);
}
