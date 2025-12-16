package com.gateway.Gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RequestPredicates.*;
import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration
public class GatewayConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public RouterFunction<ServerResponse> userServiceProxy(RestTemplate restTemplate) {
        return route(path("/api/users/**"), request -> {
            String targetUrl = "http://localhost:8081" + request.uri().getPath();
            if (request.uri().getQuery() != null) {
                targetUrl += "?" + request.uri().getQuery();
            }
            
            org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
            
            String body = request.body(String.class);
            org.springframework.http.HttpEntity<String> entity = new org.springframework.http.HttpEntity<>(body, headers);
            
            return ServerResponse.ok().body(restTemplate.exchange(
                    targetUrl,
                    request.method(),
                    entity,
                    String.class
            ).getBody());
        });
    }

    @Bean
    public RouterFunction<ServerResponse> experienceServiceProxy(RestTemplate restTemplate) {
        return route(path("/api/experiences/**"), request -> {
            String targetUrl = "http://localhost:8082" + request.uri().getPath();
            if (request.uri().getQuery() != null) {
                targetUrl += "?" + request.uri().getQuery();
            }
            
            org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
            
            String body = request.body(String.class);
            org.springframework.http.HttpEntity<String> entity = new org.springframework.http.HttpEntity<>(body, headers);
            
            return ServerResponse.ok().body(restTemplate.exchange(
                    targetUrl,
                    request.method(),
                    entity,
                    String.class
            ).getBody());
        });
    }

    @Bean
    public RouterFunction<ServerResponse> likeServiceProxy(RestTemplate restTemplate) {
        return route(path("/api/likes/**"), request -> {
            String targetUrl = "http://localhost:9000" + request.uri().getPath();
            if (request.uri().getQuery() != null) {
                targetUrl += "?" + request.uri().getQuery();
            }
            
            org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
            
            String body = request.body(String.class);
            org.springframework.http.HttpEntity<String> entity = new org.springframework.http.HttpEntity<>(body, headers);
            
            return ServerResponse.ok().body(restTemplate.exchange(
                    targetUrl,
                    request.method(),
                    entity,
                    String.class
            ).getBody());
        });
    }

    @Bean
    public RouterFunction<ServerResponse> tagServiceProxy(RestTemplate restTemplate) {
        return route(path("/api/tags/**"), request -> {
            String targetUrl = "http://localhost:8083" + request.uri().getPath();
            if (request.uri().getQuery() != null) {
                targetUrl += "?" + request.uri().getQuery();
            }
            
            org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
            
            String body = request.body(String.class);
            org.springframework.http.HttpEntity<String> entity = new org.springframework.http.HttpEntity<>(body, headers);
            
            return ServerResponse.ok().body(restTemplate.exchange(
                    targetUrl,
                    request.method(),
                    entity,
                    String.class
            ).getBody());
        });
    }
}
