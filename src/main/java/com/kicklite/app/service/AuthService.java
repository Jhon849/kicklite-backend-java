package com.kicklite.app.service;

import com.kicklite.app.dto.*;
import com.kicklite.app.model.Role;
import com.kicklite.app.model.User;
import com.kicklite.app.repository.RoleRepository;
import com.kicklite.app.repository.UserRepository;
import com.kicklite.app.security.JwtUtil;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    private final UserRepository users;
    private final RoleRepository roles;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtUtil jwt;

    public AuthService(
            UserRepository users,
            RoleRepository roles,
            PasswordEncoder encoder,
            AuthenticationManager authManager,
            JwtUtil jwt
    ) {
        this.users = users;
        this.roles = roles;
        this.encoder = encoder;
        this.authManager = authManager;
        this.jwt = jwt;
    }

    public void register(RegisterRequest req) {
        if (users.existsByEmail(req.email()))
            throw new RuntimeException("Email already exists");

        User u = new User();
        u.setEmail(req.email());
        u.setFullName(req.fullName());
        u.setPassword(encoder.encode(req.password()));

        Role roleUser = roles.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Missing ROLE_USER"));

        u.getRoles().add(roleUser);
        users.save(u);
    }

    public JwtResponse login(LoginRequest req) {

        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.email(), req.password()));

        var user = users.findByEmail(req.email())
                .orElseThrow();

        String token = jwt.generate(user.getEmail());

        return new JwtResponse(token, user.getId(), user.getEmail(), user.getFullName());
    }
}

