package com.kicklite.app.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity @Table(name="projects")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Project {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=120)
    private String name;

    @Column(length=300)
    private String description;

    @Column(nullable=false)
    private Instant createdAt = Instant.now();
}
