package com.example.Experience_service.controller;

import com.example.Experience_service.dto.ExperienceRequest;
import com.example.Experience_service.entity.Experience;

import com.example.Experience_service.service.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/experiences")
@RequiredArgsConstructor
public class ExperienceController {

    private final ExperienceService experienceService;

    @PostMapping
    public Experience create(@RequestBody ExperienceRequest request) {
        Long userId = 1L; // you will replace with JWT userId
        return experienceService.createExperience(request, userId);
    }
}
