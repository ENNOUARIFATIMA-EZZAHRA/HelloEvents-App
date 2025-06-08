package com.event.management.app.eventManagement.service;

import com.event.management.app.eventManagement.entity.Role;
import com.event.management.app.eventManagement.entity.User;
import com.event.management.app.eventManagement.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

  private final UserRepository userRepository;

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public void deleteUser(String username) {
    userRepository.deleteByUsername(username);
  }

  public User updateUserRoles(String username, List<String> newRoles) {
    User user = userRepository.findByUsername(username)
      .orElseThrow(() -> new RuntimeException("User not found"));

    user.setRoles(newRoles.stream()
      .map(role -> Role.valueOf(role.toUpperCase()))
      .collect(Collectors.toSet())); // ← التصحيح هنا

    return userRepository.save(user);
  }


}
