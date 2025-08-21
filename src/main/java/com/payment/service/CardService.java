package com.payment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.payment.dto.CardDto;
import com.payment.model.Card;
import com.payment.model.User;
import com.payment.repository.CardRepository;
import com.payment.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    private final UserRepository userRepository;

    public List<CardDto> getAllCards() {
        return cardRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }


    public CardDto createCard(CardDto dto) {
        Card card = new Card();

        // Get user from DB
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        card.setName(dto.getName());
        card.setNumber(dto.getNumber());
        card.setCvv(dto.getCvv());
        card.setLastFourDigits(dto.getLastFourDigits());
        card.setExpiry(dto.getExpiry());
        card.setUser(user);

        Card savedCard = cardRepository.save(card);
        return toDto(savedCard);
    }
    
    public CardDto updateCard(Long id, CardDto dto) {
        Card card = cardRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Card not found"));
        card.setName(dto.getName());
        card.setNumber(dto.getNumber());
        card.setCvv(dto.getCvv());
        card.setExpiry(dto.getExpiry());
        card.setLastFourDigits((dto.getNumber() != null && dto.getNumber().length() >= 4)
            ? dto.getNumber().substring(dto.getNumber().length() - 4)
            : null);
        cardRepository.save(card);
        return toDto(card);
    }

    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }

    private CardDto toDto(Card card) {
        CardDto dto = new CardDto();
        dto.setId(card.getId());
        dto.setName(card.getName());
        dto.setNumber(card.getNumber());
        dto.setCvv(card.getCvv());
        dto.setLastFourDigits(card.getLastFourDigits());
        dto.setExpiry(card.getExpiry());
        dto.setUserId(card.getUser().getId());
        return dto;
    }
}
