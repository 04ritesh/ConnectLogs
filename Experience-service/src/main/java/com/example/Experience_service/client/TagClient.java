package com.example.Experience_service.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TagClient {

    private final RestTemplate restTemplate;

    private final String TAG_SERVICE_BASE_URL = "https://triumphant-flexibility-production.up.railway.app/api/tags";

    // POST → Create Tags & Return Tag IDs
    public List<Long> createAndReturnTagIds(List<String> tagNames) {

       // String url = TAG_SERVICE_BASE_URL + "/create-or-return";

        Long[] response = restTemplate.postForObject(TAG_SERVICE_BASE_URL, tagNames, Long[].class);

        return Arrays.asList(response);
    }
}
