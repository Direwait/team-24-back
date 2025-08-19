package ru.team24.service.domain.general.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.team24.database.domain.general.entity.RefreshToken;
import ru.team24.database.domain.general.repository.UserRepository;
import ru.team24.service.domain.general.AuthService;
import ru.team24.service.payload.request.SignInRequest;
import ru.team24.service.payload.response.RefreshResponse;
import ru.team24.service.payload.response.SignInResponse;
import ru.team24.service.security.JwtService;
import ru.team24.service.security.RefreshTokenService;
import ru.team24.service.security.UserDetailsImpl;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private RefreshTokenService refreshTokenService;

    public SignInResponse signIn(SignInRequest signInRequest) throws BadCredentialsException {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword())
            );
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtService.generateToken(authentication);
            List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
            var refreshToken = refreshTokenService.generateRefreshToken(userDetails.getId());
            var response = new SignInResponse();
            response.setRole(roles.getFirst());
            response.setAccessToken(jwt);
            response.setRefreshToken(refreshToken.getRefreshToken());
            return response;
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Неверный email или пароль");
        }
    }

    public void logout(String accessToken, String refreshToken) {
        if (accessToken != null && accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }
        jwtService.deleteToken(accessToken);
        refreshTokenService.deleteRefreshToken(refreshToken);
    }

    public RefreshResponse refresh(String accessToken, String refreshToken) {
        if (accessToken != null && accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }
        var refreshTokenEntity = refreshTokenService.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Token %s is not found", refreshToken)));
        var role = refreshTokenEntity.getUser().getRole().getRoleName();
        jwtService.deleteToken(accessToken);
        return refreshTokenService.findByRefreshToken(refreshToken)
                .map(refreshTokenService::checkExpired)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtService.generateTokenByLogin(user.getUserMail());
                    return new RefreshResponse(role, token);
                }).orElseThrow(() -> new RuntimeException("Refresh token is missing"));
    }
}
