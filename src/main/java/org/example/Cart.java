package org.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cart {
    private List<CartItem> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public void addProduct(Product product) {
        boolean found = false;

        if(product.getQuantity() > 0) {
            for (CartItem c : items) {
                if (product.equals(c.getProduct())) {
                    c.increaseQuantity();
                    c.getProduct().decreaseQuantity();

                    found = true;
                }
            }

            if (!found) {
                CartItem cartItem = new CartItem(product, 1);
                items.add(cartItem);
                cartItem.getProduct().decreaseQuantity();
            }
        }
    }

    public void decreaseQuantity(Product product) {
        Iterator<CartItem> iterator = items.iterator();

        while(iterator.hasNext()) {
            CartItem c = iterator.next();

            if(product.equals(c.getProduct())) {
                if(c.getQuantity() == 1) {
                    product.increaseQuantity(1);
                    iterator.remove();
                } else if(c.getQuantity() > 1) {
                    c.decreaseQuantity();
                    product.increaseQuantity(1);
                }
            }
        }
    }

    public void removeItem(Product product) {
        Iterator<CartItem> iterator = items.iterator();

        while(iterator.hasNext()) {
            CartItem c = iterator.next();

            if(product.equals(c.getProduct())) {
                product.increaseQuantity(c.getQuantity());
                iterator.remove();
            }
        }
    }

    public Order checkout() {
        Order order = new Order();

        for(CartItem c : items) {
            OrderItem item = new OrderItem(c.getProduct(), c.getQuantity(), c.getProduct().getPrice());
            order.addItem(item);
        }

        return order;
    }


    public void showCart() {
        for(CartItem c : items) {
            System.out.print("Product: " + c.getProduct().getNameOfProduct() + " | ");
            System.out.print("Price: $" + c.getProduct().getPrice() + " | ");
            System.out.print("Quantity: " + c.getQuantity() + "\n");
        }

        System.out.println("---------------------------------------------------------------");

        System.out.println("Total price: $" + getTotal());
    }

    public double getTotal() {
        double totalSum = 0;

        for (CartItem c : items) {
            totalSum += (c.getProduct().getPrice() * c.getQuantity());
        }

        return totalSum;
    }
}
