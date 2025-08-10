package ru.team24.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.team24.database.entities.Role;
import ru.team24.database.entities.User;
import ru.team24.database.repositories.UserRepository;
import ru.team24.service.interfaces.UserService;
import ru.team24.service.mapper.UserMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public User findByUserId(long userId) {
        return null;
    }

    public List<User> findAllUsers() {
        return List.of();
    }

    public void addUser(Role roleId, String userMail, String userPassword, String userFirstName, String userLastName) {

    }
}
