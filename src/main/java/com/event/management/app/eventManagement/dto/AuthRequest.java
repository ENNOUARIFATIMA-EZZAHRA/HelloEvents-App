package com.event.management.app.eventManagement.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}