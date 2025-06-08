package com.event.management.app.eventManagement.security;

import com.event.management.app.eventManagement.dto.LoginRequest;
import com.event.management.app.eventManagement.dto.RegisterRequest;
import com.event.management.app.eventManagement.entity.Role;
import com.event.management.app.eventManagement.entity.User;
import com.event.management.app.eventManagement.repository.UserRepository;
import com.event.management.app.eventManagement.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final JwtService jwtService;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final CustomUserDetailsService userDetailsService;

  public String register(RegisterRequest request) {
    if (userRepository.findByUsername(request.getUsername()).isPresent()) {
      throw new RuntimeException("Nom d'utilisateur déjà utilisé.");
    }

    User user = new User();
    user.setEmail(request.getEmail());
    user.setFullName(request.getFullName());
    user.setUsername(request.getUsername());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setRoles(Set.of(Role.USER, Role.ADMIN));

    // ✅ Enum

    userRepository.save(user); // ✅ إصلاح

    UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
    return jwtService.generateToken(userDetails);
  }

  public String login(LoginRequest request) {
    // Authentifier l'utilisateur
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        request.getUsername(),
        request.getPassword()
      )
    );

    // Charger les informations de l'utilisateur et générer un token
    UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
    return jwtService.generateToken(userDetails);
  }
}
