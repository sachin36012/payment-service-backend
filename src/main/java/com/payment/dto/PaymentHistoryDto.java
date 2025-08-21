package com.payment.dto;

import com.payment.model.Status;
import com.payment.model.Subscription;
import com.payment.model.User;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class PaymentHistoryDto {
	private Long id;
	private BigDecimal amount;
	private String message;
	private Status status;
	private String name;
	private String email;

}