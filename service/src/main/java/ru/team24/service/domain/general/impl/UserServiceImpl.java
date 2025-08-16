package ru.team24.service.domain.general.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.team24.database.domain.general.entity.User;
import ru.team24.service.dto.UserDto;
import ru.team24.database.domain.general.repository.RoleRepository;
import ru.team24.database.domain.general.repository.UserRepository;
import ru.team24.service.domain.general.UserService;
import ru.team24.service.mapper.UserMapper;

import java.util.List;

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
        return userRepository.findAll()
                .stream()
                .filter(User::isUserIsActive)
                .map(userMapper::userToUserDto).toList();
    }

    public boolean existsByUserMail(String mail) {
        return userRepository.existsUserByUserMail(mail);
    }

    public void addUser(UserDto userDto) {
        var user = userMapper.userDtoToUser(userDto);
        userRepository.save(user);
    }
}
