package com.gateway.Gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
public class HealthController {

    @GetMapping("/health")
    public Mono<Map<String, String>> health() {
        return Mono.just(Map.of("status", "UP", "service", "Gateway"));
    }

    @GetMapping("/")
    public Mono<Map<String, String>> root() {
        return Mono.just(Map.of(
            "message", "Gateway is running",
            "endpoints", "/health, /api/users/**, /api/experiences/**, /api/tags/**, /api/likes/**"
        ));
    }
}