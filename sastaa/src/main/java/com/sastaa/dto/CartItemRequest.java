package com.sastaa.dto;


public class CartItemRequest {

    private Long productId;  // ID of the product to be added to the cart
    private int quantity;    // Quantity of the product to be added

    // Default constructor
    public CartItemRequest() {
    }

    // Parameterized constructor
    public CartItemRequest(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    // Getters and setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItemRequest{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
