package com.example.Experience_service.service.impl;

import com.example.Experience_service.client.UserClient;
import com.example.Experience_service.dto.UserResponse;
import com.example.Experience_service.entity.Experience;
import com.example.Experience_service.repository.ExperienceRepository;
import com.example.Experience_service.service.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final UserClient userClient;

    @Override
    public Experience saveExperience(Experience experience) {

        // 🔍 1. Validate user via User Service
        UserResponse user = userClient.getUserById(experience.getUserId());
        if (user == null) {
            throw new RuntimeException("User not found with id: " + experience.getUserId());
        }

        // 🔍 2. Validate that user is ACTIVE (optional)
        if ("BLOCKED".equalsIgnoreCase(user.getStatus())) {
            throw new RuntimeException("This user is blocked, cannot create experience.");
        }

        // 🔥 3. Save experience
        return experienceRepository.save(experience);
    }

    @Override
    public Optional<Experience> getExperienceById(Long id) {
        return experienceRepository.findById(id);
    }

    @Override
    public Optional<Experience> getExperienceBySlug(String slug) {
        return experienceRepository.findBySlug(slug);
    }

    @Override
    public List<Experience> getAllExperiences() {
        return experienceRepository.findAll();
    }

    @Override
    public void deleteExperience(Long id) {
        experienceRepository.deleteById(id);
    }

    @Override
    public void incrementViews(Long id) {
        experienceRepository.findById(id).ifPresent(exp -> {
            exp.setViewsCount(exp.getViewsCount() + 1);
            experienceRepository.save(exp);
        });
    }

    @Override
    public void incrementLikes(Long id) {
        experienceRepository.findById(id).ifPresent(exp -> {
            exp.setLikesCount(exp.getLikesCount() + 1);
            experienceRepository.save(exp);
        });
    }
}
