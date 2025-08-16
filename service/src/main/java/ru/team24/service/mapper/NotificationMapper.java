package ru.team24.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.team24.database.domain.manager.entity.Notification;
import ru.team24.service.dto.NotificationDto;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    @Mapping(source = "requestId", target = "request.requestId")
    Notification dtoToEntity(NotificationDto notificationDto);

    @Mapping(source = "request.requestId", target = "requestId")
    NotificationDto entityToDto(Notification notification);
}
