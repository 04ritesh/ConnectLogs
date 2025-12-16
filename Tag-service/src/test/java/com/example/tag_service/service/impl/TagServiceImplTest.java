package com.example.tag_service.service.impl;

import com.example.tag_service.entity.Tag;
import com.example.tag_service.repository.TagRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TagServiceImplTest {

    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private TagServiceImpl tagService;

    private Tag testTag;

    @BeforeEach
    void setUp() {
        testTag = new Tag();
        testTag.setId(1L);
        testTag.setName("java");
    }

    @Test
    void createTags_ShouldReturnTagIds_WhenTagsExist() {
        List<String> tagNames = Arrays.asList("java", "spring");
        Tag springTag = new Tag();
        springTag.setId(2L);
        springTag.setName("spring");

        when(tagRepository.findByName("java")).thenReturn(Optional.of(testTag));
        when(tagRepository.findByName("spring")).thenReturn(Optional.of(springTag));

        List<Long> result = tagService.createTags(tagNames);

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0));
        assertEquals(2L, result.get(1));
        verify(tagRepository, never()).save(any(Tag.class));
    }

    @Test
    void createTags_ShouldCreateAndReturnTagIds_WhenTagsDoNotExist() {
        List<String> tagNames = Arrays.asList("newTag");
        Tag newTag = new Tag();
        newTag.setId(3L);
        newTag.setName("newTag");

        when(tagRepository.findByName("newTag")).thenReturn(Optional.empty());
        when(tagRepository.save(any(Tag.class))).thenReturn(newTag);

        List<Long> result = tagService.createTags(tagNames);

        assertEquals(1, result.size());
        assertEquals(3L, result.get(0));
        verify(tagRepository).save(any(Tag.class));
    }

    @Test
    void getAllTags_ShouldReturnAllTags() {
        List<Tag> tags = Arrays.asList(testTag);
        when(tagRepository.findAll()).thenReturn(tags);

        List<Tag> result = tagService.getAllTags();

        assertEquals(1, result.size());
        assertEquals(testTag.getName(), result.get(0).getName());
    }

    @Test
    void getTagById_ShouldReturnTag() {
        when(tagRepository.findById(1L)).thenReturn(Optional.of(testTag));

        Tag result = tagService.getTagById(1L);

        assertNotNull(result);
        assertEquals(testTag.getId(), result.getId());
        assertEquals(testTag.getName(), result.getName());
    }

    @Test
    void getTagById_ShouldThrowException_WhenTagNotFound() {
        when(tagRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> tagService.getTagById(1L));

        assertTrue(exception.getMessage().contains("Tag not found"));
    }
}