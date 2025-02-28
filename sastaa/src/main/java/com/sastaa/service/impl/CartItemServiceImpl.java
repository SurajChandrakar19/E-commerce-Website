package com.sastaa.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sastaa.model.Cart;
import com.sastaa.model.CartItem;
import com.sastaa.repository.CartItemRepository;
import com.sastaa.repository.CartRepository;
import com.sastaa.service.CartItemService;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private CartRepository cartRepository;

    @Autowired
    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public CartItem addItemToCart(Long cartId, CartItem cartItem) {
        // Check if the cart exists
        Cart cart = cartRepository.findById(cartId)
                                   .orElseThrow(() -> new IllegalArgumentException("Cart not found with ID: " + cartId));

        // Associate the cart with the cart item
        cartItem.setCart(cart);

        // Save the cart item to the database
        return cartItemRepository.save(cartItem);
    }


    @Override
    public void removeItemFromCart(Long cartId, Long productId) {
        cartItemRepository.deleteByCartIdAndProductId(cartId, productId);
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartId, Long productId, int quantity) {
        // Fetch the cart item using the repository
        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cartId, productId)
            .orElseThrow(() -> new RuntimeException("CartItem not found for cartId: " + cartId + ", productId: " + productId));
        
        // Update the quantity
        cartItem.setQuantity(quantity);
        
        // Save and return the updated cart item
        return cartItemRepository.save(cartItem);
    }

    @Override
    public List<CartItem> getCartItems(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }

    @Override
    public void deleteCartItem(Long cartId, Long productId) {
        cartItemRepository.deleteByCartIdAndProductId(cartId, productId);
    }
}
