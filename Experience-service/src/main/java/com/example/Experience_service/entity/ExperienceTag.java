package com.example.Experience_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "experience_tag")
public class ExperienceTag {

    @EmbeddedId
    private ExperienceTagId id;
}
