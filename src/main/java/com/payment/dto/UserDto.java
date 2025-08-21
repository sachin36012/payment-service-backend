package com.payment.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UserDto {
    private long id;
    private UUID uuid;
    private String name;
    private String email;
    private String phNo;
    private String pin;
    private List<CardDto> cards;
    private List<SubscriptionDto> subscriptions;
}