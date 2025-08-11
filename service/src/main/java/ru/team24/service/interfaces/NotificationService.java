package ru.team24.service.interfaces;

import jakarta.persistence.Column;
import ru.team24.database.dto.NotificationDto;
import ru.team24.database.entities.Notification;
import ru.team24.database.entities.Request;
import ru.team24.database.enums.NotificationState;

import java.util.Date;

public interface NotificationService {
    NotificationDto finByNotificationId(long notificationId);
    void createNotification(long requestId,
                            String notificationText,
                            NotificationState notificationState,
                            Date notificationReadAt);
}
