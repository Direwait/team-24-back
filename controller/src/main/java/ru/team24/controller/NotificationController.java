package ru.team24.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Notifications. Уведомления", description = "Операции связанные с уведомлениями")
public interface NotificationController {

    @Operation(
            summary = "Список уведомление по Id"
    )
    ResponseEntity<?> findByNotificationId(long id);

    @Operation(
            summary = "Список всех уведомлений по статусу. Notification State"
    )
    ResponseEntity<List<?>> getByNotificationState(Enum state);

    @Operation(
            summary = "Создание уведомления. Notification"
    )
    ResponseEntity<?> createNotification();
}
