package com.payment.service;

import com.payment.dto.UserSubscriptionDto;
import com.payment.model.Card;
import com.payment.model.User;
import com.payment.model.UserSubscription;
import com.payment.repository.UserSubscriptionRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserSubscriptionService {

    private final UserSubscriptionRepository userSubRepository;

    public UserSubscriptionService(UserSubscriptionRepository userSubRepository) {
        this.userSubRepository = userSubRepository;
    }

    public List<UserSubscriptionDto> getAllUserSubscriptions() {
        return userSubRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public UserSubscriptionDto createUserSubscription(UserSubscriptionDto dto) {
        UserSubscription us = new UserSubscription();
        us.setStartDate(dto.getStartDate());
        us.setEndDate(dto.getEndDate());
        us.setStatus(dto.getStatus());
        userSubRepository.save(us);
        return toDto(us);
    }
    
    public UserSubscriptionDto update(Long id, UserSubscriptionDto dto) {
        UserSubscription entity = userSubRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("UserSubscription not found"));
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setStatus(dto.getStatus());
        userSubRepository.save(entity);
        return toDto(entity);
    }

    public void delete(Long id) {
    	userSubRepository.deleteById(id);
    }

    private UserSubscriptionDto toDto(UserSubscription us) {
        UserSubscriptionDto dto = new UserSubscriptionDto();
        String email = us.getUser().getEmail();
        String cardNumber = us.getCard().getNumber();
        dto.setId(us.getId());
        dto.setStartDate(us.getStartDate());
        dto.setEndDate(us.getEndDate());
        dto.setStatus(us.getStatus());
        dto.setEmail(email);
        dto.setCardNumber(cardNumber);
        return dto;
    }
}
