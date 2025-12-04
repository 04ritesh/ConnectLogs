package com.example.Experience_service.service.impl;

import com.example.Experience_service.client.TagClient;
import com.example.Experience_service.client.LikesClient;
import com.example.Experience_service.dto.ExperienceRequest;
import com.example.Experience_service.entity.Experience;
import com.example.Experience_service.entity.ExperienceTag;
import com.example.Experience_service.entity.ExperienceTagId;
import com.example.Experience_service.repository.ExperienceRepository;
import com.example.Experience_service.repository.ExperienceTagRepository;
import com.example.Experience_service.service.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final ExperienceTagRepository experienceTagRepository;
    private final TagClient tagClient;
    private final LikesClient likesClient;

    @Override
    public Experience createExperience(ExperienceRequest request, Long userId) {



        Experience exp = new Experience();
        exp.setUserId(userId);
        exp.setTitle(request.getTitle());
        exp.setContent(request.getContent());

        Experience saved = experienceRepository.save(exp);

        if (request.getTags() != null && !request.getTags().isEmpty()) {

            // CALL TAG SERVICE USING REST TEMPLATE
            List<Long> tagIds = tagClient.createAndReturnTagIds(request.getTags());

            // SAVE IN EXPERIENCE_TAG TABLE
            for (Long tagId : tagIds) {
                ExperienceTag et = new ExperienceTag();
                ExperienceTagId id = new ExperienceTagId(saved.getId(), tagId);
                et.setId(id);

                experienceTagRepository.save(et);
            }
        }

        return saved;
    }

    @Override
    public Experience getExperienceById(Long id) {
        Experience experience = experienceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Experience not found with id: " + id));
        
        // Fetch current likes count from Likes service
        try {
            Integer likesCount = likesClient.getLikesCount(id);
            experience.setLikesCount(likesCount != null ? likesCount : 0);
        } catch (Exception e) {
            // If likes service is down, keep existing count
            System.out.println("Could not fetch likes count: " + e.getMessage());
        }
        
        return experience;
    }

    @Override
    @Transactional
    public void updateLikesCount(Long expId, int count) {
        experienceRepository.updateLikesCount(expId, count);
    }



}
