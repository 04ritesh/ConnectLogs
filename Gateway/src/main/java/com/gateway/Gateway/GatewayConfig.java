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
                        .uri("https://connectlogs-production-31ac.up.railway.app"))
                .route("experience-service", r -> r.path("/api/experiences/**")
                        .uri("https://connectlogs-production-23c9.up.railway.app"))
                .route("tag-service", r -> r.path("/api/tags/**")
                        .uri("https://triumphant-flexibility-production.up.railway.app"))
                .route("likes-service", r -> r.path("/api/likes/**")
                        .uri("https://connectlogs-production-640c.up.railway.app"))
                .build();
    }
}