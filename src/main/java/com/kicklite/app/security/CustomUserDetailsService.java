package com.kicklite.app.security;

import com.kicklite.app.model.User;
import com.kicklite.app.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repo;

    public CustomUserDetailsService(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User u = repo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
        return new org.springframework.security.core.userdetails.User(
                u.getEmail(),
                u.getPassword(),
                u.getRoles().stream()
                        .map(r -> new SimpleGrantedAuthority(r.getName()))
                        .collect(Collectors.toList())
        );
    }
}
