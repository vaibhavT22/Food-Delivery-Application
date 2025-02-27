package com.vt.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Integer, CartItem> items;

    // Constructor to initialize the cart items map
    public Cart() {
        this.items = new HashMap<>();
    }

    // Add an item to the cart, or update the quantity if it already exists
    public void addCartItem(CartItem item) {
        int id = item.getId();
        if (items.containsKey(id)) {
            CartItem existingItem = items.get(id);
            existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
        } else {
            items.put(id, item);
        }
    }

    // Update the quantity of an item in the cart
    public void updateCartItem(int id, int quantity) {
        if (items.containsKey(id)) {
            if (quantity <= 0) {
                items.remove(id);  // If quantity is 0 or negative, remove the item
            } else {
                items.get(id).setQuantity(quantity);  // Update the quantity of the item
            }
        }
    }

    // Remove an item from the cart
    public void removeCartItem(int id) {
        items.remove(id);
    }

    // Calculate the total price of the cart
    public double getTotalPrice() {
        return items.values().stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    // Clear all items in the cart
    public void clear() {
        items.clear();
    }

    // Get the map of cart items (optional, for inspection or modification)
    public Map<Integer, CartItem> getItems() {
        return items;
    }
}
