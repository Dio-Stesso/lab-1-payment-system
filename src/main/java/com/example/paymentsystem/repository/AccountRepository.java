package com.example.paymentsystem.repository;

import com.example.paymentsystem.config.DatabaseConfig;
import com.example.paymentsystem.model.Account;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
    private static final Logger logger = Logger.getLogger(ClientRepository.class);

    public void createAccount(Account account) throws SQLException {
        String query = "INSERT INTO accounts (balance, status, credit_card_id) VALUES (?, ?, ?)";
        logger.info("Creating account: " + account);

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, account.getBalance());
            statement.setString(2, account.getStatus());
            statement.setLong(3, account.getCreditCardId());
            statement.executeUpdate();
        }
    }

    public Account findAccountById(Long id) throws SQLException {
        String query = "SELECT * FROM accounts WHERE id = ?";
        logger.info("Finding account by ID: " + id);
        Account account = null;

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                account = new Account();
                account.setId(resultSet.getLong("id"));
                account.setBalance(resultSet.getDouble("balance"));
                account.setStatus(resultSet.getString("status"));
                account.setCreditCardId(resultSet.getLong("credit_card_id"));
            }
        }

        return account;
    }

    public List<Account> findAllAccountsByCreditCardId(Long creditCardId) throws SQLException {
        List<Account> accounts = new ArrayList<>();
        String query = "SELECT * FROM accounts WHERE credit_card_id = ?";
        logger.info("Finding all accounts by credit card ID");

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, creditCardId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Account account = new Account();
                account.setId(resultSet.getLong("id"));
                account.setBalance(resultSet.getDouble("balance"));
                account.setStatus(resultSet.getString("status"));
                account.setCreditCardId(resultSet.getLong("credit_card_id"));
                accounts.add(account);
            }
        }

        return accounts;
    }

    public void updateAccount(Account account) throws SQLException {
        String query = "UPDATE accounts SET balance = ?, status = ? WHERE id = ?";
        logger.info("Updating account: " + account);

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, account.getBalance());
            statement.setString(2, account.getStatus());
            statement.setLong(3, account.getId());
            statement.executeUpdate();
        }
    }

    public void deleteAccount(Long id) throws SQLException {
        String query = "DELETE FROM accounts WHERE id = ?";
        logger.info("Deleting account by ID: " + id);

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }
}
