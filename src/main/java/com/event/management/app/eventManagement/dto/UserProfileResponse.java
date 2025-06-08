
package com.event.management.app.eventManagement.dto;

import com.event.management.app.eventManagement.entity.Role;

import java.util.Set;

public class UserProfileResponse{
  private Long id;
  private String username;
  private String role;
  private String email;
  private String fullName;

  public UserProfileResponse(Long id, String username, Set<Role> role, String email, String fullName) {
    this.id = id;
    this.username = username;
    this.role = String.valueOf(role);
    this.email = email;
    this.fullName = fullName;
  }

  // getters and setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getUsername() { return username; }
  public void setUsername(String username) { this.username = username; }

  public String getRole() { return role; }
  public void setRole(String role) { this.role = role; }

  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }

  public String getFullName() { return fullName; }
  public void setFullName(String fullName) { this.fullName = fullName; }
}
