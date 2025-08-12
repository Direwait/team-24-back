package ru.team24.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.team24.controller.NotificationController;
import ru.team24.service.dto.NotificationDto;
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
        return new ResponseEntity<>(notificationService.finByNotificationId(notificationId), HttpStatus.OK);
    }

    @GetMapping("/getByState/{notificationState}")
    public ResponseEntity<List<?>> getByNotificationState(@PathVariable String notificationState) {
        return new ResponseEntity<>(notificationService.finByNotificationState(notificationState), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createNotification(@RequestBody NotificationDto notificationDto) {
        notificationService.createNotification(notificationDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
