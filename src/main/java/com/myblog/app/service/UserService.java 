package com.myblog.app.service;

import com.myblog.app.dto.AuthResponse;
import com.myblog.app.dto.LoginRequest;
import com.myblog.app.dto.SignupRequest;
import com.myblog.app.entity.User;
import com.myblog.app.repository.UserRepository;
import com.myblog.app.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponse signup(SignupRequest request) {
        User user = User.builder()
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .nickname(request.getNickname())
            .build();
        userRepository.save(user);
        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token, user.getNickname());
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("이메일을 찾을 수 없어요"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("비밀번호가 틀렸어요");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token, user.getNickname());
    }
}