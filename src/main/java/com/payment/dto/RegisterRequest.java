package com.payment.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Long id;
    private UUID uuid;
    private String pin;
    private String phNo;
    private boolean enabled;
    private String role;

}