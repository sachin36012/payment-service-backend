package com.payment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "payment_gateways")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentGateway {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String details;

    @OneToMany(mappedBy = "paymentGateway", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subscription> subscriptions;
}