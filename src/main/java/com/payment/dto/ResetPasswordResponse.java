package com.payment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordResponse {
    private String message;

    public ResetPasswordResponse(String message) {
        this.message = message;
    }
}
