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
        System.out.println("Liking experience: " + expId + " by user: " + userId);
        
        boolean alreadyLiked = likeRepository.existsByExperienceIdAndUserId(expId, userId);
        System.out.println("Already liked: " + alreadyLiked);
        
        if (!alreadyLiked) {
            Likes like = new Likes(expId, userId);
            Likes saved = likeRepository.save(like);
            System.out.println("Saved like with ID: " + saved.getId());
            
            int count = likeRepository.countByExperienceId(expId);
            System.out.println("Current likes count: " + count);
        }
    }

    @Override
    @Transactional
    public void unlikeExperience(Long expId, Long userId) {
        likeRepository.deleteByExperienceIdAndUserId(expId, userId);
        
        // Update likes count in Experience service (disabled for testing)
        // int newCount = likeRepository.countByExperienceId(expId);
        // restClient.put()
        //         .uri("/experience/{id}/likes?count={count}", expId, newCount)
        //         .retrieve();
    }

    @Override
    public boolean isLiked(Long expId, Long userId) {
        return likeRepository.existsByExperienceIdAndUserId(expId, userId);
    }

    @Override
    public int getLikesCount(Long expId) {
        int count = likeRepository.countByExperienceId(expId);
        System.out.println("Getting likes count for experience " + expId + ": " + count);
        return count;
    }

    @Override
    public ExperienceResponse getExperienceById(Long id) {
        return restClient.get()
                .uri("/experience/{id}", id)
                .retrieve()
                .body(ExperienceResponse.class);
    }
}
