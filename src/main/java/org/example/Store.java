package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Store {
    private final ProductDao productDao = new ProductDao();

    public Store() {
    }

    public void insertProduct(Product product) throws SQLException {
        productDao.insertProduct(product);
    }

    public void showProduct() throws SQLException {
        for (Product p : productDao.findAll()) {
            System.out.println(p);
        }
    }

    public Product findProductById(int id) throws SQLException {
        return productDao.findProductById(id);
    }
}
