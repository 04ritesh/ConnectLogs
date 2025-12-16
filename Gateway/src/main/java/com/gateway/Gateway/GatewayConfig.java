package com.gateway.Gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/api/users/**")
                        .uri("http://localhost:8081"))
                .route("experience-service", r -> r.path("/api/experiences/**")
                        .uri("http://localhost:8082"))
                .route("tag-service", r -> r.path("/api/tags/**")
                        .uri("http://localhost:8083"))
                .route("likes-service", r -> r.path("/api/likes/**")
                        .uri("http://localhost:8084"))
                .build();
    }
}