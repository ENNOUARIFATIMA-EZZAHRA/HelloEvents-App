package com.event.management.app.eventManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public class UpdateProfileRequest {
    private String email;
    private String fullName;
  }

