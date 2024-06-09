package com.example.paymentsystem.model;

import lombok.Data;

@Data
public class CreditCard {
    private Long id;
    private String cardNumber;
    private String expiryDate;
    private String cvv;
    private Long clientId;
}
