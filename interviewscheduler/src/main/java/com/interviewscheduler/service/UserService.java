package com.interviewscheduler.service;

import com.interviewscheduler.dto.LoginRequestDto;
import com.interviewscheduler.dto.UserDto;
import com.interviewscheduler.entity.User;
import com.interviewscheduler.entity.Candidate;
import com.interviewscheduler.entity.Interviewer;
import com.interviewscheduler.enums.UserRole;
import com.interviewscheduler.repository.UserRepository;
import com.interviewscheduler.repository.CandidateRepository;
import com.interviewscheduler.repository.InterviewerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CandidateRepository candidateRepository;
    private final InterviewerRepository interviewerRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       CandidateRepository candidateRepository,
                       InterviewerRepository interviewerRepository) {
        this.userRepository = userRepository;
        this.candidateRepository = candidateRepository;
        this.interviewerRepository = interviewerRepository;
    }

    // 1. Register
    public void registerUser(UserDto userDto) {
        // ✅ Convert String role → Enum safely
        UserRole role;
        try {
            role = UserRole.valueOf(userDto.getRole().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid role provided: " + userDto.getRole());
        }

        // ✅ Save user in users table (store as String)
        User user = new User(
                userDto.getName(),
                userDto.getEmail(),
                userDto.getPassword(),
                role.name()   // convert Enum → String
        );
        userRepository.save(user);

        // ✅ Save in candidate/interviewer table depending on role
        if (role == UserRole.CANDIDATE) {
            Candidate candidate = new Candidate();
            candidate.setName(userDto.getName());
            candidate.setEmail(userDto.getEmail());
            candidateRepository.save(candidate);
        } else if (role == UserRole.INTERVIEWER) {
            Interviewer interviewer = new Interviewer();
            interviewer.setName(userDto.getName());
            interviewer.setEmail(userDto.getEmail());
            interviewerRepository.save(interviewer);
        }
    }

    // 2. Login
    public String loginUser(LoginRequestDto loginRequest) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByEmail(loginRequest.getEmail()));

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPassword().equals(loginRequest.getPassword())) {
                return "Login successful for user: " + user.getName();
            } else {
                return "Invalid password!";
            }
        } else {
            return "User not found with email: " + loginRequest.getEmail();
        }
    }
}
