package com.payment.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.payment.dto.UserSubscriptionDto;
import com.payment.service.UserSubscriptionService;

@RestController
@RequestMapping("/api/user-subscriptions")
public class UserSubscriptionController {

    private final UserSubscriptionService userSubService;

    public UserSubscriptionController(UserSubscriptionService userSubService) {
        this.userSubService = userSubService;
    }

    @GetMapping
    public List<UserSubscriptionDto> getAllUserSubscriptions() {
        return userSubService.getAllUserSubscriptions();
    }

    @PostMapping
    public UserSubscriptionDto createUserSubscription(@RequestBody UserSubscriptionDto dto) {
        return userSubService.createUserSubscription(dto);
    }
    
    @PutMapping("/{id}")
    public UserSubscriptionDto update(@PathVariable Long id, @RequestBody UserSubscriptionDto dto) {
        return userSubService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
    	userSubService.delete(id);
    }
}
