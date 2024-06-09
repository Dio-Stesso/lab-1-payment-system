package com.example.paymentsystem.repository;

import com.example.paymentsystem.config.DatabaseConfig;
import com.example.paymentsystem.model.CreditCard;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreditCardRepository {
    private static final Logger logger = Logger.getLogger(ClientRepository.class);

    public void createCreditCard(CreditCard creditCard) throws SQLException {
        String query = "INSERT INTO credit_cards (card_number, expiry_date, cvv, client_id) VALUES (?, ?, ?, ?)";
        logger.info("Creating credit card: " + creditCard);

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, creditCard.getCardNumber());
            statement.setString(2, creditCard.getExpiryDate());
            statement.setString(3, creditCard.getCvv());
            statement.setLong(4, creditCard.getClientId());
            statement.executeUpdate();
        }
    }

    public CreditCard findCreditCardById(Long id) throws SQLException {
        String query = "SELECT * FROM credit_cards WHERE id = ?";
        logger.info("Finding credit card by ID: " + id);
        CreditCard creditCard = null;

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                creditCard = new CreditCard();
                creditCard.setId(resultSet.getLong("id"));
                creditCard.setCardNumber(resultSet.getString("card_number"));
                creditCard.setExpiryDate(resultSet.getString("expiry_date"));
                creditCard.setCvv(resultSet.getString("cvv"));
                creditCard.setClientId(resultSet.getLong("client_id"));
            }
        }

        return creditCard;
    }

    public List<CreditCard> findAllCreditCardsByClientId(Long clientId) throws SQLException {
        List<CreditCard> creditCards = new ArrayList<>();
        String query = "SELECT * FROM credit_cards WHERE client_id = ?";
        logger.info("Finding all credit cards by client ID:" + clientId);

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, clientId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                CreditCard creditCard = new CreditCard();
                creditCard.setId(resultSet.getLong("id"));
                creditCard.setCardNumber(resultSet.getString("card_number"));
                creditCard.setExpiryDate(resultSet.getString("expiry_date"));
                creditCard.setCvv(resultSet.getString("cvv"));
                creditCard.setClientId(resultSet.getLong("client_id"));
                creditCards.add(creditCard);
            }
        }

        return creditCards;
    }

    public void updateCreditCard(CreditCard creditCard) throws SQLException {
        String query = "UPDATE credit_cards SET card_number = ?, expiry_date = ?, cvv = ? WHERE id = ?";
        logger.info("Updating credit card: " + creditCard);

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, creditCard.getCardNumber());
            statement.setString(2, creditCard.getExpiryDate());
            statement.setString(3, creditCard.getCvv());
            statement.setLong(4, creditCard.getId());
            statement.executeUpdate();
        }
    }

    public void deleteCreditCard(Long id) throws SQLException {
        String query = "DELETE FROM credit_cards WHERE id = ?";
        logger.info("Deleting credit card by ID: " + id);

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }
}
