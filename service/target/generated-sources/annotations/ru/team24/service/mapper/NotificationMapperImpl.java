package ru.team24.service.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.team24.database.dto.CandidateDto;
import ru.team24.database.dto.NotificationDto;
import ru.team24.database.dto.RequestDto;
import ru.team24.database.dto.TemplateDto;
import ru.team24.database.dto.UserDto;
import ru.team24.database.entities.Candidate;
import ru.team24.database.entities.Notification;
import ru.team24.database.entities.Request;
import ru.team24.database.entities.Template;
import ru.team24.database.entities.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-10T00:08:39+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class NotificationMapperImpl implements NotificationMapper {

    @Override
    public Notification dtoToEntity(NotificationDto notificationDto) {
        if ( notificationDto == null ) {
            return null;
        }

        Notification notification = new Notification();

        notification.setRequest( requestDtoToRequest( notificationDto.getRequest() ) );
        notification.setNotificationId( notificationDto.getNotificationId() );
        notification.setNotificationText( notificationDto.getNotificationText() );
        notification.setNotificationState( notificationDto.getNotificationState() );
        notification.setNotificationCreatedAt( notificationDto.getNotificationCreatedAt() );
        notification.setNotificationReadAt( notificationDto.getNotificationReadAt() );

        return notification;
    }

    @Override
    public NotificationDto entityToDto(Notification notification) {
        if ( notification == null ) {
            return null;
        }

        NotificationDto notificationDto = new NotificationDto();

        notificationDto.setRequest( requestToRequestDto( notification.getRequest() ) );
        notificationDto.setNotificationId( notification.getNotificationId() );
        notificationDto.setNotificationText( notification.getNotificationText() );
        notificationDto.setNotificationState( notification.getNotificationState() );
        notificationDto.setNotificationCreatedAt( notification.getNotificationCreatedAt() );
        notificationDto.setNotificationReadAt( notification.getNotificationReadAt() );

        return notificationDto;
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

    protected Candidate candidateDtoToCandidate(CandidateDto candidateDto) {
        if ( candidateDto == null ) {
            return null;
        }

        Candidate candidate = new Candidate();

        candidate.setCandidateId( candidateDto.getCandidateId() );
        candidate.setCandidateFirstName( candidateDto.getCandidateFirstName() );
        candidate.setCandidateLastName( candidateDto.getCandidateLastName() );
        candidate.setCandidateFatherName( candidateDto.getCandidateFatherName() );
        candidate.setCandidateMail( candidateDto.getCandidateMail() );
        candidate.setCandidateBirthDate( candidateDto.getCandidateBirthDate() );
        candidate.setCandidatePhone( candidateDto.getCandidatePhone() );
        candidate.setCandidateCreatedAt( candidateDto.getCandidateCreatedAt() );

        return candidate;
    }

    protected Template templateDtoToTemplate(TemplateDto templateDto) {
        if ( templateDto == null ) {
            return null;
        }

        Template template = new Template();

        template.setTemplateId( templateDto.getTemplateId() );
        template.setTemplateName( templateDto.getTemplateName() );
        template.setTemplateSubject( templateDto.getTemplateSubject() );
        template.setTemplateBody( templateDto.getTemplateBody() );
        template.setTemplateText( templateDto.getTemplateText() );

        return template;
    }

    protected Request requestDtoToRequest(RequestDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        Request request = new Request();

        request.setRequestId( requestDto.getRequestId() );
        request.setUser( userDtoToUser( requestDto.getUser() ) );
        request.setCandidate( candidateDtoToCandidate( requestDto.getCandidate() ) );
        request.setTemplate( templateDtoToTemplate( requestDto.getTemplate() ) );
        request.setRequestToken( requestDto.getRequestToken() );
        request.setRequestState( requestDto.getRequestState() );
        request.setRequestDate( requestDto.getRequestDate() );

        return request;
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

    protected CandidateDto candidateToCandidateDto(Candidate candidate) {
        if ( candidate == null ) {
            return null;
        }

        CandidateDto candidateDto = new CandidateDto();

        candidateDto.setCandidateId( candidate.getCandidateId() );
        candidateDto.setCandidateFirstName( candidate.getCandidateFirstName() );
        candidateDto.setCandidateLastName( candidate.getCandidateLastName() );
        candidateDto.setCandidateFatherName( candidate.getCandidateFatherName() );
        candidateDto.setCandidateMail( candidate.getCandidateMail() );
        candidateDto.setCandidateBirthDate( candidate.getCandidateBirthDate() );
        candidateDto.setCandidatePhone( candidate.getCandidatePhone() );
        candidateDto.setCandidateCreatedAt( candidate.getCandidateCreatedAt() );

        return candidateDto;
    }

    protected TemplateDto templateToTemplateDto(Template template) {
        if ( template == null ) {
            return null;
        }

        TemplateDto templateDto = new TemplateDto();

        templateDto.setTemplateId( template.getTemplateId() );
        templateDto.setTemplateName( template.getTemplateName() );
        templateDto.setTemplateSubject( template.getTemplateSubject() );
        templateDto.setTemplateBody( template.getTemplateBody() );
        templateDto.setTemplateText( template.getTemplateText() );

        return templateDto;
    }

    protected RequestDto requestToRequestDto(Request request) {
        if ( request == null ) {
            return null;
        }

        RequestDto requestDto = new RequestDto();

        requestDto.setRequestId( request.getRequestId() );
        requestDto.setUser( userToUserDto( request.getUser() ) );
        requestDto.setCandidate( candidateToCandidateDto( request.getCandidate() ) );
        requestDto.setTemplate( templateToTemplateDto( request.getTemplate() ) );
        requestDto.setRequestToken( request.getRequestToken() );
        requestDto.setRequestState( request.getRequestState() );
        requestDto.setRequestDate( request.getRequestDate() );

        return requestDto;
    }
}
