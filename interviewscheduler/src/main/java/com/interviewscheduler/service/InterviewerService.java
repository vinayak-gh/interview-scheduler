package com.interviewscheduler.service;

import com.interviewscheduler.entity.Interviewer;
import com.interviewscheduler.repository.InterviewerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewerService {

    private final InterviewerRepository interviewerRepository;

    @Autowired
    public InterviewerService(InterviewerRepository interviewerRepository) {
        this.interviewerRepository = interviewerRepository;
    }

    public Interviewer saveInterviewer(Interviewer interviewer) {
        return interviewerRepository.save(interviewer);
    }

    public List<Interviewer> getAllInterviewers() {
        return interviewerRepository.findAll();
    }

    public Interviewer getInterviewerById(Long id) {
        return interviewerRepository.findById(id).orElse(null);
    }
}
