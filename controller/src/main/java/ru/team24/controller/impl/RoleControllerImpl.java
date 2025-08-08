package ru.team24.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.team24.controller.RoleController;

@RequestMapping("/api/v1/roles")
@RestController
@RequiredArgsConstructor
public class RoleControllerImpl implements RoleController {

    @Override
    public ResponseEntity<?> findByRoleId(long id) {

        return null;
    }
}
