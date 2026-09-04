package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    private static final String URL = "jdbc:postgresql://localhost:5432/console_shop";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public Integer findIdByName(String name) throws SQLException {
        String sql = "SELECT id FROM categories " +
                "WHERE name = ?";

        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);

            try(ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
        }

        return null;
    }

}
