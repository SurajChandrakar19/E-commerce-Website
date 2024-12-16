package com.sastaa.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sastaa.model.CartItem;
import com.sastaa.repository.CartItemRepository;
import com.sastaa.service.CartItemService;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
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
    public CartItem updateCartItemQuantity(Long cartId, Long productId, int quantity) {
    	CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cartId, productId);
    		    //.orElseThrow(() -> new CartItemNotFoundException("CartItem not found for cartId: " + cartId + ", productId: " + productId));
        cartItem.setQuantity(quantity);
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
