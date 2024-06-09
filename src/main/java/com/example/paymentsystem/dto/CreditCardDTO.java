package com.example.paymentsystem.dto;

import lombok.Data;

@Data
public class CreditCardDTO {
    private Long id;
    private String cardNumber;
    private String expiryDate;
    private String cvv;
    private Long clientId;
}
