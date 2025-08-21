package com.payment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.payment.dto.PaymentHistoryDto;
import com.payment.model.PaymentHistory;
import com.payment.repository.PaymentHistoryRepository;

@Service
public class PaymentHistoryService {

    private final PaymentHistoryRepository historyRepository;

    public PaymentHistoryService(PaymentHistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public List<PaymentHistoryDto> getAllHistories() {
        return historyRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    private PaymentHistoryDto toDto(PaymentHistory history) {
        PaymentHistoryDto dto = new PaymentHistoryDto();
        String name = history.getSubscription().getName();
        String email = history.getUser().getEmail();
        dto.setId(history.getId());
        dto.setAmount(history.getAmount());
        dto.setMessage(history.getMessage());
        dto.setStatus(history.getStatus());
        dto.setName(name);
        dto.setEmail(email);
        return dto;
    }
}
