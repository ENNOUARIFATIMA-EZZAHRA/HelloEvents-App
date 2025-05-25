package com.event.management.app.eventManagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
  @NotBlank(message = "اسم المستخدم مطلوب")
  private String username;

  @NotBlank(message = "كلمة المرور مطلوبة")
  @Size(min = 8, message = "يجب أن تكون كلمة المرور 8 أحرف على الأقل")
  private String password;

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
}
