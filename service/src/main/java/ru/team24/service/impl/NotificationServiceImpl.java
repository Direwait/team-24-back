package ru.team24.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.team24.database.dto.NotificationDto;
import ru.team24.database.entities.Notification;
import ru.team24.database.enums.NotificationState;
import ru.team24.database.repositories.NotificationRepository;
import ru.team24.database.repositories.RequestRepository;
import ru.team24.service.interfaces.NotificationService;
import ru.team24.service.mapper.NotificationMapper;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationMapper notificationMapper;
    private final NotificationRepository notificationRepository;
    private final RequestRepository requestRepository;

    public NotificationDto finByNotificationId(long notificationId) {
        var notification = notificationRepository.findById(notificationId).orElse(null);
        return notificationMapper.entityToDto(notification);
    }

    public void createNotification(long requestId,
                                   String notificationText,
                                   NotificationState notificationState,
                                   Date notificationReadAt) {
        var notification = new Notification();
        notification.setRequest(requestRepository.findById(requestId).orElse(null));
        notification.setNotificationText(notificationText);
        notification.setNotificationState(notificationState);
        notification.setNotificationReadAt(notificationReadAt);
        notificationRepository.save(notification);
    }
}
