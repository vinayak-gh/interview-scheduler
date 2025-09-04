package com.interviewscheduler.controller;

import com.interviewscheduler.entity.Candidate;
import com.interviewscheduler.service.CandidateService;
import com.interviewscheduler.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    private final CandidateService candidateService;
    private final FileUploadService fileUploadService;

    @Autowired
    public CandidateController(CandidateService candidateService, FileUploadService fileUploadService) {
        this.candidateService = candidateService;
        this.fileUploadService = fileUploadService;
    }

    // ✅ Add new candidate (without resume)
    @PostMapping
    public Candidate addCandidate(@RequestBody Candidate candidate) {
        return candidateService.saveCandidate(candidate);
    }

    // ✅ List all candidates
    @GetMapping
    public List<Candidate> getAllCandidates() {
        return candidateService.getAllCandidates();
    }

    // ✅ Upload Resume for a Candidate
    @PostMapping("/{id}/upload-resume")
    public String uploadResume(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            Candidate candidate = candidateService.getCandidateById(id);

            String resumePath = fileUploadService.saveResume(file);
            candidate.setResumePath(resumePath);

            candidateService.saveCandidate(candidate);

            return "✅ Resume uploaded successfully: " + resumePath;
        } catch (IOException e) {
            return "❌ Failed to upload resume: " + e.getMessage();
        } catch (RuntimeException e) {
            return "❌ Error: " + e.getMessage();
        }
    }
}
