package com.payment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.payment.dto.PaymentHistoryDto;
import com.payment.model.Subscription;
import com.payment.model.User;
import com.payment.repository.PaymentHistoryRepository;
import com.payment.repository.SubscriptionRepository;
import com.payment.repository.UserRepository;
import com.payment.service.PaymentHistoryService;

@RestController
@RequestMapping("/api/histories")
public class PaymentHistoryController {

	private final PaymentHistoryService historyService;

	private PaymentHistoryRepository paymentRepo;

	private SubscriptionRepository subscriptionRepo;

	private UserRepository userRepo;

	public PaymentHistoryController(PaymentHistoryService historyService) {
		this.historyService = historyService;
	}

	@GetMapping
	public List<PaymentHistoryDto> getAllHistories() {
		return historyService.getAllHistories();
	}

	@GetMapping("/details")
	public Map<String, String> getDetails(@RequestParam Long subscriptionId, @RequestParam Long userId) {
		Map<String, String> response = new HashMap<>();
		Subscription sub = subscriptionRepo.findById(subscriptionId).orElse(null);
		User user = userRepo.findById(userId).orElse(null);

		response.put("subscriptionName", sub != null ? sub.getName() : "Not Found");
		response.put("userEmail", user != null ? user.getEmail() : "Not Found");

		return response;
	}

}
