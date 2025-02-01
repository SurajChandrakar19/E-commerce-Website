package com.sastaa.dto;

import com.sastaa.model.Cart;
import java.util.List;

public class CartResponse {
    private Long cartId;
    private Long userId;
    private List<CartItemResponse> items;

    public CartResponse(Cart cart) {
        this.cartId = cart.getId();
        this.items = cart.getCartItems()
                         .stream()
                         .map(CartItemResponse::new)
                         .toList();
    }

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<CartItemResponse> getItems() {
		return items;
	}

	public void setItems(List<CartItemResponse> items) {
		this.items = items;
	}

}
