package ru.team24.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.team24.service.dto.UserDto;
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


    public UserDto findByUserId(long userId) {
        var user = userRepository.findById(userId).orElse(null);
        return userMapper.userToUserDto(user);
    }

    public List<UserDto> findAllUsers() {
        return userRepository.findAll().stream().map(userMapper::userToUserDto).toList();
    }

    public boolean existsByUserMail(String mail) {
        return userRepository.existsUserByUserMail(mail);
    }

    public void addUser(UserDto userDto) {

        var user = userMapper.userDtoToUser(userDto);
        user.setUserId(null); // для авто-генерации ID
        userRepository.save(user);
    }
}
