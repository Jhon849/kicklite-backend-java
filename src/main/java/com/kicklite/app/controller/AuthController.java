package com.kicklite.app.controller;

import com.kicklite.app.dto.*;
import com.kicklite.app.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        service.register(req);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest req) {
        return service.login(req);
    }
}
