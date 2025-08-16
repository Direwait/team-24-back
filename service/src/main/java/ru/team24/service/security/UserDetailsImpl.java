package ru.team24.service.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.team24.database.entities.User;

import java.util.Collection;
import java.util.stream.Stream;

public class UserDetailsImpl implements UserDetails {
    private final User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Stream.of(user.getRole().getRoleName()).map(SimpleGrantedAuthority::new).toList();
    }

    public String getPassword() {
        return user.getUserPassword();
    }

    public String getUsername() {
        return user.getUserMail();
    }

    public long getId(){
        return user.getUserId();
    }
}
