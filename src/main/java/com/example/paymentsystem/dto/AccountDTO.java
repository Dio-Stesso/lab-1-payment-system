package com.example.paymentsystem.dto;

import lombok.Data;

@Data
public class AccountDTO {
    private Long id;
    private Double balance;
    private String status;
    private Long creditCardId;
}
