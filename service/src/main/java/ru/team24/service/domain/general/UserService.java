package ru.team24.service.domain.general;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.team24.service.dto.user.UserDto;
import ru.team24.service.dto.user.UserDtoWithRoleDto;
import ru.team24.service.payload.request.AddNewUserRequest;

public interface UserService {
    UserDto findByUserId(long userId);
    Page<UserDtoWithRoleDto> findUsers(String role, Pageable pageable);

    boolean existsByUserMail(String mail);
    void addUser(AddNewUserRequest userRequest);

    void softDeleteUserById(long userId);

}
