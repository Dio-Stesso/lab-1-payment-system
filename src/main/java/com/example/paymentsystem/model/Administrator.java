package com.example.paymentsystem.model;


import lombok.Data;

@Data
public class Administrator {
    private Long id;
    private String name;
    private String login;
    private String password;
}
