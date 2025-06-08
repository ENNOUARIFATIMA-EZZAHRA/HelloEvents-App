package com.event.management.app.eventManagement.controller;

import com.event.management.app.eventManagement.entity.User;
import com.event.management.app.eventManagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')") // يقدر يدخل فقط admin
public class AdminController {

  private final UserService userService;

  // عرض جميع المستخدمين
  @GetMapping("/users")
  public ResponseEntity<List<User>> getAllUsers() {
    return ResponseEntity.ok(userService.getAllUsers());
  }

  // حذف مستخدم حسب username
  @DeleteMapping("/user/{username}")
  public ResponseEntity<Void> deleteUser(@PathVariable String username) {
    userService.deleteUser(username);
    return ResponseEntity.noContent().build();
  }

  // تغيير دور المستخدم
  @PutMapping("/user/{username}/role")
  public ResponseEntity<User> updateUserRole(@PathVariable String username, @RequestParam String newRole) {
    User updatedUser = userService.updateUserRoles(username, Collections.singletonList(newRole));
    return ResponseEntity.ok(updatedUser);
  }
}
