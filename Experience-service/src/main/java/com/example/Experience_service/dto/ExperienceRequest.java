package com.example.Experience_service.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ExperienceRequest {
    private String title;
    private String content;
    private List<String> tags;

    // getters & setters
}
