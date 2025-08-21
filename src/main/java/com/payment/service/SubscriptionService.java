package com.payment.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.payment.dto.PaymentGatewayDto;
import com.payment.dto.SubscriptionDto;
import com.payment.model.Subscription;
import com.payment.repository.SubscriptionRepository;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public List<SubscriptionDto> getAllSubscriptions() {
        return subscriptionRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public SubscriptionDto createSubscription(SubscriptionDto dto) {
        Subscription sub = new Subscription();
        sub.setName(dto.getName());
        sub.setAmount(dto.getAmount());
        sub.setDescription(dto.getDescription());
        subscriptionRepository.save(sub);
        return toDto(sub);
    }
    
    public SubscriptionDto updateSubscription(Long id, SubscriptionDto dto) {
        Subscription sub = subscriptionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Subscription not found"));
        sub.setName(dto.getName());
        sub.setAmount(dto.getAmount());
        sub.setDescription(dto.getDescription());
        subscriptionRepository.save(sub);
        return toDto(sub);
    }

    public void deleteSubscription(Long id) {
        subscriptionRepository.deleteById(id);
    }

    private SubscriptionDto toDto(Subscription sub) {
        SubscriptionDto dto = new SubscriptionDto();
        dto.setId(sub.getId());
        dto.setName(sub.getName());
        dto.setAmount(sub.getAmount());
        dto.setDescription(sub.getDescription());
        if (sub.getPaymentGateway() != null) {
            PaymentGatewayDto pgDto = new PaymentGatewayDto();
            pgDto.setId(sub.getPaymentGateway().getId());
            pgDto.setName(sub.getPaymentGateway().getName());
            pgDto.setDetails(sub.getPaymentGateway().getDetails());
            dto.setPaymentGateway(pgDto);
        }
        return dto;
    }
}
