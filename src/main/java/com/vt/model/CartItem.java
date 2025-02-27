package com.vt.model;

public class CartItem {
    private int id;
    private int restaurantId;
    private String name;
    private double price;
    private int quantity;

    // Constructor to initialize the cart item
    public CartItem(int id, int restaurantId, String name, int quantity, double price) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    // Default constructor
    public CartItem() {
        // Default constructor if needed
    }

    // Getter and setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }

    // Calculate total price dynamically based on quantity and price
    public double getTotalPrice() {
        return price * quantity;
    }
}
