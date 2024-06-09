package com.example.paymentsystem.model;

import lombok.Data;

@Data
public class Account {
    private Long id;
    private Double balance;
    private String status;
    private Long creditCardId;
}
