package ru.team24.controller.domain.general.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.team24.service.domain.general.RoleService;
import ru.team24.service.dto.RoleDto;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleControllerImplTest {

    @Mock
    private RoleService roleService;

    @InjectMocks
    private RoleControllerImpl roleController;

    @Test
    void findByRoleId_ShouldReturnRoleAndOkStatus() {
        // Arrange
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleId(1L);
        roleDto.setRoleName("ADMIN");
        when(roleService.findByRoleId(anyLong())).thenReturn(roleDto);

        // Act
        ResponseEntity<?> response = roleController.findByRoleId(1L);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(roleDto, response.getBody());
        verify(roleService).findByRoleId(1L);
    }

    @Test
    void findByRoleId_WithNonExistentId_ShouldReturnOkWithNull() {
        // Arrange
        when(roleService.findByRoleId(anyLong())).thenReturn(null);

        // Act
        ResponseEntity<?> response = roleController.findByRoleId(999L);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
        verify(roleService).findByRoleId(999L);
    }

    @Test
    void findByRoleId_ShouldCallServiceWithCorrectId() {
        // Arrange
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleId(2L);
        roleDto.setRoleName("USER");
        when(roleService.findByRoleId(anyLong())).thenReturn(roleDto);

        // Act
        ResponseEntity<?> response = roleController.findByRoleId(2L);

        // Assert
        verify(roleService).findByRoleId(2L);
        assertEquals(roleDto, response.getBody());
    }

    @Test
    void findByRoleId_WithZeroId_ShouldHandleGracefully() {
        // Arrange
        when(roleService.findByRoleId(anyLong())).thenReturn(null);

        // Act
        ResponseEntity<?> response = roleController.findByRoleId(0L);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(roleService).findByRoleId(0L);
    }

    @Test
    void findByRoleId_WithNegativeId_ShouldHandleGracefully() {
        // Arrange
        when(roleService.findByRoleId(anyLong())).thenReturn(null);

        // Act
        ResponseEntity<?> response = roleController.findByRoleId(-1L);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(roleService).findByRoleId(-1L);
    }
}