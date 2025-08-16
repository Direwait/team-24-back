package ru.team24.service.domain.general;

import ru.team24.service.dto.RoleDto;

public interface RoleService {
    RoleDto findByRoleId(long roleId);
}
