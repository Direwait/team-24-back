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
import ru.team24.service.domain.general.UserService;
import ru.team24.service.dto.user.UserDto;
import ru.team24.service.payload.request.AddNewUserRequest;


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
    private AddNewUserRequest addNewUserRequest;

    @BeforeEach
    void setUp() {
        userDto = new UserDto();
        userDto.setUserId(1L);
        userDto.setUserMail("test@example.com");
        userDto.setUserPassword("plainPassword");

        addNewUserRequest = new AddNewUserRequest();
        addNewUserRequest.setUserMail("test@example.com");
        addNewUserRequest.setUserPassword("plainPassword");
        // Установите другие необходимые поля для AddNewUserRequest
    }

    @Test
    void findByUserId_ShouldReturnUserAndOkStatus() {

        when(userService.findByUserId(anyLong())).thenReturn(userDto);


        ResponseEntity<?> response = userController.findByUserId(1L);


        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDto, response.getBody());
        verify(userService).findByUserId(1L);
    }

    @Test
    void softDeleteUserById_ShouldReturnOkStatus() {

        doNothing().when(userService).softDeleteUserById(anyLong());


        ResponseEntity<?> response = userController.softDeleteUserById(1L);


        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userService).softDeleteUserById(1L);
    }

    @Test
    void existsByEmail_WhenEmailExists_ShouldReturnConflictStatus() {

        when(userService.existsByUserMail(anyString())).thenReturn(true);


        ResponseEntity<?> response = userController.existsByEmail("test@example.com");


        assertNotNull(response);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        verify(userService).existsByUserMail("test@example.com");
    }

    @Test
    void existsByEmail_WhenEmailDoesNotExist_ShouldReturnOkStatus() {

        when(userService.existsByUserMail(anyString())).thenReturn(false);


        ResponseEntity<?> response = userController.existsByEmail("test@example.com");


        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userService).existsByUserMail("test@example.com");
    }

    @Test
    void addUser_ShouldEncodePasswordAndReturnCreatedStatus() {

        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        doNothing().when(userService).addUser(any(AddNewUserRequest.class));


        ResponseEntity<?> response = userController.addUser(addNewUserRequest);


        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());


        assertEquals("encodedPassword", addNewUserRequest.getUserPassword());
        verify(passwordEncoder).encode("plainPassword");
        verify(userService).addUser(addNewUserRequest);
    }

    @Test
    void addUser_ShouldCallServiceWithEncodedPassword() {

        when(passwordEncoder.encode("plainPassword")).thenReturn("encodedPassword");
        doNothing().when(userService).addUser(any(AddNewUserRequest.class));


        userController.addUser(addNewUserRequest);


        verify(passwordEncoder).encode("plainPassword");
        verify(userService).addUser(argThat(request ->
                "encodedPassword".equals(request.getUserPassword()) &&
                        "test@example.com".equals(request.getUserMail())
        ));
    }
}