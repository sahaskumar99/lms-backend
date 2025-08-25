package com.jwt.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.jwt.example.model.User;
import com.jwt.example.repository.UserRepository;
import com.jwt.example.security.JwtService;

import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtService jwt;

    public AuthController(UserRepository repo, PasswordEncoder encoder,
                          AuthenticationManager authManager, JwtService jwt) {
        this.repo = repo;
        this.encoder = encoder;
        this.authManager = authManager;
        this.jwt = jwt;
    }

    // ✅ Register new user
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (repo.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        // hash the password
        user.setPasswordHash(encoder.encode(user.getPasswordHash()));

        // default role = STUDENT
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(Set.of("STUDENT"));
        }

        repo.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    // ✅ Login user and return JWT
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User req) {
        // authenticate using AuthenticationManager
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPasswordHash())
        );

        // load user from DB
        var user = repo.findByEmail(req.getEmail()).orElseThrow();

        // build Spring Security UserDetails object
        var springUser = org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPasswordHash())
                .authorities(user.getRoles().toArray(new String[0]))
                .build();

        // generate JWT using JwtService
        String token = jwt.generateToken(springUser);

        return ResponseEntity.ok(token);
    }
}