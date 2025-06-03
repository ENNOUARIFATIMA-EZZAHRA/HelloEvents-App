package com.event.management.app.eventManagement.security;

import com.event.management.app.eventManagement.dto.LoginRequest;
import com.event.management.app.eventManagement.dto.RegisterRequest;
import com.event.management.app.eventManagement.entity.User;
import com.event.management.app.eventManagement.repository.UserRepository;
import com.event.management.app.eventManagement.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final JwtService jwtService;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final CustomUserDetailsService userDetailsService;

  public String register(RegisterRequest request) {
    if(userRepository.findByUsername(request.getUsername()).isPresent()) {
      throw new RuntimeException("Username already exists");
    }

    User user = User.builder()
      .username(request.getUsername())
      .password(passwordEncoder.encode(request.getPassword()))
      .email(request.getEmail())
      .fullName(request.getFullName())
      .role("USER")
      .build();

    userRepository.save(user);

    UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
    return jwtService.generateToken(userDetails);
  }

  public String login(LoginRequest request) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
    );

    UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
    return jwtService.generateToken(userDetails);
  }
}
