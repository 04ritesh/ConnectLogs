package com.example.Tag_service.controller;

import com.example.Tag_service.entity.Tag;
import com.example.Tag_service.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    // -------------------------------
    // CREATE TAG
    // -------------------------------
    @PostMapping
    public ResponseEntity<?> createTag(@RequestBody Tag tagRequest) {
        try {
            Tag newTag = tagService.createTag(tagRequest);
            return new ResponseEntity<>(newTag, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // -------------------------------
    // GET ALL TAGS
    // -------------------------------
    @GetMapping
    public ResponseEntity<List<Tag>> getAllTags() {
        return ResponseEntity.ok(tagService.getAllTags());
    }

    // -------------------------------
    // GET TAG BY ID
    // -------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<?> getTagById(@PathVariable Long id) {
        return tagService.getTagById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Tag not found with ID " + id));
    }

    // -------------------------------
    // GET TAG BY NAME
    // -------------------------------
    @GetMapping("/search")
    public ResponseEntity<?> getTagByName(@RequestParam String name) {
        return tagService.getTagByName(name)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Tag not found with name '" + name + "'"));
    }

    // -------------------------------
    // UPDATE TAG
    // -------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTag(@PathVariable Long id, @RequestBody Tag tagDetails) {
        try {
            Tag updated = tagService.updateTag(id, tagDetails);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // -------------------------------
    // DELETE TAG
    // -------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable Long id) {
        try {
            tagService.deleteTag(id);
            return ResponseEntity.ok("Tag deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
