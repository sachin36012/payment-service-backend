package com.payment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentGatewayDto {
    private Long id;
    private String name;
    private String details;
}