package ru.team24.service.mapper;

import ru.team24.database.entities.Notification;
import ru.team24.service.dto.NotificationDto;

//@Mapper(componentModel = "spring")
public interface NotificationMapper {

    //@Mapping(source = "request.requestId", target = "request.requestId")
    Notification dtoToEntity(NotificationDto notificationDto);

    //@Mapping(source = "request.requestId", target = "request.requestId")
    NotificationDto entityToDto(Notification notification);
}
