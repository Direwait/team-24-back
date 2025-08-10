package ru.team24.controller.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.team24.controller.UserController;
import ru.team24.service.interfaces.UserService;

import java.util.List;

@RequestMapping("/api/v1/users")
@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @GetMapping("/userId")
    @Override
    public ResponseEntity<?> findByUserId(@PathVariable long userId) {

        return null;
    }

    @GetMapping("/{mail}")
    @Override
    public ResponseEntity<?> existsByEmail(@PathVariable String mail) {

        return null;
    }

    @GetMapping()
    @Override
    public ResponseEntity<List<?>> findAllUsers() {
        return null;
    }
}
