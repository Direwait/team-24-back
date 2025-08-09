package ru.team24.service.interfaces;

import ru.team24.database.entities.Role;

public interface RoleService {
    Role findByRoleId(long roleId);
}
