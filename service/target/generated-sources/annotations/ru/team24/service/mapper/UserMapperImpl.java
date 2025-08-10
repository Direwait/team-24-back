package ru.team24.service.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.team24.database.dto.RoleDto;
import ru.team24.database.dto.UserDto;
import ru.team24.database.entities.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-10T00:08:39+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setRole( userToRoleDto( user ) );
        userDto.setUserId( user.getUserId() );
        userDto.setUserMail( user.getUserMail() );
        userDto.setUserPassword( user.getUserPassword() );
        userDto.setUserFirstName( user.getUserFirstName() );
        userDto.setUserLastName( user.getUserLastName() );
        userDto.setUserCreatedAt( user.getUserCreatedAt() );

        return userDto;
    }

    @Override
    public User userDtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setRoleId( userDtoRoleRoleId( userDto ) );
        user.setUserId( userDto.getUserId() );
        user.setUserMail( userDto.getUserMail() );
        user.setUserPassword( userDto.getUserPassword() );
        user.setUserFirstName( userDto.getUserFirstName() );
        user.setUserLastName( userDto.getUserLastName() );
        user.setUserCreatedAt( userDto.getUserCreatedAt() );

        return user;
    }

    protected RoleDto userToRoleDto(User user) {
        if ( user == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setRoleId( user.getRoleId() );

        return roleDto;
    }

    private long userDtoRoleRoleId(UserDto userDto) {
        if ( userDto == null ) {
            return 0L;
        }
        RoleDto role = userDto.getRole();
        if ( role == null ) {
            return 0L;
        }
        long roleId = role.getRoleId();
        return roleId;
    }
}
