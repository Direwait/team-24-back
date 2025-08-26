package ru.team24.service.domain.general.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.team24.database.domain.general.entity.User;
import ru.team24.service.dto.user.UserDto;
import ru.team24.database.domain.general.repository.RoleRepository;
import ru.team24.database.domain.general.repository.UserRepository;
import ru.team24.service.domain.general.UserService;
import ru.team24.service.dto.user.UserDtoWithRoleDto;
import ru.team24.service.mapper.UserMapper;
import ru.team24.service.payload.request.AddNewUserRequest;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;


    public UserDto findByUserId(long userId) {
        var user = userRepository.findById(userId).orElse(null);
        return userMapper.entityToDto(user);
    }

    public Page<UserDtoWithRoleDto> findUsers(String role, Pageable pageable) {
        Page<User> page;
        if (role == null || role.isEmpty() || role.equalsIgnoreCase("all")) {
            page = userRepository.findAllByUserIsActiveOrderByUserCreatedAtDesc(true, pageable);
        } else {
            role = role.toUpperCase();
            page = userRepository.findAllByUserIsActiveAndRole_RoleNameOrderByUserCreatedAtDesc(true, role, pageable);
        }
        return page.map(userMapper::entityToDtoWithRole);
    }

    public boolean existsByUserMail(String mail) {
        return userRepository.existsUserByUserMail(mail);
    }

    public void addUser(AddNewUserRequest userRequest) {
        var user = userMapper.addNewUserRequestToDto(userRequest);
        if (userRequest.getUserRole().equals("ADMIN")){
            user.setRoleId(1);
        } else if (userRequest.getUserRole().equals("MANAGER")){
            user.setRoleId(2);
        } else {
            throw new RuntimeException("role " + userRequest.getUserRole() + " does not exist");
        }
        user.setUserIsActive(true);
        var userEntity = userMapper.dtoToEntity(user);
        userEntity.setUserId(null);
        userEntity.setUserCreatedAt(new Date());
        userRepository.save(userEntity);
    }

    public void softDeleteUserById(long userId) {
        userRepository.findByUserId(userId).ifPresent(user -> {
            user.setUserIsActive(false);
            userRepository.save(user);
        });
    }


}
