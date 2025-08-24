package ru.team24.service.domain.general.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.team24.database.domain.general.entity.Role;
import ru.team24.database.domain.general.repository.RoleRepository;
import ru.team24.service.dto.RoleDto;
import ru.team24.service.mapper.RoleMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {

    @Mock
    private RoleMapper roleMapper;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleServiceImpl roleService;

    private Role testRole;
    private RoleDto testRoleDto;
    private final long TEST_ROLE_ID = 1L;

    @BeforeEach
    void setUp() {
        testRole = new Role();
        testRole.setRoleId(TEST_ROLE_ID);
        testRole.setRoleName("ROLE_USER");

        testRoleDto = new RoleDto();
        testRoleDto.setRoleId(TEST_ROLE_ID);
        testRoleDto.setRoleName("ROLE_USER");
    }

    @Test
    void findByRoleId_shouldReturnRoleDto_whenRoleExists() {
        when(roleRepository.findById(TEST_ROLE_ID)).thenReturn(Optional.of(testRole));
        when(roleMapper.entityToDto(testRole)).thenReturn(testRoleDto);


        RoleDto result = roleService.findByRoleId(TEST_ROLE_ID);


        assertNotNull(result);
        assertEquals(TEST_ROLE_ID, result.getRoleId());
        assertEquals("ROLE_USER", result.getRoleName());

        verify(roleRepository).findById(TEST_ROLE_ID);
        verify(roleMapper).entityToDto(testRole);
    }

    @Test
    void findByRoleId_shouldReturnNull_whenRoleIsNull() {
        when(roleRepository.findById(TEST_ROLE_ID)).thenReturn(Optional.of(testRole));
        when(roleMapper.entityToDto(testRole)).thenReturn(null);


        RoleDto result = roleService.findByRoleId(TEST_ROLE_ID);


        assertNull(result);
        verify(roleRepository).findById(TEST_ROLE_ID);
        verify(roleMapper).entityToDto(testRole);
    }
}