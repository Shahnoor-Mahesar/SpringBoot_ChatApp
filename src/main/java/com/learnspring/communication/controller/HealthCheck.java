package com.learnspring.communication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
    @GetMapping("/api/health")
    public String healthCheck() {
        return "OK_ALL_IS_WELL";
    }
}
