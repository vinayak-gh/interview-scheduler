package com.interviewscheduler.controller;

import com.interviewscheduler.dto.InterviewRequestDto;
import com.interviewscheduler.entity.Interview;
import com.interviewscheduler.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    private final InterviewService interviewService;

    @Autowired
    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    // ✅ Schedule a new interview
    @PostMapping("/schedule")
    public ResponseEntity<?> scheduleInterview(@RequestBody InterviewRequestDto dto) {
        try {
            interviewService.scheduleInterview(dto);
            return ResponseEntity.ok().body("{\"message\": \"✅ Interview scheduled successfully!\"}");
        } catch (Exception e) {
            e.printStackTrace(); // log error in backend console
            return ResponseEntity
                    .badRequest()
                    .body("{\"error\": \"❌ Failed to schedule interview! Reason: " + e.getMessage() + "\"}");
        }
    }

    // ✅ Get all interviews
    @GetMapping
    public ResponseEntity<List<Interview>> getAllInterviews() {
        return ResponseEntity.ok(interviewService.getAllInterviews());
    }
}
