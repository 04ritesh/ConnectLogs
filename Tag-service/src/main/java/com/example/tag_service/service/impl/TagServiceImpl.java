package com.example.tag_service.service.impl;

import com.example.tag_service.entity.Tag;
import com.example.tag_service.repository.TagRepository;
import com.example.tag_service.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public List<Long> createTags(List<String> names) {

        List<Long> ids = new ArrayList<>();

        for (String name : names) {

            Tag tag = tagRepository.findByName(name)
                    .orElseGet(() -> {
                        Tag t = new Tag();
                        t.setName(name);
                        return tagRepository.save(t);
                    });

            ids.add(tag.getId());
        }

        return ids;
    }

    @Override
    public List<Tag> getAllTags() {   // <-- ADD THIS
        return tagRepository.findAll();
    }

    @Override
    public Tag getTagById(Long id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found with id: " + id));
    }

}
