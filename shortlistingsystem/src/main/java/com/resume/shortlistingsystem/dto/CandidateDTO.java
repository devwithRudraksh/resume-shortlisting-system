package com.resume.shortlistingsystem.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateDTO {
    private String name;
    private String email;
    private String phone;
    private String resumeUrl;
    private Double atsScore;
}

