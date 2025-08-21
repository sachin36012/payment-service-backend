package com.payment.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class SubscriptionDto {
    private Long id;
    private String name;
    private BigDecimal amount;
    private String description;
    private PaymentGatewayDto paymentGateway;
}