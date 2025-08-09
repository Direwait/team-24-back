package ru.team24.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.team24.database.entities.Notification;
import ru.team24.database.enums.NotificationState;
import ru.team24.database.repositories.NotificationRepository;
import ru.team24.service.interfaces.NotificationService;
import ru.team24.service.mapper.NotificationMapper;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationMapper notificationMapper;
    private final NotificationRepository notificationRepository;

    public Notification finByNotificationId(long notificationId) {
        return null;
    }

    public void createNotification(long requestId, String notificationText, NotificationState notificationState, Date notificationReadAt) {

    }
}
