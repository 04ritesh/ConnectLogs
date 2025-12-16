package com.gateway.Gateway;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
    "server.port=5876",
    "spring.cloud.gateway.mvc.routes[0].id=user-service",
    "spring.cloud.gateway.mvc.routes[0].uri=http://localhost:8081",
    "spring.cloud.gateway.mvc.routes[0].predicates[0]=Path=/api/users/**"
})
class GatewayConfigTest {

    @Test
    void contextLoads() {
        // Test that the application context loads successfully
    }
}