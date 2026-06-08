package com.myblog.app.controller;

import com.myblog.app.dto.AuthResponse;
import com.myblog.app.dto.LoginRequest;
import com.myblog.app.dto.SignupRequest;
import com.myblog.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    public AuthResponse signup(@RequestBody SignupRequest request) {
        return userService.signup(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }
}