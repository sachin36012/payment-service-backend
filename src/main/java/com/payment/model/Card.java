package com.payment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents card information for payment processing.
 * This entity is used to store card details securely.
 */

@Entity
@Table(name = "cards")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Full card number (should be encrypted or tokenized in real applications)
    private String number;

    @Column(length=3)
    private String cvv;

    // Last 4 digits for display
    @Column(length = 4)
    private String lastFourDigits;

    private String expiry; // Format MM/YY

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserSubscription> userSubscriptions;

    // Automatically extract last 4 digits when setting number
    public void setNumber(String number) {
        this.number = number;
        if (number != null && number.length() >= 4) {
            this.lastFourDigits = number.substring(number.length() - 4);
        }
    }

}
