package com.resume.shortlistingsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String phone;

    private String resumeUrl;

    private Double atsScore; // Calculated by your scoring logic

    @Lob
    private String parsedSkills; // optional: comma-separated or JSON

}