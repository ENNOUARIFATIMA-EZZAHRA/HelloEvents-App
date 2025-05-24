package com.event.management.app.eventManagement.controller;

import com.event.management.app.eventManagement.entity.User;
import com.event.management.app.eventManagement.repository.UserRepository;
import com.event.management.app.eventManagement.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

  private final UserRepository userRepo;
  private final PasswordEncoder encoder;
  private final AuthenticationManager authManager;
  private final JwtUtils jwtUtils;

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody User user) {
    if (userRepo.findByUsername(user.getUsername()) != null) {
      return ResponseEntity.badRequest().body("User already exists");
    }
    user.setPassword(encoder.encode(user.getPassword()));
    return ResponseEntity.ok(userRepo.save(user));
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody User user) {
    try {
      authManager.authenticate(
        new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
      String token = jwtUtils.generateToken(user.getUsername());
      return ResponseEntity.ok(Map.of("token", token));
    } catch (Exception e) {
      return ResponseEntity.status(401).body("Invalid credentials");
    }
  }
}
