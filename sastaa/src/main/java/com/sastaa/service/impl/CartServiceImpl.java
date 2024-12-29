package com.sastaa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sastaa.dto.CartItemRequest;
import com.sastaa.model.Cart;
import com.sastaa.model.CartItem;
import com.sastaa.repository.CartItemRepository;
import com.sastaa.repository.CartRepository;
import com.sastaa.service.CartService;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Optional<Cart> getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    public CartItem addItemToCart(Long cartId, CartItem cartItem) {
        cartItem.setCartId(cartId);
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void removeItemFromCart(Long cartId, Long productId) {
        cartItemRepository.deleteByCartIdAndProductId(cartId, productId);
    }

    @Override
    public List<CartItem> getCartItems(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }

    @Override
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public void addToCart(Long cartId, CartItemRequest cartItemRequest) {
        // Fetch the cart by ID
        Optional<Cart> optionalCart = cartRepository.findById(cartId);

        Cart cart;
        if (optionalCart.isPresent()) {
            cart = optionalCart.get();
        } else {
            // Create a new cart if it doesn't exist
            cart = new Cart();
            cart.setId(cartId);
            cart = cartRepository.save(cart);
        }

        // Check if the item already exists in the cart
        Optional<CartItem> existingItem = cartItemRepository.findByCartIdAndProductId(cartId, cartItemRequest.getProductId());
        if (existingItem.isPresent()) {
            // Update the quantity of the existing item
            CartItem cartItem = existingItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + cartItemRequest.getQuantity());
            cartItemRepository.save(cartItem);
        } else {
            // Add a new item to the cart
            CartItem cartItem = new CartItem();
            cartItem.setCartId(cartId);
            cartItem.setProductId(cartItemRequest.getProductId());
            cartItem.setQuantity(cartItemRequest.getQuantity());
            cartItemRepository.save(cartItem);
        }
    }
    
    @Override
    public void removeFromCart(Long cartId, Long itemId) {
        Optional<CartItem> cartItem = cartItemRepository.findById(itemId);
        if (cartItem.isPresent() && cartItem.get().getCartId().equals(cartId)) {
            cartItemRepository.deleteById(itemId);
        } else {
            throw new IllegalArgumentException("CartItem not found or does not belong to the cart.");
        }
    }

    

    @Override
    public Cart viewCart(Long cartId) {
        return cartRepository.findById(cartId).orElseThrow(() -> new IllegalArgumentException("Cart not found"));
    }

    @Override
    public List<CartItem> listCartItems(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }
}
