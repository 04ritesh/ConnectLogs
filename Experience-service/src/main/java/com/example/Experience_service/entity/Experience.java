package com.example.Experience_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Data
@Getter
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String title;
    private String slug;
    private String summary;
    private String content;

    private Integer likesCount = 0;
    private Integer viewsCount = 0;

    private String status = "ACTIVE";

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
}
