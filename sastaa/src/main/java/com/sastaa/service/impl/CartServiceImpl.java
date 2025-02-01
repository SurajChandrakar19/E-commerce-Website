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

	@Autowired
    private CartRepository cartRepository;
	@Autowired
    private CartItemRepository cartItemRepository;
    
    public CartServiceImpl() {
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
        // Check if the cart exists
        Cart cart = cartRepository.findById(cartId)
                                   .orElseThrow(() -> new IllegalArgumentException("Cart not found with ID: " + cartId));

        // Set the cart reference in the cart item
        cartItem.setCart(cart);

        // Save the cart item to the database
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
        // Find the cart by ID, or throw an exception if not found
        Cart cart = cartRepository.findById(cartId)
                                   .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

        // Check if the product already exists in the cart
        Optional<CartItem> existingItem = cart.getCartItems().stream()
                                              .filter(item -> item.getProductId().equals(cartItemRequest.getProductId()))
                                              .findFirst();

        if (existingItem.isPresent()) {
        	
            CartItem cartItem = existingItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + cartItemRequest.getQuantity());
        } else {
            // Add a new cart item
            CartItem newCartItem = new CartItem();
            newCartItem.setCart(cart); // Set the relationship
            newCartItem.setProductId(cartItemRequest.getProductId());
            newCartItem.setQuantity(cartItemRequest.getQuantity());
            cart.getCartItems().add(newCartItem); // Add the new item to the cart
        }

        // Save the cart to the repository
        cartRepository.save(cart); // Cascade should ensure CartItem is saved automatically
    }
    
    @Override
    public void removeFromCart(Long cartId, Long itemId) {
        // Find the cart item by its ID
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(itemId);

        // Check if the cart item exists and belongs to the specified cart
        if (cartItemOptional.isPresent()) {
            CartItem cartItem = cartItemOptional.get();

            // Validate that the cart item's cart ID matches the provided cart ID
            if (cartItem.getCart().getId().equals(cartId)) {
                // Remove the cart item
                cartItemRepository.deleteById(itemId);
            } else {
                throw new IllegalArgumentException("CartItem does not belong to the specified cart.");
            }
        } else {
            throw new IllegalArgumentException("CartItem not found.");
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
