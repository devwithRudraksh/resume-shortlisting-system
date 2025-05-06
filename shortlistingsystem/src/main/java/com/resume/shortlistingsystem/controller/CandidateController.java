package com.resume.shortlistingsystem.controller;

import com.resume.shortlistingsystem.dto.CandidateDTO;
import com.resume.shortlistingsystem.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    // POST /api/candidates/add
    @PostMapping("/add")
    public ResponseEntity<CandidateDTO> addCandidate(@RequestBody CandidateDTO dto) {
        CandidateDTO saved = candidateService.saveCandidate(dto);
        return ResponseEntity.ok(saved);
    }

    // GET /api/candidates/all
    @GetMapping("/all")
    public ResponseEntity<List<CandidateDTO>> getAllCandidates() {
        return ResponseEntity.ok(candidateService.getAllCandidates());
    }

    // GET /api/candidates/search?score=80
    @GetMapping("/search")
    public ResponseEntity<List<CandidateDTO>> searchByScore(@RequestParam double score) {
        return ResponseEntity.ok(candidateService.getCandidatesByMinScore(score));
    }
}
