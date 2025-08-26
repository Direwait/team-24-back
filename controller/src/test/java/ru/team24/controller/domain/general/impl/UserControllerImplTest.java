package ru.team24.controller.domain.general.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.team24.service.dto.UserDto;
import ru.team24.service.domain.general.UserService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerImplTest {

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserControllerImpl userController;

    private UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto = new UserDto();
        userDto.setUserId(1L);
        userDto.setUserMail("test@example.com");
        userDto.setUserPassword("plainPassword");
    }

    @Test
    void findByUserId_ShouldReturnUserAndOkStatus() {
        // Arrange
        when(userService.findByUserId(anyLong())).thenReturn(userDto);

        // Act
        ResponseEntity<?> response = userController.findByUserId(1L);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDto, response.getBody());
        verify(userService).findByUserId(1L);
    }

    @Test
    void existsByEmail_WhenEmailExists_ShouldReturnConflictStatus() {
        // Arrange
        when(userService.existsByUserMail(anyString())).thenReturn(true);

        // Act
        ResponseEntity<?> response = userController.existsByEmail("test@example.com");

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNull(response.getBody());
        verify(userService).existsByUserMail("test@example.com");
    }

    @Test
    void existsByEmail_WhenEmailDoesNotExist_ShouldReturnOkStatus() {
        // Arrange
        when(userService.existsByUserMail(anyString())).thenReturn(false);

        // Act
        ResponseEntity<?> response = userController.existsByEmail("test@example.com");

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
        verify(userService).existsByUserMail("test@example.com");
    }

    @Test
    void findAllUsers_ShouldReturnListOfUsersAndOkStatus() {
        // Arrange
        List<UserDto> users = List.of(userDto, new UserDto());
        when(userService.findAllUsers()).thenReturn(users);

        // Act
        ResponseEntity<List<?>> response = userController.findAllUsers();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
        verify(userService).findAllUsers();
    }

    @Test
    void findAllUsers_WhenNoUsers_ShouldReturnEmptyListAndOkStatus() {
        // Arrange
        List<UserDto> emptyList = List.of();
        when(userService.findAllUsers()).thenReturn(emptyList);

        // Act
        ResponseEntity<List<?>> response = userController.findAllUsers();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
        verify(userService).findAllUsers();
    }

    @Test
    void addUser_ShouldEncodePasswordAndReturnCreatedStatus() {
        // Arrange
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        doNothing().when(userService).addUser(any(UserDto.class));

        // Act
        ResponseEntity<?> response = userController.addUser(userDto);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNull(response.getBody());

        // Verify password was encoded
        assertEquals("encodedPassword", userDto.getUserPassword());
        verify(passwordEncoder).encode("plainPassword");
        verify(userService).addUser(userDto);
    }

    @Test
    void addUser_ShouldCallServiceWithEncodedPassword() {
        // Arrange
        when(passwordEncoder.encode("plainPassword")).thenReturn("encodedPassword");
        doNothing().when(userService).addUser(any(UserDto.class));

        // Act
        userController.addUser(userDto);

        // Assert
        verify(passwordEncoder).encode("plainPassword");
        verify(userService).addUser(argThat(dto ->
                "encodedPassword".equals(dto.getUserPassword()) &&
                        "test@example.com".equals(dto.getUserMail())
        ));
    }
}