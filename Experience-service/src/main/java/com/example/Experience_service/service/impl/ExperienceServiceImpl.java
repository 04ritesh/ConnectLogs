package com.example.Experience_service.service.impl;

import com.example.Experience_service.client.TagClient;
import com.example.Experience_service.dto.ExperienceRequest;
import com.example.Experience_service.entity.Experience;
import com.example.Experience_service.entity.ExperienceTag;
import com.example.Experience_service.entity.ExperienceTagId;
import com.example.Experience_service.repository.ExperienceRepository;
import com.example.Experience_service.repository.ExperienceTagRepository;
import com.example.Experience_service.service.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final ExperienceTagRepository experienceTagRepository;
    private final TagClient tagClient;

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
}
