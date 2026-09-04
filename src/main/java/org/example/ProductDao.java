package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductDao {
        private static final String URL = "jdbc:postgresql://localhost:5432/console_shop";
        private static final String USER = "postgres";
        private static final String PASSWORD = "postgres";

    public List<Product> findAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.id, p.name, p.price, c.name AS category_name, p.quantity " +
                "FROM products p " +
                "JOIN categories c ON p.category_id = c.id";

        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String category = resultSet.getString("category_name");
                int quantity = resultSet.getInt("quantity");

                products.add(new Product(id, name, price, category, quantity));
            }
        }

        return products;
    }

    public Product findProductById(int id) throws SQLException {
        String sql = "SELECT p.id, p.name, p.price, c.name AS category_name, p.quantity " +
                "FROM products p " +
                "JOIN categories c ON p.category_id = c.id " +
                "WHERE p.id = ? ";

        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()) { // можно потом заменить на if
                    id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    double price = resultSet.getDouble("price");
                    String category = resultSet.getString("category_name");
                    int quantity = resultSet.getInt("quantity");

                    return new Product(id, name, price, category, quantity);
                }

            }

            return null;
        }
    }

    public void insertProduct(Product product) throws SQLException {
        String sql = "INSERT INTO products (name, price, category_id, quantity) " +
                "VALUES (?, ?, ?, ?)";

        CategoryDao category = new CategoryDao();
        Integer categoryId = category.findIdByName(product.getCategory());

        if(categoryId == null) {
            throw new SQLException("Категория не найдена: " + product.getCategory());
        }

        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, product.getNameOfProduct());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, categoryId);
            statement.setInt(4, product.getQuantity());

            statement.executeUpdate();


        }
    }
}
