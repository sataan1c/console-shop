package org.example;

public class Product {
    private int id;
    private String nameOfProduct;
    private double price;
    private String category;
    private int quantity;

    public Product(int id, String nameOfProduct, double price, String category, int quantity) {
        this.id = id;
        this.nameOfProduct = nameOfProduct;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public String getNameOfProduct() {
        return nameOfProduct;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getId() {
        return id;
    }

    public void decreaseQuantity() {
        if(this.quantity > 0) {
            --quantity;
        }
    }

    public void increaseQuantity(int quantity) {
        if(quantity > 0) {
            this.quantity += quantity;
        }
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;

        if(o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return id + ". " + nameOfProduct +
                " | $" + price +
                " | Category: " + category +
                " | In stock: " + quantity;
    }

}
