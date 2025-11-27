package com.example.Experience_service.controller;

import com.example.Experience_service.entity.Experience;
import com.example.Experience_service.service.ExperienceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experiences")
public class ExperienceController {

    private final ExperienceService experienceService;

    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    // Create a new experience
    @PostMapping
    public ResponseEntity<Experience> createExperience(@RequestBody Experience experience) {
        Experience savedExperience = experienceService.saveExperience(experience);
        return ResponseEntity.ok(savedExperience);
    }

    // Get all experiences
    @GetMapping
    public ResponseEntity<List<Experience>> getAllExperiences() {
        List<Experience> experiences = experienceService.getAllExperiences();
        return ResponseEntity.ok(experiences);
    }

    // Get experience by ID
    @GetMapping("/{id}")
    public ResponseEntity<Experience> getExperienceById(@PathVariable Long id) {
        return experienceService.getExperienceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get experience by slug
    @GetMapping("/slug/{slug}")
    public ResponseEntity<Experience> getExperienceBySlug(@PathVariable String slug) {
        return experienceService.getExperienceBySlug(slug)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update an experience
    @PutMapping("/{id}")
    public ResponseEntity<Experience> updateExperience(
            @PathVariable Long id,
            @RequestBody Experience experienceDetails) {

        return experienceService.getExperienceById(id)
                .map(exp -> {
                    exp.setTitle(experienceDetails.getTitle());
                    exp.setSummary(experienceDetails.getSummary());
                    exp.setContent(experienceDetails.getContent());
                    exp.setSlug(experienceDetails.getSlug());
                    exp.setStatus(experienceDetails.getStatus());
                    Experience updatedExp = experienceService.saveExperience(exp);
                    return ResponseEntity.ok(updatedExp);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete an experience
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExperience(@PathVariable Long id) {
        experienceService.deleteExperience(id);
        return ResponseEntity.noContent().build();
    }

    // Increment views count
    @PostMapping("/{id}/view")
    public ResponseEntity<Void> incrementViews(@PathVariable Long id) {
        experienceService.incrementViews(id);
        return ResponseEntity.ok().build();
    }

    // Increment likes count
    @PostMapping("/{id}/like")
    public ResponseEntity<Void> incrementLikes(@PathVariable Long id) {
        experienceService.incrementLikes(id);
        return ResponseEntity.ok().build();
    }
}
