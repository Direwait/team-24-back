package ru.team24.controller.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.team24.controller.UserController;
import ru.team24.service.dto.UserDto;
import ru.team24.service.interfaces.UserService;

import java.util.List;

@RequestMapping("/api/v1/users")
@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> findByUserId(@PathVariable long userId) {
        return new ResponseEntity<>(userService.findByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/{mail}")
    public ResponseEntity<?> existsByEmail(@PathVariable String mail) {
        if(userService.existsByUserMail(mail)){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<?>> findAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> addUser(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
