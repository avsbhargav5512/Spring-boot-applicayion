package com.example.cicd.controller;

import com.example.cicd.service.GreetingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@RestController
public class HelloController {

    private final GreetingService greetingService;

    public HelloController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/")
    public String root() {
        return "Welcome to Spring Boot CI/CD Practice App!";
    }

    @GetMapping("/api/hello")
    public Map<String, String> hello(@RequestParam(required = false) String name) {
        return Map.of(
                "message", greetingService.getGreeting(name),
                "timestamp", Instant.now().toString()
        );
    }

    @GetMapping("/api/health")
    public ResponseEntity<Map<String, Object>> health() {
        return ResponseEntity.ok(Map.of(
                "status", "UP",
                "app", "spring-boot-cicd-practice",
                "timestamp", Instant.now().toString()
        ));
    }
}
