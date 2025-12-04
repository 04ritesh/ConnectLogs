package com.example.Experience_service.repository;

import com.example.Experience_service.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    @Modifying
    @Query("update Experience e set e.likesCount = :count where e.id = :expId")
    void updateLikesCount(@Param("expId") Long expId, @Param("count") int count);
}
