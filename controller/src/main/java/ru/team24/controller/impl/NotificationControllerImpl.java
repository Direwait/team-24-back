package ru.team24.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.team24.controller.NotificationController;
import ru.team24.service.interfaces.NotificationService;

import java.util.List;


@RequestMapping("/api/v1/notifications")
@RestController
@RequiredArgsConstructor
public class NotificationControllerImpl implements NotificationController {

    private final NotificationService notificationService;


    @GetMapping("/{notificationId}")
    @Override
    public ResponseEntity<?> findByNotificationId(@PathVariable long notificationId) {

        return null;
    }

    @GetMapping("/{notificationState}")
    @Override
    public ResponseEntity<List<?>> getByNotificationState(@PathVariable Enum notificationState) {

        return null;
    }

    @PostMapping()
    @Override
    public ResponseEntity<?> createNotification() {
        return null;
    }
}
