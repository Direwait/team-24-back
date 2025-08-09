package ru.team24.database.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private long userId;

    private RoleDto role;

    private String userMail;

    private String userPassword;

    private String userFirstName;

    private String userLastName;

    private Date userCreatedAt;

}
