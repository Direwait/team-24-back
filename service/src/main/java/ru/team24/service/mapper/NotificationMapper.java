package ru.team24.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.team24.service.dto.NotificationDto;
import ru.team24.database.entities.Notification;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    @Mapping(source = "request.requestId", target = "request.requestId")
    Notification dtoToEntity(NotificationDto notificationDto);

    @Mapping(source = "request.requestId", target = "request.requestId")
    NotificationDto entityToDto(Notification notification);
}
