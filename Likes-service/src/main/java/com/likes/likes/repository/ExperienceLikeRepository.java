package com.likes.likes.repository;

import com.likes.likes.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceLikeRepository extends JpaRepository<Likes, Long> {

    boolean existsByExperienceIdAndUserId(Long experienceId, Long userId);

    void deleteByExperienceIdAndUserId(Long experienceId, Long userId);

    int countByExperienceId(Long experienceId);

}
