package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    private static final String URL = "jdbc:postgresql://localhost:5432/console_shop";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

        public int insertOrder(int customerId, List<CartItem> items) throws SQLException {
            String insertOrderSql = "INSERT INTO orders (customer_id, status) " +
                    "VALUES (?, ?::order_status)";

            String insertItemSql = "INSERT INTO order_items (order_id, product_id, product_name, quantity, price) " +
                    "VALUES (?, ?, ?, ?, ?)";

            int orderId;

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);

            try {
                PreparedStatement statement = connection.prepareStatement(insertOrderSql, Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, customerId);
                statement.setString(2, "pending");
                statement.executeUpdate();

                try(ResultSet keys = statement.getGeneratedKeys()) {
                    keys.next();
                    orderId = keys.getInt(1);
                }

                PreparedStatement itemStatement = connection.prepareStatement(insertItemSql);

                for(CartItem c : items) {
                    itemStatement.setInt(1, orderId);
                    itemStatement.setInt(2, c.getProduct().getId());
                    itemStatement.setString(3, c.getProduct().getNameOfProduct());
                    itemStatement.setInt(4, c.getQuantity());
                    itemStatement.setDouble(5, c.getProduct().getPrice());

                    itemStatement.executeUpdate();
                }

                String updateStock = "UPDATE products " +
                        "SET quantity = quantity - ? " +
                        "WHERE id = ?";

                PreparedStatement updatedStock = connection.prepareStatement(updateStock);

                for(CartItem c : items) {
                    updatedStock.setInt(1, c.getQuantity());
                    updatedStock.setInt(2, c.getProduct().getId());
                    updatedStock.executeUpdate();
                }

                connection.commit();

            } catch (SQLException e) {
                connection.rollback();
                throw e;
            } finally {
                connection.close();
            }

            return orderId;
        }
}
