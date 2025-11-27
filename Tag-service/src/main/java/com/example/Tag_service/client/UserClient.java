package com.example.Experience_service.client;

import com.example.Experience_service.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class UserClient {

    private final RestClient restClient;

    @Value("${services.user-service.base-url}")
    private String userServiceBaseUrl;

    // Call: http://USER-SERVICE/users/{id}
    public UserResponse getUserById(Long userId) {

        return restClient.get()
                .uri(userServiceBaseUrl + "/api/users/" + userId)
                .retrieve()
                .body(UserResponse.class);
    }
}
