package com.example.Experience_service.repository;

import com.example.Experience_service.entity.ExperienceTag;
import com.example.Experience_service.entity.ExperienceTagId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceTagRepository extends JpaRepository<ExperienceTag, ExperienceTagId> {}
