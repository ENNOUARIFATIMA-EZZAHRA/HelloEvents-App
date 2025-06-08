package com.event.management.app.eventManagement.controller;

import com.event.management.app.eventManagement.dto.LoginRequest;
import com.event.management.app.eventManagement.dto.RegisterRequest;
import com.event.management.app.eventManagement.security.AuthService;
import com.event.management.app.eventManagement.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
    String token = authService.register(request);
    return ResponseEntity.ok(Map.of("token", token));
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserDetails userDetails = (UserDetails) authentication.getPrincipal();

    List<String> roles = userDetails.getAuthorities()
      .stream()
      .map(GrantedAuthority::getAuthority)
      .toList();

    String token = jwtService.generateToken(userDetails);


    return ResponseEntity.ok(Map.of("token", token));
  }

  @GetMapping("/test")
  public ResponseEntity<?> test() {
    return ResponseEntity.ok("Test endpoint works");
  }
}
