package com.likes.likes.service.impl;

import com.likes.likes.dto.ExperienceResponse;
import com.likes.likes.entity.Likes;
import com.likes.likes.repository.ExperienceLikeRepository;
import com.likes.likes.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final RestClient restClient;
    private final ExperienceLikeRepository likeRepository;

    @Override
    @Transactional
    public void likeExperience(Long expId, Long userId) {
        // Check if experience exists (commented for testing)
        // try {
        //     restClient.get()
        //             .uri("/experience/{id}", expId)
        //             .retrieve()
        //             .body(ExperienceResponse.class);
        // } catch (Exception e) {
        //     throw new RuntimeException("Experience with id " + expId + " not found");
        // }
        
        if (!likeRepository.existsByExperienceIdAndUserId(expId, userId)) {
            likeRepository.save(new Likes(expId, userId));
            
            // Update likes count in Experience service
            int newCount = likeRepository.countByExperienceId(expId);
            restClient.put()
                    .uri("/experience/{id}/likes?count={count}", expId, newCount)
                    .retrieve();
        }
    }

    @Override
    @Transactional
    public void unlikeExperience(Long expId, Long userId) {
        likeRepository.deleteByExperienceIdAndUserId(expId, userId);
        
        // Update likes count in Experience service
        int newCount = likeRepository.countByExperienceId(expId);
        restClient.put()
                .uri("/experience/{id}/likes?count={count}", expId, newCount)
                .retrieve();
    }

    @Override
    public boolean isLiked(Long expId, Long userId) {
        return likeRepository.existsByExperienceIdAndUserId(expId, userId);
    }

    @Override
    public int getLikesCount(Long expId) {
        return likeRepository.countByExperienceId(expId);
    }

    @Override
    public ExperienceResponse getExperienceById(Long id) {
        return restClient.get()
                .uri("/experience/{id}", id)
                .retrieve()
                .body(ExperienceResponse.class);
    }
}
