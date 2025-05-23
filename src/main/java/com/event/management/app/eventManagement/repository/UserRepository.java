package com.event.management.app.eventManagement.repository;

import com.event.management.app.eventManagement.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
