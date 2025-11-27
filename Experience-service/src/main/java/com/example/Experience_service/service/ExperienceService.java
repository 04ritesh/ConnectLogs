package com.example.Experience_service.service;

import com.example.Experience_service.entity.Experience;

import java.util.List;
import java.util.Optional;

public interface ExperienceService {

    Experience saveExperience(Experience experience);

    Optional<Experience> getExperienceById(Long id);

    Optional<Experience> getExperienceBySlug(String slug);

    List<Experience> getAllExperiences();

    void deleteExperience(Long id);

    void incrementViews(Long id);

    void incrementLikes(Long id);
}
