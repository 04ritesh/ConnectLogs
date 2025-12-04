package com.example.Experience_service.controller;

import com.example.Experience_service.dto.ExperienceRequest;
import com.example.Experience_service.entity.Experience;

import com.example.Experience_service.service.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/experiences")
@RequiredArgsConstructor
public class ExperienceController {

    private final ExperienceService experienceService;

    @PostMapping
    public Experience create(@RequestBody ExperienceRequest request, @RequestParam Long userId) {
        return experienceService.createExperience(request, userId);
    }


    @GetMapping("/{id}")
    public Experience getExperience(@PathVariable Long id) {
        return experienceService.getExperienceById(id);
    }

    @PutMapping("/{id}/likes")
    public void updateLikes(@PathVariable Long id, @RequestParam int count) {
        experienceService.updateLikesCount(id, count);
    }



}
