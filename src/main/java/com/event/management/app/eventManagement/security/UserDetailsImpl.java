package com.event.management.app.eventManagement.security;

import com.event.management.app.eventManagement.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

  private Long id;
  private String username;
  private String password;

  public UserDetailsImpl(Long id, String username, String password) {
    this.id = id;
    this.username = username;
    this.password = password;
  }

  public static UserDetailsImpl build(User user) {
    return new UserDetailsImpl(user.getId(), user.getUsername(), user.getPassword());
  }

  public Long getId() {
    return id;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null; // بلا صلاحيات في هذا المثال (يمكن تزيدهم إذا بغيت)
  }

  @Override
  public String getPassword() { return password; }

  @Override
  public String getUsername() { return username; }

  @Override
  public boolean isAccountNonExpired() { return true; }

  @Override
  public boolean isAccountNonLocked() { return true; }

  @Override
  public boolean isCredentialsNonExpired() { return true; }

  @Override
  public boolean isEnabled() { return true; }
}
