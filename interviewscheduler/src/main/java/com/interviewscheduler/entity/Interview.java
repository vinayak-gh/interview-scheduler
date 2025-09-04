package com.interviewscheduler.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "interviews")
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ Ensure consistent format for both input & output JSON
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime interviewTime;

    @ManyToOne
    @JoinColumn(name = "interviewer_id")
    private Interviewer interviewer;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    public Interview() {}

    public Interview(LocalDateTime interviewTime, Interviewer interviewer, Candidate candidate) {
        this.interviewTime = interviewTime;
        this.interviewer = interviewer;
        this.candidate = candidate;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(LocalDateTime interviewTime) {
        this.interviewTime = interviewTime;
    }

    public Interviewer getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(Interviewer interviewer) {
        this.interviewer = interviewer;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}
