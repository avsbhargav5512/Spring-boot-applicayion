package com.example.cicd;

import com.example.cicd.service.GreetingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GreetingServiceTest {

    private GreetingService greetingService;

    @BeforeEach
    void setUp() {
        greetingService = new GreetingService();
    }

    @Test
    void testGetGreeting_Default() {
        String result = greetingService.getGreeting(null);
        assertEquals("Hello, World! CI/CD build is successful!", result);
    }

    @Test
    void testGetGreeting_WithCustomName() {
        String result = greetingService.getGreeting("Jenkins");
        assertEquals("Hello, Jenkins! CI/CD build is successful!", result);
    }

    @Test
    void testGetGreeting_WithWhitespace() {
        String result = greetingService.getGreeting("   DevOps   ");
        assertEquals("Hello, DevOps! CI/CD build is successful!", result);
    }
}
