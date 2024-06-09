package com.example.paymentsystem.repository;

import com.example.paymentsystem.config.DatabaseConfig;
import com.example.paymentsystem.model.Payment;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepository {
    private static final Logger logger = Logger.getLogger(ClientRepository.class);

    public void createPayment(Payment payment) throws SQLException {
        String query = "INSERT INTO payments (amount, date, recipient, account_id) VALUES (?, ?, ?, ?)";
        logger.info("Creating payment: " + payment);

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, payment.getAmount());
            statement.setString(2, payment.getDate());
            statement.setString(3, payment.getRecipient());
            statement.setLong(4, payment.getAccountId());
            statement.executeUpdate();
        }
    }

    public Payment findPaymentById(Long id) throws SQLException {
        String query = "SELECT * FROM payments WHERE id = ?";
        logger.info("Finding payment by ID: " + id);
        Payment payment = null;

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                payment = new Payment();
                payment.setId(resultSet.getLong("id"));
                payment.setAmount(resultSet.getDouble("amount"));
                payment.setDate(resultSet.getString("date"));
                payment.setRecipient(resultSet.getString("recipient"));
                payment.setAccountId(resultSet.getLong("account_id"));
            }
        }

        return payment;
    }

    public List<Payment> findAllPaymentsByAccountId(Long accountId) throws SQLException {
        List<Payment> payments = new ArrayList<>();
        String query = "SELECT * FROM payments WHERE account_id = ?";
        logger.info("Finding all payments by accoutn ID: " + accountId);

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, accountId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Payment payment = new Payment();
                payment.setId(resultSet.getLong("id"));
                payment.setAmount(resultSet.getDouble("amount"));
                payment.setDate(resultSet.getString("date"));
                payment.setRecipient(resultSet.getString("recipient"));
                payment.setAccountId(resultSet.getLong("account_id"));
                payments.add(payment);
            }
        }

        return payments;
    }

    public void updatePayment(Payment payment) throws SQLException {
        String query = "UPDATE payments SET amount = ?, date = ?, recipient = ? WHERE id = ?";
        logger.info("Updating payment: " + payment);

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, payment.getAmount());
            statement.setString(2, payment.getDate());
            statement.setString(3, payment.getRecipient());
            statement.setLong(4, payment.getId());
            statement.executeUpdate();
        }
    }

    public void deletePayment(Long id) throws SQLException {
        String query = "DELETE FROM payments WHERE id = ?";
        logger.info("Deleting payment by ID: " + id);

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }
}
