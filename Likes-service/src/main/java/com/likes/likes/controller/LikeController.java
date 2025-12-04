package com.likes.likes.controller;

import com.likes.likes.dto.ExperienceResponse;
import com.likes.likes.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/likes")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/experience/{experienceId}/user/{userId}")
    public ResponseEntity<String> likeExperience(@PathVariable Long experienceId, @PathVariable Long userId) {
        likeService.likeExperience(experienceId, userId);
        return ResponseEntity.ok("Experience liked successfully");
    }

    @DeleteMapping("/experience/{experienceId}/user/{userId}")
    public ResponseEntity<String> unlikeExperience(@PathVariable Long experienceId, @PathVariable Long userId) {
        likeService.unlikeExperience(experienceId, userId);
        return ResponseEntity.ok("Experience unliked successfully");
    }

    @GetMapping("/experience/{experienceId}/user/{userId}/status")
    public ResponseEntity<Boolean> isLiked(@PathVariable Long experienceId, @PathVariable Long userId) {
        return ResponseEntity.ok(likeService.isLiked(experienceId, userId));
    }

    @GetMapping("/experience/{experienceId}/count")
    public ResponseEntity<Integer> getLikesCount(@PathVariable Long experienceId) {
        return ResponseEntity.ok(likeService.getLikesCount(experienceId));
    }

    @GetMapping("/experience/{id}")
    public ExperienceResponse getExperience(@PathVariable Long id) {
        return likeService.getExperienceById(id);
    }
}
