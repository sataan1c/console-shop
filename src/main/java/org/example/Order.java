package org.example;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderItem> items;

    public Order() {
        items = new ArrayList<>();
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public void showOrder() {
        for(OrderItem item : items) {
            System.out.println(item);
        }

        System.out.println("Total price: " + getTotal());
    }

    public double getTotal() {
        double totalPrice = 0;
        for(OrderItem item : items) {
            totalPrice += item.getTotal();
        }

        return totalPrice;
    }
}
