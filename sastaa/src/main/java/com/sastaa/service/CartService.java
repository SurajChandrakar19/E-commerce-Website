package com.sastaa.service;

import java.util.List;

import java.util.Optional;

import com.sastaa.dto.CartItemRequest;
import com.sastaa.model.Cart;
import com.sastaa.model.CartItem;

public interface CartService {

    // Create or update a cart
    Cart saveCart(Cart cart);

    // Get cart by user ID
    Optional<Cart> getCartByUserId(Long userId);

    // Add item to the cart
    CartItem addItemToCart(Long cartId, CartItem cartItem);

    // Remove item from the cart
    void removeItemFromCart(Long cartId, Long productId);

    // Get all items in the cart
    List<CartItem> getCartItems(Long cartId);

    // Delete a cart
    void deleteCart(Long id);
    
    void addToCart(Long id, CartItemRequest cartItemRequest);

	void removeFromCart(Long cartId, Long itemId);

	Cart viewCart(Long cartId);

	List<CartItem> listCartItems(Long cartId);
}
