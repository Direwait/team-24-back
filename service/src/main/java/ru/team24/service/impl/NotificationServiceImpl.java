package ru.team24.service.impl;

import org.springframework.stereotype.Service;
import ru.team24.database.entities.Notification;
import ru.team24.database.enums.NotificationState;
import ru.team24.service.interfaces.NotificationService;

import java.util.Date;

@Service
public class NotificationServiceImpl implements NotificationService {
    public Notification finByNotificationId(long notificationId) {
        return null;
    }

    public void createNotification(long requestId, String notificationText, NotificationState notificationState, Date notificationReadAt) {

    }
}
