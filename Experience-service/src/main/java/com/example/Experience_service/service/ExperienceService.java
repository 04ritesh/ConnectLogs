package com.example.Experience_service.service;

import com.example.Experience_service.dto.ExperienceRequest;
import com.example.Experience_service.entity.Experience;

public interface ExperienceService {

    Experience createExperience(ExperienceRequest request, Long userId);

}
