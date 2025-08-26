package ru.team24.service.dto.user;

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

    private long roleId;

    private String userMail;

    private String userPassword;

    private String userFirstName;

    private String userLastName;

    private String userFatherName;

    private Date userCreatedAt;

    private boolean userIsActive;

}
