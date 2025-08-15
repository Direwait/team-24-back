package ru.team24.service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.team24.database.repositories.RefreshTokenRepository;
import ru.team24.database.repositories.UserRepository;
import ru.team24.service.payload.request.SignInRequest;
import ru.team24.service.payload.response.SignInResponse;
import ru.team24.service.security.JwtService;
import ru.team24.service.security.RefreshTokenService;
import ru.team24.service.security.UserDetailsImpl;

import java.util.List;

@Service
public class AuthService {
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
    public SignInResponse signIn(SignInRequest signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword())
        );
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateToken(authentication);
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        var refreshToken = refreshTokenService.generateRefreshToken(userDetails.getId());
        var response =  new SignInResponse();
        response.setRole(roles.getFirst());
        response.setAccessToken(jwt);
        response.setRefreshToken(refreshToken.getRefreshToken());
        return response;
    }
}
