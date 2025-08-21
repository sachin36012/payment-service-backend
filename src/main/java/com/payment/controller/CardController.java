package com.payment.controller;

import org.springframework.web.bind.annotation.*;

import com.payment.dto.CardDto;
import com.payment.service.CardService;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public List<CardDto> getAllCards() {
        return cardService.getAllCards();
    }

    @PostMapping
    public CardDto createCard(@RequestBody CardDto dto) {
        return cardService.createCard(dto);
    }
    
    @PutMapping("/{id}")
    public CardDto updateCard(@PathVariable Long id, @RequestBody CardDto dto) {
        return cardService.updateCard(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
    }
}
