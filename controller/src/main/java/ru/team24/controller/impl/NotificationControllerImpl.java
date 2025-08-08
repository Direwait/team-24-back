package ru.team24.controller.impl;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.team24.controller.NotificationController;

import java.util.List;


@RequestMapping("/api/v1/notifications")
@RestController

public class NotificationControllerImpl implements NotificationController {



    @GetMapping("/{id}")
    @Override
    public ResponseEntity<?> findByNotificationId(long id) {

        return null;
    }

    @GetMapping("/{state}")
    @Override
    public ResponseEntity<List<?>> getByNotificationState(Enum state) {

        return null;
    }
}
