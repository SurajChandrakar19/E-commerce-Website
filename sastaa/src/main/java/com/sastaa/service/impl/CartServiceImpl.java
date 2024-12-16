package com.sastaa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
