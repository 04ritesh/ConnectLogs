package com.example.Experience_service.repository;

import com.example.Experience_service.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {

    Optional<Experience> findBySlug(String slug);

}
