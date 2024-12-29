package com.sastaa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sastaa.dto.CartItemRequest;
import com.sastaa.model.Cart;
import com.sastaa.model.CartItem;
import com.sastaa.service.impl.CartServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartServiceImpl cartService;

    @PostMapping("/{cartId}/add")
    public ResponseEntity<String> addToCart(@PathVariable Long cartId, @RequestBody CartItemRequest cartItemRequest) {
        cartService.addToCart(cartId, cartItemRequest);
        return ResponseEntity.ok("Item added to cart successfully!");
    }

    @DeleteMapping("/{cartId}/remove/{itemId}")
    public ResponseEntity<String> removeFromCart(@PathVariable Long cartId, @PathVariable Long itemId) {
        cartService.removeFromCart(cartId, itemId);
        return ResponseEntity.ok("Item removed from cart successfully!");
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> viewCart(@PathVariable Long cartId) {
        return ResponseEntity.ok(cartService.viewCart(cartId));
    }

    @GetMapping("/{cartId}/items")
    public ResponseEntity<List<CartItem>> listCartItems(@PathVariable Long cartId) {
        return ResponseEntity.ok(cartService.listCartItems(cartId));
    }
}
