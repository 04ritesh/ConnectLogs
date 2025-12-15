package com.likes.likes.controller;

import com.likes.likes.dto.ExperienceResponse;
import com.likes.likes.service.LikeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LikeController.class)
class LikeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LikeService likeService;

    @Test
    void likeExperience_ShouldReturnSuccessMessage() throws Exception {
        mockMvc.perform(post("/api/likes/experience/1/user/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Experience liked successfully"));
    }

    @Test
    void unlikeExperience_ShouldReturnSuccessMessage() throws Exception {
        mockMvc.perform(delete("/api/likes/experience/1/user/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Experience unliked successfully"));
    }

    @Test
    void isLiked_ShouldReturnLikeStatus() throws Exception {
        when(likeService.isLiked(1L, 1L)).thenReturn(true);

        mockMvc.perform(get("/api/likes/experience/1/user/1/status"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void getLikesCount_ShouldReturnCount() throws Exception {
        when(likeService.getLikesCount(1L)).thenReturn(5);

        mockMvc.perform(get("/api/likes/experience/1/count"))
                .andExpect(status().isOk())
                .andExpect(content().string("5"));
    }


}