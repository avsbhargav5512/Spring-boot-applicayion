package com.example.cicd.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public String getGreeting(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "Hello, World! CI/CD build is successful!";
        }
        return "Hello, " + name.trim() + "! CI/CD build is successful!";
    }
}
