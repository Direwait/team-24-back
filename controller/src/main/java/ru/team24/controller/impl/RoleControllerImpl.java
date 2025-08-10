package ru.team24.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.team24.controller.RoleController;
import ru.team24.service.interfaces.RoleService;

@RequestMapping("/api/v1/roles")
@RestController
@RequiredArgsConstructor
public class RoleControllerImpl implements RoleController {

    private final RoleService roleService;

    @GetMapping("/{roleId}")
    @Override
    public ResponseEntity<?> findByRoleId(@PathVariable long roleId) {

        return null;
    }
}
