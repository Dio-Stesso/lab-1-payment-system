package com.example.paymentsystem.repository;

import com.example.paymentsystem.config.DatabaseConfig;
import com.example.paymentsystem.model.Client;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository {
    private static final Logger logger = Logger.getLogger(ClientRepository.class);

    public void createClient(Client client) throws SQLException {
        String query = "INSERT INTO clients (name, email) VALUES (?, ?)";
        logger.info("Creating client: " + client);

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getEmail());
            statement.executeUpdate();
        }
    }

    public Client findClientById(Long id) throws SQLException {
        String query = "SELECT * FROM clients WHERE id = ?";
        logger.info("Finding client by ID: " + id);
        Client client = null;

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                client = new Client();
                client.setId(resultSet.getLong("id"));
                client.setName(resultSet.getString("name"));
                client.setEmail(resultSet.getString("email"));
            }
        }

        return client;
    }

    public List<Client> findAllClients() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM clients";
        logger.info("Finding all clients");

        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getLong("id"));
                client.setName(resultSet.getString("name"));
                client.setEmail(resultSet.getString("email"));
                clients.add(client);
            }
        }

        return clients;
    }

    public void updateClient(Client client) throws SQLException {
        String query = "UPDATE clients SET name = ?, email = ? WHERE id = ?";
        logger.info("Updating client: " + client);

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getEmail());
            statement.setLong(3, client.getId());
            statement.executeUpdate();
        }
    }

    public void deleteClient(Long id) throws SQLException {
        String query = "DELETE FROM clients WHERE id = ?";
        logger.info("Deleting client by ID: " + id);

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }
}
