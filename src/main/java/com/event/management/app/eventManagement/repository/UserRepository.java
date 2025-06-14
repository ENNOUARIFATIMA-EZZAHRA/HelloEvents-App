package com.event.management.app.eventManagement.repository;

import com.event.management.app.eventManagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);
  void deleteByUsername(String username);
}
