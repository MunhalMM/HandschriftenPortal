package com.example.handschriftenportal.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "manuscripts")
public class Manuscript {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String storageLocation;
    @Column(nullable = false)
    private String storageLocationGndId;
    @Column(nullable = false)
    private String owner;
    @Column(nullable = false)
    private String ownerGndId;
    @Column(nullable = false)
    private int creationYear;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String researcherEmail;
}
