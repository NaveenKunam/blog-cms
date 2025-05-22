package com.example.blogcms.controller;

import com.example.blogcms.dto.LoginRequest;
import com.example.blogcms.dto.LoginResponse;
import com.example.blogcms.model.User;
import com.example.blogcms.repository.UserRepository;
import com.example.blogcms.security.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        Optional<User> userOpt = userRepository.findByUsername(request.getUsername());
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(request.getPassword())) {
            String token = jwtUtil.generateToken(userOpt.get().getUsername(), userOpt.get().getRole());
            return new LoginResponse(token);
        }
        throw new RuntimeException("Invalid credentials");
    }

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        userRepository.save(user);
        return "User registered successfully";
    }
}
