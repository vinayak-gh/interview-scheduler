package com.interviewscheduler.service;

import com.interviewscheduler.dto.InterviewRequestDto;
import com.interviewscheduler.entity.Candidate;
import com.interviewscheduler.entity.Interview;
import com.interviewscheduler.entity.Interviewer;
import com.interviewscheduler.repository.CandidateRepository;
import com.interviewscheduler.repository.InterviewRepository;
import com.interviewscheduler.repository.InterviewerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewService {

    private final InterviewerRepository interviewerRepository;
    private final CandidateRepository candidateRepository;
    private final InterviewRepository interviewRepository;

    @Autowired
    public InterviewService(InterviewerRepository interviewerRepository,
                            CandidateRepository candidateRepository,
                            InterviewRepository interviewRepository) {
        this.interviewerRepository = interviewerRepository;
        this.candidateRepository = candidateRepository;
        this.interviewRepository = interviewRepository;
    }

    public void scheduleInterview(InterviewRequestDto dto) {
        Interview interview = new Interview();

        Interviewer interviewer = interviewerRepository.findById(dto.getInterviewerId())
                .orElseThrow(() -> new RuntimeException("Interviewer not found"));

        Candidate candidate = candidateRepository.findById(dto.getCandidateId())
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        interview.setInterviewer(interviewer);
        interview.setCandidate(candidate);
        interview.setInterviewTime(dto.getInterviewTime());

        interviewRepository.save(interview);
    }

    // ✅ New method
    public List<Interview> getAllInterviews() {
        return interviewRepository.findAll();
    }
}
