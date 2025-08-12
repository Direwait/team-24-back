package ru.team24.service.interfaces;

import ru.team24.service.dto.RoleDto;

public interface RoleService {
    RoleDto findByRoleId(long roleId);
}
