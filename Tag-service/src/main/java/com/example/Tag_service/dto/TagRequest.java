package com.example.Tag_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TagRequest {

    @NotBlank(message = "Tag name cannot be empty")
    @Size(min = 2, message = "Tag name must have at least 2 characters")
    private String name;

    @NotBlank(message = "Type cannot be empty")
    private String type;

    private Integer popularityScore;

    // Getters & Setters
}
