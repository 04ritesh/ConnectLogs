package com.example.Experience_service.service;

import com.example.Experience_service.dto.ExperienceRequest;
import com.example.Experience_service.entity.Experience;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExperienceService {

    Experience createExperience(ExperienceRequest request, Long userId);
    Experience getExperienceById(Long id);
    void updateLikesCount(Long expId, int count);
}
