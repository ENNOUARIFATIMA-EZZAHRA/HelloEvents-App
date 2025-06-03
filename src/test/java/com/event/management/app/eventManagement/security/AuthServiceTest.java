package com.event.management.app.eventManagement.security;

import com.event.management.app.eventManagement.dto.RegisterRequest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AuthServiceTest {

  @Autowired
  private AuthService authService;

  @Test
  public void testRegisterNewUser() {
    RegisterRequest request = new RegisterRequest(
      "newuser",
      "password123",
      "newuser@example.com",
      "New User"
    );

    String token = authService.register(request);

    assertThat(token).isNotNull();
    assertThat(token).isNotEmpty();
  }
}
