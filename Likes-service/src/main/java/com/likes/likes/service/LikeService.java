package com.likes.likes.service;

import com.likes.likes.dto.ExperienceResponse;

public interface LikeService {
    void likeExperience(Long expId, Long userId);
    void unlikeExperience(Long expId, Long userId);
    boolean isLiked(Long expId, Long userId);
    int getLikesCount(Long expId);
    ExperienceResponse getExperienceById(Long id);
}
