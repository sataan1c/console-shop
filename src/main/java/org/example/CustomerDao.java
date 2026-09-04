package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
    private static final String URL = "jdbc:postgresql://localhost:5432/console_shop";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public List<Customer> findAll() throws SQLException {
        List<Customer> customers = new ArrayList<>();

        String sql = "SELECT id, name, email, balance " +
                "FROM customers";

        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                double balance = resultSet.getDouble("balance");

                customers.add(new Customer(id, name, email, balance));
            }
        }

        return customers;
    }

    public Customer findById(int id) throws SQLException {
        String sql = "SELECT id, name, email, balance " +
                "FROM customers " +
                "WHERE id = ?";

        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try(ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    double balance = resultSet.getDouble("balance");

                    return new Customer(id, name, email, balance);
                }
            }
        }

        return null;
    }
}
