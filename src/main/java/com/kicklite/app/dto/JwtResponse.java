package com.kicklite.app.dto;
import java.util.List;
public record JwtResponse(String token, String email, List<String> roles) {}
