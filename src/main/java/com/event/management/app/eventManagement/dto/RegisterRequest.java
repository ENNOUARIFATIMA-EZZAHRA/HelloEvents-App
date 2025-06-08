package com.event.management.app.eventManagement.dto;

public class RegisterRequest {
  private String username;
  private String password;
  private String email;
  private String fullName;
  private String role;

  public RegisterRequest(String username, String password, String email, String fullName, String role) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.fullName = fullName;
    this.role = role;
  }

  public RegisterRequest() {}

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }
}
