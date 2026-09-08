package org.example;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ProductDao productDao = new ProductDao();
        Store store = new Store();

        try{
            Product product = store.findProductById(4);
            CartItem item = new CartItem(product, 1 );

            List<CartItem> items = new ArrayList<>();
            items.add(item);

            int orderId = store.insertOrder(1, items);
            System.out.println("Заказ создан, id = " + orderId);
        } catch (SQLException e) {
            System.out.println("Ошибка при оформлении заказа: " + e.getMessage());
        }


//        try {
//            List<Product> products = productDao.findAll();
//            Product currentProduct = store.findProductById(2);
//            System.out.println(currentProduct);
//            for(Product product : products) {
//                System.out.println(product);
//            }
//        } catch (SQLException e) {
//            System.out.println("Ошибка при получении товаров: " + e.getMessage());
//        }
//
//        try {
//            Product product = new Product(10, "Java Head First", 25, "Book", 20);
//            productDao.insertProduct(product);
//            System.out.println("Продукт был успешно добавлен.");
//        } catch (SQLException e) {
//            System.out.println("Ошибка при добавлении товара: " + e.getMessage());
//        }
//
//        try {
//            Product product = new Product(11, "Glasses", 50, "Health", 1);
//            productDao.insertProduct(product);
//            System.out.println("Продукт был успешно добавлен.");
//        } catch (SQLException e) {
//            System.out.println("Ошибка при добавлении товара: " + e.getMessage());
//        }

//        try{
//            productDao.updateQuantity(6, 40);
//        } catch (SQLException e) {
//            System.out.println("Не удалось обновить количество товара на складе: " + e.getMessage());
//        }
    }
}