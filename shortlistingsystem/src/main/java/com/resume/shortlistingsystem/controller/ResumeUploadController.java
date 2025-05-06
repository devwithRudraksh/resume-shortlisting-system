package com.resume.shortlistingsystem.controller;

import com.resume.shortlistingsystem.service.ResumeScoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/resume")
@RequiredArgsConstructor
public class ResumeUploadController {

    private final ResumeScoringService scoringService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadResume(@RequestParam MultipartFile file) {
        String result = scoringService.parseAndScore(file);
        return ResponseEntity.ok(result);
    }
}
