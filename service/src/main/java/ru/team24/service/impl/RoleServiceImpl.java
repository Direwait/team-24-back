package ru.team24.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.team24.database.entities.Role;
import ru.team24.database.repositories.RoleRepository;
import ru.team24.service.interfaces.RoleService;
import ru.team24.service.mapper.RoleMapper;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;

    public Role findByRoleId(long roleId) {
        return null;
    }
}
