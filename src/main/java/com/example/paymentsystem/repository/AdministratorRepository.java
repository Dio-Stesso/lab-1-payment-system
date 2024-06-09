package com.example.paymentsystem.repository;

import com.example.paymentsystem.config.DatabaseConfig;
import com.example.paymentsystem.model.Administrator;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdministratorRepository {
    private static final Logger logger = Logger.getLogger(ClientRepository.class);

    public Administrator findAdministratorById(Long id) throws SQLException {
        String query = "SELECT * FROM administrators WHERE id = ?";
        logger.info("Finding administrator by ID: " + id);
        Administrator administrator = null;

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                administrator = new Administrator();
                administrator.setId(resultSet.getLong("id"));
                administrator.setName(resultSet.getString("name"));
                administrator.setLogin(resultSet.getString("login"));
                administrator.setPassword(resultSet.getString("password"));
            }
        }

        return administrator;
    }

    public List<Administrator> findAllAdministrators() throws SQLException {
        List<Administrator> administrators = new ArrayList<>();
        String query = "SELECT * FROM administrators";
        logger.info("Finding all administrators");

        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Administrator administrator = new Administrator();
                administrator.setId(resultSet.getLong("id"));
                administrator.setName(resultSet.getString("name"));
                administrator.setLogin(resultSet.getString("login"));
                administrator.setPassword(resultSet.getString("password"));
                administrators.add(administrator);
            }
        }

        return administrators;
    }

    public void unblockAccount(Long accountId) throws SQLException {
        String query = "UPDATE accounts SET status = 'active' WHERE id = ?";
        logger.info("Unblocking account with ID: " + accountId);

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, accountId);
            statement.executeUpdate();
        }
    }
}
