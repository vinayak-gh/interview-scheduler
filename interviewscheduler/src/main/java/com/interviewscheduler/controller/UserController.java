package com.interviewscheduler.controller;

import com.interviewscheduler.dto.LoginRequestDto;
import com.interviewscheduler.dto.UserDto;
import com.interviewscheduler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ✅ Register API
    @PostMapping("/register")
    public String registerUser(@RequestBody UserDto userDto) {
        userService.registerUser(userDto);
        return "User registered successfully!";
    }

    // ✅ Login API
    @PostMapping("/login")
    public String loginUser(@RequestBody LoginRequestDto loginRequest) {
        return userService.loginUser(loginRequest);
    }
}
