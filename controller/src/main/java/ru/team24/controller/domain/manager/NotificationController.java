package ru.team24.controller.domain.manager;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import ru.team24.service.dto.NotificationDto;

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
    ResponseEntity<List<?>> getByNotificationState(String state);

    @Operation(
            summary = "Создание уведомления. Notification"
    )
    ResponseEntity<?> createNotification(NotificationDto notificationDto);
}
