package ru.team24.service.interfaces;

import ru.team24.database.entities.Role;
import ru.team24.database.entities.User;

import java.util.List;

public interface UserService {
    User findByUserId(long userId);
    List<User> findAllUsers();
    void addUser(Role roleId, String userMail, String userPassword, String userFirstName, String userLastName);
}
