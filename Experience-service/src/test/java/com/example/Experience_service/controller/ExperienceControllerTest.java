package com.example.Experience_service.controller;

import com.example.Experience_service.entity.Experience;
import com.example.Experience_service.service.ExperienceService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ExperienceControllerTest {

    @MockBean
    private ExperienceService experienceService;

    @Test
    void contextLoads() {
        assertNotNull(experienceService);
    }

    @Test
    void getExperienceById_ShouldReturnExperience() {
        Experience experience = new Experience();
        experience.setId(1L);
        experience.setTitle("Test Experience");

        when(experienceService.getExperienceById(1L)).thenReturn(experience);

        Experience result = experienceService.getExperienceById(1L);
        
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Experience", result.getTitle());
    }
}