package com.example.Experience_service.service.impl;

import com.example.Experience_service.client.LikesClient;
import com.example.Experience_service.client.TagClient;
import com.example.Experience_service.entity.Experience;
import com.example.Experience_service.repository.ExperienceRepository;
import com.example.Experience_service.repository.ExperienceTagRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExperienceServiceImplTest {

    @Mock
    private ExperienceRepository experienceRepository;

    @Mock
    private ExperienceTagRepository experienceTagRepository;

    @Mock
    private TagClient tagClient;

    @Mock
    private LikesClient likesClient;

    @InjectMocks
    private ExperienceServiceImpl experienceService;

    private Experience testExperience;

    @BeforeEach
    void setUp() {
        testExperience = new Experience();
        testExperience.setId(1L);
        testExperience.setUserId(1L);
        testExperience.setTitle("Test Experience");
        testExperience.setContent("Test Content");
    }

    @Test
    void contextLoads() {
        assertNotNull(experienceService);
    }

    @Test
    void getExperienceById_ShouldReturnExperience() {
        when(experienceRepository.findById(1L)).thenReturn(Optional.of(testExperience));
        when(likesClient.getLikesCount(1L)).thenReturn(5);

        Experience result = experienceService.getExperienceById(1L);

        assertNotNull(result);
        assertEquals(testExperience.getId(), result.getId());
        assertEquals(5, result.getLikesCount());
    }

    @Test
    void getExperienceById_ShouldThrowException_WhenNotFound() {
        when(experienceRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> experienceService.getExperienceById(1L));

        assertTrue(exception.getMessage().contains("Experience not found"));
    }

    @Test
    void updateLikesCount_ShouldCallRepository() {
        experienceService.updateLikesCount(1L, 10);
        verify(experienceRepository).updateLikesCount(1L, 10);
    }
}