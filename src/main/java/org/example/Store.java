package org.example;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Product> products;

    public Store() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void showProduct() {
        for(Product p : products) {
            System.out.println(p);
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product findProductById(int id) {
        for(Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }

        return null;
    }
}
