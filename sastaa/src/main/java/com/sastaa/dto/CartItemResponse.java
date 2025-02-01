package com.sastaa.dto;

import com.sastaa.model.CartItem;

public class CartItemResponse {
    private Long productId;
    private int quantity;

    public CartItemResponse(CartItem cartItem) {
        this.productId = cartItem.getProductId();
        this.quantity = cartItem.getQuantity();
    }

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

}
