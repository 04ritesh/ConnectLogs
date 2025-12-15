package com.likes.likes.service.impl;

import com.likes.likes.dto.ExperienceResponse;
import com.likes.likes.entity.Likes;
import com.likes.likes.repository.ExperienceLikeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LikeServiceImplTest {

    @Mock
    private RestClient restClient;

    @Mock
    private ExperienceLikeRepository likeRepository;

    @Mock
    private RestClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private RestClient.ResponseSpec responseSpec;

    @InjectMocks
    private LikeServiceImpl likeService;

    private Likes testLike;

    @BeforeEach
    void setUp() {
        testLike = new Likes(1L, 1L);
        testLike.setId(1L);
    }

    @Test
    void likeExperience_ShouldSaveLike_WhenNotAlreadyLiked() {
        when(likeRepository.existsByExperienceIdAndUserId(1L, 1L)).thenReturn(false);
        when(likeRepository.save(any(Likes.class))).thenReturn(testLike);
        when(likeRepository.countByExperienceId(1L)).thenReturn(1);

        likeService.likeExperience(1L, 1L);

        verify(likeRepository).save(any(Likes.class));
        verify(likeRepository).countByExperienceId(1L);
    }

    @Test
    void likeExperience_ShouldNotSaveLike_WhenAlreadyLiked() {
        when(likeRepository.existsByExperienceIdAndUserId(1L, 1L)).thenReturn(true);

        likeService.likeExperience(1L, 1L);

        verify(likeRepository, never()).save(any(Likes.class));
    }

    @Test
    void unlikeExperience_ShouldDeleteLike() {
        likeService.unlikeExperience(1L, 1L);

        verify(likeRepository).deleteByExperienceIdAndUserId(1L, 1L);
    }

    @Test
    void isLiked_ShouldReturnTrue_WhenLikeExists() {
        when(likeRepository.existsByExperienceIdAndUserId(1L, 1L)).thenReturn(true);

        boolean result = likeService.isLiked(1L, 1L);

        assertTrue(result);
    }

    @Test
    void isLiked_ShouldReturnFalse_WhenLikeDoesNotExist() {
        when(likeRepository.existsByExperienceIdAndUserId(1L, 1L)).thenReturn(false);

        boolean result = likeService.isLiked(1L, 1L);

        assertFalse(result);
    }

    @Test
    void getLikesCount_ShouldReturnCount() {
        when(likeRepository.countByExperienceId(1L)).thenReturn(5);

        int result = likeService.getLikesCount(1L);

        assertEquals(5, result);
    }

    @Test
    void getExperienceById_ShouldReturnExperience() {
        ExperienceResponse expectedResponse = new ExperienceResponse();
        when(restClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/experience/{id}", 1L)).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.body(ExperienceResponse.class)).thenReturn(expectedResponse);

        ExperienceResponse result = likeService.getExperienceById(1L);

        assertNotNull(result);
        assertEquals(expectedResponse, result);
    }
}