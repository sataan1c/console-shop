package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Store store = new Store();
        Cart cart = new Cart();

        Product book = new Product(1,
                "Song of the ice and wind",
                50,
                "Book",
                5);

        Product laptop = new Product(2,
                "Macbook",
                1000,
                "IT",
                10);

        Product mouse = new Product(3,
                "Logitech G102",
                20,
                "IT",
                4);

        Product book1 = new Product(4,
                "Miyamoto Musashi",
                10,
                "Book",
                2);

        store.addProduct(book);
        store.addProduct(laptop);
        store.addProduct(mouse);
        store.addProduct(book1);

        while (true) {
            int productId;
            System.out.println(" ========================\n JAVA SHOP\n ======================== ");
            System.out.println(" 1. Show products\n" +
                    " 2. Add products to cart\n" +
                    " 3. Show cart\n" +
                    " 4. Decrease quantity\n" +
                    " 5. Remove product\n" +
                    " 6. Checkout\n" +
                    " 0. Exit"
            );

            int usersChoice = keyboard.nextInt();

            switch (usersChoice) {
                case 1:
                    store.showProduct();
                    break;
                case 2:
                    System.out.println("Enter product id: ");
                    productId = keyboard.nextInt();
                    Product currentProduct = store.findProductById(productId);

                    if(currentProduct == null) {
                        System.out.println("Product not found.");
                        break;
                    }

                    cart.addProduct(currentProduct);
                    cart.showCart();

                    break;

                case 3:
                    cart.showCart();

                    break;

                case 4:
                    System.out.println("Enter product id: ");
                    productId = keyboard.nextInt();

                    currentProduct = store.findProductById(productId);

                    if(currentProduct == null) {
                        System.out.println("Product not found.");
                        break;
                    }

                    cart.decreaseQuantity(currentProduct);

                    break;

                case 5:
                    System.out.println("Enter product id: ");
                    productId = keyboard.nextInt();

                    currentProduct = store.findProductById(productId);

                    if(currentProduct == null) {
                        System.out.println("Product not found.");
                        break;
                    }

                    cart.removeItem(currentProduct);

                    break;

                case 6:
                    try {
                        Order order = cart.checkout();
                        order.showOrder();
                    } catch(IllegalStateException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 0:
                    return;

            }
        }
    }
}