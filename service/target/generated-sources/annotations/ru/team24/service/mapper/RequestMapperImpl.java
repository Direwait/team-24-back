package ru.team24.service.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.team24.database.dto.RequestDto;
import ru.team24.database.dto.UserDto;
import ru.team24.database.entities.Candidate;
import ru.team24.database.entities.Request;
import ru.team24.database.entities.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-10T00:08:39+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class RequestMapperImpl implements RequestMapper {

    @Override
    public Request dtoToEntity(RequestDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        Request request = new Request();

        request.setUser( userDtoToUser( requestDto.getUser() ) );
        request.setCandidate( userDtoToCandidate( requestDto.getUser() ) );
        request.setRequestId( requestDto.getRequestId() );
        request.setRequestToken( requestDto.getRequestToken() );
        request.setRequestState( requestDto.getRequestState() );
        request.setRequestDate( requestDto.getRequestDate() );

        return request;
    }

    @Override
    public RequestDto entityToDto(Request request) {
        if ( request == null ) {
            return null;
        }

        RequestDto requestDto = new RequestDto();

        requestDto.setUser( userToUserDto( request.getUser() ) );
        requestDto.setRequestId( request.getRequestId() );
        requestDto.setRequestToken( request.getRequestToken() );
        requestDto.setRequestState( request.getRequestState() );
        requestDto.setRequestDate( request.getRequestDate() );

        return requestDto;
    }

    protected User userDtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setUserId( userDto.getUserId() );
        user.setUserMail( userDto.getUserMail() );
        user.setUserPassword( userDto.getUserPassword() );
        user.setUserFirstName( userDto.getUserFirstName() );
        user.setUserLastName( userDto.getUserLastName() );
        user.setUserCreatedAt( userDto.getUserCreatedAt() );

        return user;
    }

    protected Candidate userDtoToCandidate(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        Candidate candidate = new Candidate();

        candidate.setCandidateId( userDto.getUserId() );

        return candidate;
    }

    protected UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setUserId( user.getUserId() );
        userDto.setUserMail( user.getUserMail() );
        userDto.setUserPassword( user.getUserPassword() );
        userDto.setUserFirstName( user.getUserFirstName() );
        userDto.setUserLastName( user.getUserLastName() );
        userDto.setUserCreatedAt( user.getUserCreatedAt() );

        return userDto;
    }
}
