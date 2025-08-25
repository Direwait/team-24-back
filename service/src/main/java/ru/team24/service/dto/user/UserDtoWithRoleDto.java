package ru.team24.service.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.team24.service.dto.RoleDto;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoWithRoleDto {
    private long userId;

    private RoleDto role;

    private String userMail;

    private String userPassword;

    private String userFirstName;

    private String userLastName;

    private String userFatherName;

    private Date userCreatedAt;

    private boolean userIsActive;
}
