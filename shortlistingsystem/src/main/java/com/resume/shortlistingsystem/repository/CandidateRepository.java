package com.resume.shortlistingsystem.repository;

import com.resume.shortlistingsystem.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    List<Candidate> findByAtsScoreGreaterThanEqual(double score);
}
