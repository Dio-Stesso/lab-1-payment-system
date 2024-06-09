package com.example.paymentsystem.dto;

import lombok.Data;

@Data
public class PaymentDTO {
    private Long id;
    private Double amount;
    private String date;
    private String recipient;
    private Long accountId;
}
