package ru.team24.service.domain.general.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.team24.database.domain.general.entity.RefreshToken;
import ru.team24.database.domain.general.entity.Role;
import ru.team24.database.domain.general.entity.User;
import ru.team24.database.domain.general.repository.UserRepository;
import ru.team24.service.payload.request.SignInRequest;
import ru.team24.service.payload.response.RefreshResponse;
import ru.team24.service.payload.response.SignInResponse;
import ru.team24.service.security.JwtService;
import ru.team24.service.security.RefreshTokenService;
import ru.team24.service.security.UserDetailsImpl;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @Mock
    private RefreshTokenService refreshTokenService;

    @InjectMocks
    private AuthServiceImpl authService;

    private SignInRequest signInRequest;
    private Authentication authentication;
    private UserDetailsImpl userDetails;
    private User testUser;
    private Role testRole;
    private RefreshToken refreshToken;

    @BeforeEach
    void setUp() {
        signInRequest = new SignInRequest();
        signInRequest.setEmail("test@example.com");
        signInRequest.setPassword("password");

        testRole = new Role();
        testRole.setRoleName("ROLE_USER");

        testUser = new User();
        testUser.setUserId(1L);
        testUser.setUserMail("test@example.com");
        testUser.setUserPassword("encodedPassword");
        testUser.setRole(testRole);


        userDetails = new UserDetailsImpl(testUser);

        authentication = mock(Authentication.class);

        refreshToken = new RefreshToken();
        refreshToken.setRefreshToken("refresh-token");
        refreshToken.setUser(testUser);


        SecurityContextHolder.clearContext();
    }

    @Test
    void signIn_shouldReturnSignInResponse_whenCredentialsAreValid() {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(jwtService.generateToken(authentication)).thenReturn("jwt-token");
        when(refreshTokenService.generateRefreshToken(userDetails.getId())).thenReturn(refreshToken);


        SignInResponse response = authService.signIn(signInRequest);


        assertNotNull(response);
        assertEquals("ROLE_USER", response.getRole());
        assertEquals("jwt-token", response.getAccessToken());
        assertEquals("refresh-token", response.getRefreshToken());

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtService).generateToken(authentication);
        verify(refreshTokenService).generateRefreshToken(userDetails.getId());


        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void signIn_shouldThrowBadCredentialsException_whenCredentialsAreInvalid() {

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Invalid credentials"));


        BadCredentialsException exception = assertThrows(BadCredentialsException.class,
                () -> authService.signIn(signInRequest));

        assertEquals("Неверный email или пароль", exception.getMessage());
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtService, never()).generateToken(any());
        verify(refreshTokenService, never()).generateRefreshToken(any());
    }

    @Test
    void logout_shouldDeleteTokens_whenAccessTokenStartsWithBearer() {
        String accessToken = "Bearer jwt-token";
        String refreshToken = "refresh-token";


        authService.logout(accessToken, refreshToken);


        verify(jwtService).deleteToken("jwt-token");
        verify(refreshTokenService).deleteRefreshToken("refresh-token");
    }

    @Test
    void logout_shouldDeleteTokens_whenAccessTokenDoesNotStartWithBearer() {

        String accessToken = "jwt-token";
        String refreshToken = "refresh-token";


        authService.logout(accessToken, refreshToken);


        verify(jwtService).deleteToken("jwt-token");
        verify(refreshTokenService).deleteRefreshToken("refresh-token");
    }

    @Test
    void logout_shouldHandleNullAccessToken() {

        String accessToken = null;
        String refreshToken = "refresh-token";


        authService.logout(accessToken, refreshToken);


        verify(jwtService).deleteToken(null);
        verify(refreshTokenService).deleteRefreshToken("refresh-token");
    }

    @Test
    void refresh_shouldReturnRefreshResponse_whenAccessTokenDoesNotStartWithBearer() {

        String accessToken = "old-jwt-token";
        String refreshTokenValue = "refresh-token";

        when(refreshTokenService.findByRefreshToken(refreshTokenValue))
                .thenReturn(Optional.of(refreshToken));
        when(refreshTokenService.checkExpired(refreshToken)).thenReturn(refreshToken);
        when(jwtService.generateTokenByLogin(testUser.getUserMail())).thenReturn("new-jwt-token");


        RefreshResponse response = authService.refresh(accessToken, refreshTokenValue);


        assertNotNull(response);
        assertEquals("new-jwt-token", response.getAccessToken());

        verify(jwtService).deleteToken("old-jwt-token");
    }

}