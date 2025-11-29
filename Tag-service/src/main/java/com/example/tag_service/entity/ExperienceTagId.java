package com.example.tag_service.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class ExperienceTagId implements Serializable {
    private Long experienceId;
    private Long tagId;
}
