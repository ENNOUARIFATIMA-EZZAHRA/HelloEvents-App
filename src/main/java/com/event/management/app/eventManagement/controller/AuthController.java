package com.event.management.app.eventManagement.controller;

import com.event.management.app.eventManagement.dto.LoginRequest;
import com.event.management.app.eventManagement.dto.RegisterRequest;
import com.event.management.app.eventManagement.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
    String token = authService.register(request);
    return ResponseEntity.ok(token);
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody LoginRequest request) {
    String token = authService.login(request);
    return ResponseEntity.ok(token);
  }
}
