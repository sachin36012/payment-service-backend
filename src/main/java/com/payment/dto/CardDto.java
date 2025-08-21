package com.payment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardDto {
    private Long id;
    private String name;
    private String number;
    private String cvv;
    private String lastFourDigits;
    private String expiry;
    private Long userId;
}