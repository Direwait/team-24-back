package ru.team24.service.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.team24.database.entities.Notification;
import ru.team24.database.repositories.NotificationRepository;
import ru.team24.database.repositories.RequestRepository;
import ru.team24.service.dto.NotificationDto;
import ru.team24.service.mapper.NotificationMapper;

@Component
@RequiredArgsConstructor
public class NotificationMapperImpl implements NotificationMapper {
    @Autowired
    RequestRepository repositoryRepository;
    public Notification dtoToEntity(NotificationDto notificationDto) {
        if (notificationDto == null) {
            return null;
        }

        Notification notification = new Notification();

        notification.setNotificationId(notificationDto.getNotificationId());
        notification.setNotificationState(notificationDto.getNotificationState());
        notification.setRequest(repositoryRepository.findByRequestId(notificationDto.getRequestId()).orElseThrow());
        notification.setNotificationText(notificationDto.getNotificationText());
        notification.setNotificationReadAt(notificationDto.getNotificationReadAt());
        //notification.setNotificationCreatedAt(notificationDto.getNotificationCreatedAt());

        return notification;
    }

    public NotificationDto entityToDto(Notification notification) {
        if (notification == null) {
            return null;
        }

        NotificationDto notificationDto = new NotificationDto();

        notificationDto.setNotificationId(notification.getNotificationId());
        notificationDto.setNotificationState(notification.getNotificationState());
        notificationDto.setNotificationText(notification.getNotificationText());
        notificationDto.setNotificationReadAt(notification.getNotificationReadAt());
        notificationDto.setNotificationCreatedAt(notification.getNotificationCreatedAt());
        notificationDto.setRequestId(notification.getRequestId());

        return notificationDto;
    }
}
