package com.example.tag_service.controller;

import com.example.tag_service.entity.Tag;
import com.example.tag_service.service.TagService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TagController.class)
class TagControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TagService tagService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createTags_ShouldReturnTagIds() throws Exception {
        List<String> tagNames = Arrays.asList("java", "spring");
        List<Long> tagIds = Arrays.asList(1L, 2L);

        when(tagService.createTags(any(List.class))).thenReturn(tagIds);

        mockMvc.perform(post("/api/tags")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tagNames)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0]").value(1L))
                .andExpect(jsonPath("$[1]").value(2L));
    }

    @Test
    void getAllTags_ShouldReturnTagList() throws Exception {
        Tag tag1 = new Tag();
        tag1.setId(1L);
        tag1.setName("java");
        
        Tag tag2 = new Tag();
        tag2.setId(2L);
        tag2.setName("spring");

        List<Tag> tags = Arrays.asList(tag1, tag2);
        when(tagService.getAllTags()).thenReturn(tags);

        mockMvc.perform(get("/api/tags"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("java"))
                .andExpect(jsonPath("$[1].name").value("spring"));
    }

    @Test
    void getTagById_ShouldReturnTag() throws Exception {
        Tag tag = new Tag();
        tag.setId(1L);
        tag.setName("java");

        when(tagService.getTagById(1L)).thenReturn(tag);

        mockMvc.perform(get("/api/tags/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("java"));
    }
}