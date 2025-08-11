package ru.team24.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.team24.database.entities.Role;
import ru.team24.database.entities.User;
import ru.team24.database.repositories.RoleRepository;
import ru.team24.database.repositories.UserRepository;
import ru.team24.service.interfaces.UserService;
import ru.team24.service.mapper.UserMapper;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public User findByUserId(long userId) {
        return userRepository.findByUserId(userId).orElseThrow();
    }

    public List<User> findAllUsers() {
        return List.of();
    }

    public void addUser(long roleId, String userMail, String userPassword, String userFirstName, String userLastName) {
        User user = new User();
        Optional<Role> role = roleRepository.findById(roleId);
        role.ifPresent(user::setRole);
        user.setUserMail(userMail);
        user.setUserPassword(userPassword);
        user.setUserFirstName(userFirstName);
        user.setUserLastName(userLastName);
        userRepository.save(user);
    }
}
