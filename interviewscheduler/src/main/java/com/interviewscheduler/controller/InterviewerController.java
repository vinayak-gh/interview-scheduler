package com.interviewscheduler.controller;

import com.interviewscheduler.entity.Interviewer;
import com.interviewscheduler.service.InterviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interviewers")
public class InterviewerController {

    private final InterviewerService interviewerService;

    @Autowired
    public InterviewerController(InterviewerService interviewerService) {
        this.interviewerService = interviewerService;
    }

    // POST /api/interviewers - Add new interviewer
    @PostMapping
    public Interviewer addInterviewer(@RequestBody Interviewer interviewer) {
        return interviewerService.saveInterviewer(interviewer);
    }

    // GET /api/interviewers - List all interviewers
    @GetMapping
    public List<Interviewer> getAllInterviewers() {
        return interviewerService.getAllInterviewers();
    }
}
