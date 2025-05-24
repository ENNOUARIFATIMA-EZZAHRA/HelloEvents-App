package com.event.management.app.eventManagement.controller;

import com.event.management.app.eventManagement.dto.LoginRequest;
import com.event.management.app.eventManagement.entity.User;
import com.event.management.app.eventManagement.repository.UserRepository;
import com.event.management.app.eventManagement.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RegistrationLoginController {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JwtUtils jwtUtils;

  @PostMapping("/auth/register")
  public ResponseEntity<?> registerUser(@RequestBody User user) {
    if (userRepository.findByUsername(user.getUsername()) != null) {
      return ResponseEntity.badRequest().body("Username already exists");
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return ResponseEntity.ok(userRepository.save(user));
  }

  @PostMapping("/auth/login")
  public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
    try {
      authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
      );
      String token = jwtUtils.generateToken(loginRequest.getUsername());
      return ResponseEntity.ok(Collections.singletonMap("token", token));
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }
  }
}
