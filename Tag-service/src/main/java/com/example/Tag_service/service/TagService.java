package com.example.Tag_service.service;

import com.example.Tag_service.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface TagService {

    Tag createTag(Tag tag);

    List<Tag> getAllTags();

    Optional<Tag> getTagById(Long id);

    Optional<Tag> getTagByName(String name);

    Tag updateTag(Long id, Tag tagDetails);

    void deleteTag(Long id);
}
