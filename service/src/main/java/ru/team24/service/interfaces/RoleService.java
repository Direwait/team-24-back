package ru.team24.service.interfaces;

import ru.team24.database.dto.RoleDto;
import ru.team24.database.entities.Role;

public interface RoleService {
    RoleDto findByRoleId(long roleId);
}
