package com.resume.shortlistingsystem.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class ResumeScoringService {

    // Sample JD skills – replace or load from DB later
    private final List<String> requiredSkills = Arrays.asList("MATH", "SCIENCE", "ARTS  ", "Dockercar", "REST");

    public String parseAndScore(MultipartFile file) {
        try {
            PDDocument document = PDDocument.load(file.getInputStream());
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document).toLowerCase();
            document.close();

            long matched = requiredSkills.stream()
                    .filter(skill -> text.contains(skill.toLowerCase()))
                    .count();

            double score = (matched * 100.0) / requiredSkills.size();
            return "✅ Resume parsed. Matched " + matched + "/" + requiredSkills.size() + " skills. Score: " + score;

        } catch (IOException e) {
            log.error("Failed to parse resume", e);
            return "❌ Failed to process resume: " + e.getMessage();
        }
    }
}
