package com.payment.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.payment.dto.ResetPasswordRequest;
import com.payment.dto.ResetPasswordResponse;
import com.payment.service.PasswordService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class PasswordController {

	private final PasswordService passwordService;

	public PasswordController(PasswordService passwordService) {
		this.passwordService = passwordService;
	}

	@PostMapping("/reset-password")
	public ResetPasswordResponse resetPassword(@RequestBody ResetPasswordRequest request,
			Authentication authentication) {
		String email = authentication.getName(); // from Bearer token
		log.info("Email Received is : {}", email);
		log.info("Old Password is {}", request.getOldPassword());
		return passwordService.resetPassword(email, request);
	}
}
