package org.alibou.demo.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // user store
    private static final List<User> USERS = List.of(
            new User(
                    "alibou",
                    "$2a$12$TAso/0rGAh0/lldoKldEcuHLj2pg2cvOhyUefqEt/fx4mEcoQZFGu",
                    List.of(new SimpleGrantedAuthority("user"))
            ),
            new User(
                    "mohammed",
                    "password",
                    List.of(new SimpleGrantedAuthority("manager"))
            )
            , new User(
                    "admin",
                    "$2a$12$py72QO1KHm/87Q6fUL9/uOh47w7HMIUJeaEYFiLzS0M/XM1NlYjou",
                    List.of(new SimpleGrantedAuthority("admin"))
            )
    );

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return USERS
                .stream()
                .filter(user -> username.equals(user.getUsername()))
                .findAny()
                .orElseThrow(() -> new UsernameNotFoundException("No user found with username " + username))
                ;
    }
}
