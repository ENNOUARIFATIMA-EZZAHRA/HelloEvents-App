package com.event.management.app.eventManagement.dto;

public class UserProfileResponse {
  private String username;
  private String email;
  private String fullName;

  public UserProfileResponse(String username, String email, String fullName) {
    this.username = username;
    this.email = email;
    this.fullName = fullName;
  }

  // getters & setters
}
