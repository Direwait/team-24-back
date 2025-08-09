package ru.team24.service.impl;

import org.springframework.stereotype.Service;
import ru.team24.database.entities.Role;
import ru.team24.database.entities.User;
import ru.team24.service.interfaces.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    public User findByUserId(long userId) {
        return null;
    }

    public List<User> findAllUsers() {
        return List.of();
    }

    public void addUser(Role roleId, String userMail, String userPassword, String userFirstName, String userLastName) {

    }
}
