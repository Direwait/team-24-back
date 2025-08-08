package ru.team24.controller.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.team24.controller.UserController;

import java.util.List;

@RequestMapping("/api/v1/users")
@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    @Override
    public ResponseEntity<?> findByUserId(long id) {

        return null;
    }

    @Override
    public ResponseEntity<?> existsByEmail(String mail) {

        return null;
    }

    @Override
    public ResponseEntity<List<?>> findAllUsers() {
        return null;
    }
}
