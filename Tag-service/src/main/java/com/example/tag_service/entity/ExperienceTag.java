package com.example.tag_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "experience_tag",
        uniqueConstraints = @UniqueConstraint(columnNames = {"experienceId", "tagId"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExperienceTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long experienceId;

    private Long tagId;
}
