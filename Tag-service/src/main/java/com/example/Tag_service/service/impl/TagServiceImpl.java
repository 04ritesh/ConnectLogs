package com.example.Tag_service.service.impl;

import com.example.Tag_service.entity.Tag;
import com.example.Tag_service.repository.TagRepository;
import com.example.Tag_service.service.TagService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag createTag(Tag tag) {
        // Prevent duplicate tag names
        Optional<Tag> existing = tagRepository.findByName(tag.getName());
        if (existing.isPresent()) {
            throw new RuntimeException("Tag with name '" + tag.getName() + "' already exists");
        }
        return tagRepository.save(tag);
    }

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public Optional<Tag> getTagById(Long id) {
        return tagRepository.findById(id);
    }

    @Override
    public Optional<Tag> getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    public Tag updateTag(Long id, Tag tagDetails) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found with ID " + id));

        // Prevent renaming to an existing name
        if (!tag.getName().equals(tagDetails.getName())) {
            Optional<Tag> existing = tagRepository.findByName(tagDetails.getName());
            if (existing.isPresent()) {
                throw new RuntimeException("Tag with name '" + tagDetails.getName() + "' already exists");
            }
        }

        tag.setName(tagDetails.getName());
        tag.setType(tagDetails.getType());
        tag.setPopularityScore(tagDetails.getPopularityScore());

        return tagRepository.save(tag);
    }

    @Override
    public void deleteTag(Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found with ID " + id));

        tagRepository.delete(tag);
    }
}
