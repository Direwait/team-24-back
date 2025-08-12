package ru.team24.service.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.team24.database.enums.NotificationState;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
    private long notificationId;

    @NotNull(message = "Request information is required")
    private RequestDto request;

    @NotBlank(message = "Notification text is required")
    @Size(max = 1000, message = "Notification text must not exceed 1000 characters")
    private String notificationText;

    @NotNull(message = "Notification state is required")
    private NotificationState notificationState;

    @NotNull(message = "Creation date is required")
    @PastOrPresent(message = "Creation date must be in the past or present")
    private Date notificationCreatedAt;

    @PastOrPresent(message = "Read date must be in the past or present")
    private Date notificationReadAt;
}
