package com.example.tag_service.controller;

import com.example.tag_service.entity.Tag;
import com.example.tag_service.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping("/tags")
    public List<Long> createTags(@RequestBody List<String> tags) {
        return tagService.createTags(tags);
    }
    @GetMapping("/tags")
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }

    // ✅ Get tag by ID
    @GetMapping("/tags/{id}")
    public Tag getTagById(@PathVariable Long id) {
        return tagService.getTagById(id);
    }
}
