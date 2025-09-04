package com.interviewscheduler.service;

import com.interviewscheduler.entity.Candidate;
import com.interviewscheduler.repository.CandidateRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    // Save or update candidate
    public Candidate saveCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    // Get all candidates
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    // Get candidate by ID
    public Candidate getCandidateById(Long id) {
        return candidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found with ID: " + id));
    }

    // ✅ Update resume path
    public Candidate updateResume(Long id, String resumePath) {
        Candidate candidate = getCandidateById(id);
        candidate.setResumePath(resumePath);
        return candidateRepository.save(candidate);
    }
}
