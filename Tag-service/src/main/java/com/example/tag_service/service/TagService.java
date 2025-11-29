package com.example.tag_service.service;

import com.example.tag_service.entity.Tag;

import java.util.List;

public interface TagService {

    List<Long> createTags(List<String> names);
    List<Tag> getAllTags();
    Tag getTagById(Long id);
}
