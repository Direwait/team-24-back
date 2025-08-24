package ru.team24.service.domain.general.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.team24.database.domain.general.entity.Role;
import ru.team24.database.domain.general.entity.User;
import ru.team24.database.domain.general.repository.RoleRepository;
import ru.team24.database.domain.general.repository.UserRepository;
import ru.team24.service.dto.UserDto;
import ru.team24.service.mapper.UserMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User testUser;
    private UserDto testUserDto;
    private User inactiveUser;
    private final long TEST_USER_ID = 1L;
    private final String TEST_EMAIL = "test@example.com";

    @BeforeEach
    void setUp() {
        Role testRole = new Role();
        testRole.setRoleId(1L);
        testRole.setRoleName("ROLE_USER");

        testUser = new User();
        testUser.setUserId(TEST_USER_ID);
        testUser.setUserMail(TEST_EMAIL);
        testUser.setUserIsActive(true);
        testUser.setRole(testRole);

        inactiveUser = new User();
        inactiveUser.setUserId(2L);
        inactiveUser.setUserMail("inactive@example.com");
        inactiveUser.setUserIsActive(false);
        inactiveUser.setRole(testRole);

        testUserDto = new UserDto();
        testUserDto.setUserId(TEST_USER_ID);
        testUserDto.setUserMail(TEST_EMAIL);
        testUserDto.setUserIsActive(true);
    }

    @Test
    void findByUserId_shouldReturnUserDto_whenUserExists() {
        when(userRepository.findById(TEST_USER_ID)).thenReturn(Optional.of(testUser));
        when(userMapper.userToUserDto(testUser)).thenReturn(testUserDto);

        UserDto result = userService.findByUserId(TEST_USER_ID);

        assertNotNull(result);
        assertEquals(TEST_USER_ID, result.getUserId());
        assertEquals(TEST_EMAIL, result.getUserMail());

        verify(userRepository).findById(TEST_USER_ID);
        verify(userMapper).userToUserDto(testUser);
    }

    @Test
    void findByUserId_shouldReturnNull_whenUserMapperReturnsNull() {
        when(userRepository.findById(TEST_USER_ID)).thenReturn(Optional.of(testUser));
        when(userMapper.userToUserDto(testUser)).thenReturn(null);

        UserDto result = userService.findByUserId(TEST_USER_ID);

        assertNull(result);
        verify(userRepository).findById(TEST_USER_ID);
        verify(userMapper).userToUserDto(testUser);
    }

    @Test
    void findAllUsers_shouldReturnOnlyActiveUsers() {
        List<User> users = List.of(testUser, inactiveUser);
        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.userToUserDto(testUser)).thenReturn(testUserDto);

        List<UserDto> result = userService.findAllUsers();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(TEST_USER_ID, result.get(0).getUserId());

        verify(userRepository).findAll();
        verify(userMapper).userToUserDto(testUser);
        verify(userMapper, never()).userToUserDto(inactiveUser);
    }

    @Test
    void findAllUsers_shouldReturnEmptyList_whenNoActiveUsers() {
        List<User> users = List.of(inactiveUser);
        when(userRepository.findAll()).thenReturn(users);

        List<UserDto> result = userService.findAllUsers();

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(userRepository).findAll();
        verify(userMapper, never()).userToUserDto(any());
    }

    @Test
    void existsByUserMail_shouldReturnTrue_whenEmailExists() {
        when(userRepository.existsUserByUserMail(TEST_EMAIL)).thenReturn(true);

        boolean result = userService.existsByUserMail(TEST_EMAIL);

        assertTrue(result);
        verify(userRepository).existsUserByUserMail(TEST_EMAIL);
    }

    @Test
    void existsByUserMail_shouldReturnFalse_whenEmailDoesNotExist() {
        String NON_EXISTENT_EMAIL = "nonexistent@example.com";
        when(userRepository.existsUserByUserMail(NON_EXISTENT_EMAIL)).thenReturn(false);

        boolean result = userService.existsByUserMail(NON_EXISTENT_EMAIL);

        assertFalse(result);
        verify(userRepository).existsUserByUserMail(NON_EXISTENT_EMAIL);
    }

    @Test
    void addUser_shouldSaveUser() {
        when(userMapper.userDtoToUser(testUserDto)).thenReturn(testUser);
        when(userRepository.save(testUser)).thenReturn(testUser);

        userService.addUser(testUserDto);

        verify(userMapper).userDtoToUser(testUserDto);
        verify(userRepository).save(testUser);
    }
}