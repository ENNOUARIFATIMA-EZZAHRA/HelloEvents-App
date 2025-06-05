package com.event.management.app.eventManagement.controller;

import com.event.management.app.eventManagement.dto.UserProfileResponse;
import com.event.management.app.eventManagement.entity.User;
import com.event.management.app.eventManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

  private final UserRepository userRepository;

  // GET: جلب بيانات البروفايل ديال المستخدم اللي دار login
  @GetMapping
  public ResponseEntity<UserProfileResponse> getProfile(Principal principal) {
    String username = principal.getName();
    Optional<User> userOpt = userRepository.findByUsername(username);

    if (userOpt.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    User user = userOpt.get();

    UserProfileResponse dto = new UserProfileResponse(
      user.getId(),
      user.getUsername(),
      user.getRole(),
      user.getEmail(),
      user.getFullName()
    );

    return ResponseEntity.ok(dto);
  }

  // PUT: تحديث بيانات البروفايل (مثلاً: email, fullName)
  @PutMapping
  public ResponseEntity<?> updateProfile(@RequestBody User updatedUser) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();

    Optional<User> userOpt = userRepository.findByUsername(username);
    if (userOpt.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    User user = userOpt.get();

    // نحدّث فقط الحقول اللي مسموح لهم بالتغيير
    user.setEmail(updatedUser.getEmail());
    user.setFullName(updatedUser.getFullName());
    // ما نغيروش username أو password هنا للحفاظ على الأمان

    userRepository.save(user);

    return ResponseEntity.ok("Profile updated successfully");
  }
}
