package com.interviewscheduler.dto;

import java.time.LocalDateTime;

public class InterviewRequestDto {

    private Long interviewerId;
    private Long candidateId;
    private LocalDateTime interviewTime; // ✅ Now same as Entity

    public InterviewRequestDto() {}

    public Long getInterviewerId() {
        return interviewerId;
    }

    public void setInterviewerId(Long interviewerId) {
        this.interviewerId = interviewerId;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public LocalDateTime getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(LocalDateTime interviewTime) {
        this.interviewTime = interviewTime;
    }
}
