package ru.team24.service.interfaces;

import ru.team24.service.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto findByUserId(long userId);
    List<UserDto> findAllUsers();

    boolean existsByUserMail(String mail);
    void addUser(UserDto userDto);
}
