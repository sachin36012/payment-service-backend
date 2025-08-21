package com.payment.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

import com.payment.model.Card;
import com.payment.model.User;

@Getter
@Setter
public class UserSubscriptionDto {
    private Long id;
    private String status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String email;
    private String cardNumber;
}