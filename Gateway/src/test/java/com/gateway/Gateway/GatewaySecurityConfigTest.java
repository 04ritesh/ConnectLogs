package com.gateway.Gateway;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
    "spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:8080/realms/realm1"
})
class GatewaySecurityConfigTest {

    @Test
    void securityContextLoads() {
        // Test that security configuration loads successfully
    }
}