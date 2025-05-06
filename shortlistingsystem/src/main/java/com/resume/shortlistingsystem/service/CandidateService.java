package com.resume.shortlistingsystem.service;

import com.resume.shortlistingsystem.dto.CandidateDTO;
import com.resume.shortlistingsystem.entity.Candidate;
import com.resume.shortlistingsystem.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateRepository candidateRepository;

    // Convert DTO → Entity
    private Candidate toEntity(CandidateDTO dto) {
        if (dto.getName() == null) {
            throw new IllegalArgumentException("Candidate name cannot be null");
        }
        return Candidate.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .resumeUrl(dto.getResumeUrl())
                .atsScore(dto.getAtsScore() != null ? dto.getAtsScore() : 0.0)
                .build();

    }

    // Convert Entity → DTO
    private CandidateDTO toDTO(Candidate entity) {
        return CandidateDTO.builder()
                .name(entity.getName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .resumeUrl(entity.getResumeUrl())
                .atsScore(entity.getAtsScore())
                .build();
    }

    // Save candidate
    public CandidateDTO saveCandidate(CandidateDTO dto) {
        Candidate candidate = toEntity(dto);
        Candidate saved = candidateRepository.save(candidate);
        return toDTO(saved);
    }

    // Get all candidates
    public List<CandidateDTO> getAllCandidates() {
        return candidateRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Filter by score threshold (e.g., ATS score >= 75)
    public List<CandidateDTO> getCandidatesByMinScore(double score) {
        return candidateRepository.findByAtsScoreGreaterThanEqual(score)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
