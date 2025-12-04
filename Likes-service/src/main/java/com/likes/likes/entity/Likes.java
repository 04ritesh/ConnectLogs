// File: src/main/java/com/likes/likes/entity/Likes.java
package com.likes.likes.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "experience_likes", uniqueConstraints = @UniqueConstraint(columnNames = {"experience_id", "user_id"}))
@Data
@NoArgsConstructor
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "experience_id", nullable = false)
    private Long experienceId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // explicit constructor used in service: new Likes(expId, userId)
    public Likes(Long experienceId, Long userId) {
        this.experienceId = experienceId;
        this.userId = userId;
    }
}
