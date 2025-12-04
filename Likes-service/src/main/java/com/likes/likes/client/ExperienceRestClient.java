package com.likes.likes.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ExperienceRestClient {


    public RestClient experienceRestClient() {
        return RestClient.builder()
                .baseUrl("http://localhost:8082") // Experience-Service URL
                .build();
    }
}
