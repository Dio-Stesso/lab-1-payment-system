package com.example.paymentsystem.model;

import lombok.Data;

@Data
public class Payment {
    private Long id;
    private Double amount;
    private String date;
    private String recipient;
    private Long accountId;
}
