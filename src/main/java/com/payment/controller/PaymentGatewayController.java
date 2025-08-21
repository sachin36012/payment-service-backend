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
import com.payment.dto.PaymentGatewayDto;
import com.payment.service.PaymentGatewayService;

@RestController
@RequestMapping("/api/gateways")
public class PaymentGatewayController {

    private final PaymentGatewayService gatewayService;

    public PaymentGatewayController(PaymentGatewayService gatewayService) {
        this.gatewayService = gatewayService;
    }

    @GetMapping
    public List<PaymentGatewayDto> getAllGateways() {
        return gatewayService.getAllGateways();
    }

    @PostMapping
    public PaymentGatewayDto createGateway(@RequestBody PaymentGatewayDto dto) {
        return gatewayService.createGateway(dto);
    }
    
    @PutMapping("/{id}")
    public PaymentGatewayDto updateGateway(@PathVariable Long id, @RequestBody PaymentGatewayDto dto) {
        return gatewayService.updateGateway(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteGateway(@PathVariable Long id) {
        gatewayService.deleteGateway(id);
    }
}
