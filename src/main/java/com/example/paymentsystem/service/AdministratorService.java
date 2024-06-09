package com.example.paymentsystem.service;

import com.example.paymentsystem.repository.AdministratorRepository;

import java.sql.SQLException;

public class AdministratorService {
    private final AdministratorRepository administratorRepository = new AdministratorRepository();

    public void unblockAccount(Long accountId) throws SQLException {
        administratorRepository.unblockAccount(accountId);
    }
}
