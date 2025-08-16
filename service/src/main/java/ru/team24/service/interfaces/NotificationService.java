package ru.team24.service.interfaces;

import ru.team24.service.dto.NotificationDto;

import java.util.List;

public interface NotificationService {
    NotificationDto finByNotificationId(long notificationId);
    List<NotificationDto> finByNotificationState(String state);
    void createNotification(NotificationDto notificationDto);
}
