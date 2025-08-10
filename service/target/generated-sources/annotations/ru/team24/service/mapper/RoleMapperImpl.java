package ru.team24.service.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.team24.database.dto.RoleDto;
import ru.team24.database.entities.Role;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-10T00:08:39+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public Role dtoToEntity(RoleDto roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        Role role = new Role();

        role.setRoleId( roleDto.getRoleId() );
        role.setRoleName( roleDto.getRoleName() );
        role.setViewingMyRequests( roleDto.isViewingMyRequests() );
        role.setViewingAllRequests( roleDto.isViewingAllRequests() );
        role.setCreatingRequests( roleDto.isCreatingRequests() );
        role.setCreatingAdmins( roleDto.isCreatingAdmins() );

        return role;
    }

    @Override
    public RoleDto entityToDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setRoleId( role.getRoleId() );
        roleDto.setRoleName( role.getRoleName() );
        roleDto.setViewingMyRequests( role.isViewingMyRequests() );
        roleDto.setViewingAllRequests( role.isViewingAllRequests() );
        roleDto.setCreatingRequests( role.isCreatingRequests() );
        roleDto.setCreatingAdmins( role.isCreatingAdmins() );

        return roleDto;
    }
}
