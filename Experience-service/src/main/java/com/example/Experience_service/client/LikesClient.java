package com.example.Experience_service.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LikesClient {
    
    private final RestTemplate restTemplate = new RestTemplate();
    
    public Integer getLikesCount(Long experienceId) {
        try {
            String url = "https://connectlogs-production-640c.up.railway.app/api/likes/experience/" + experienceId + "/count";
            System.out.println("Calling Likes service: " + url);
            Integer count = restTemplate.getForObject(url, Integer.class);
            System.out.println("Received likes count: " + count);
            return count;
        } catch (Exception e) {
            System.out.println("Error calling Likes service: " + e.getMessage());
            return 0;
        }
    }
}