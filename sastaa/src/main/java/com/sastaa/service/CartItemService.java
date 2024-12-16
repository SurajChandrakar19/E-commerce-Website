package com.sastaa.service;

import java.util.List;

import com.sastaa.model.CartItem;

public interface CartItemService {

    // Add item to the cart
    CartItem addItemToCart(Long cartId, CartItem cartItem);

    // Remove item from the cart
    void removeItemFromCart(Long cartId, Long productId);

    // Update the quantity of an item in the cart
    CartItem updateCartItemQuantity(Long cartId, Long productId, int quantity);

    // Get all items in the cart
    List<CartItem> getCartItems(Long cartId);

    // Delete cart item
    void deleteCartItem(Long cartId, Long productId);
}
