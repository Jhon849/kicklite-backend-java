package com.kicklite.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/")
    public String home() {
        return "✅ KickLite backend corriendo correctamente en Railway!";
    }
}
