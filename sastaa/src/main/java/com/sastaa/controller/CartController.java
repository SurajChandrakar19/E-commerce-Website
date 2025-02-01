package com.sastaa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.sastaa.dto.CartItemRequest;
import com.sastaa.dto.CartResponse;
import com.sastaa.dto.CartItemResponse;
import com.sastaa.model.Cart;
import com.sastaa.model.Product;
import com.sastaa.service.impl.CartServiceImpl;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartServiceImpl cartServiceImpl;
    

    // Add item to cart
    @PostMapping("/add")
    public ResponseEntity<List<CartItemRequest>> addToCart(@RequestParam(required = false) Long itemId, HttpSession session) {
    	
    	// Retrieve cart from session or create a new one
        List<CartItemRequest> cart = (List<CartItemRequest>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        // Create a new cart item
        CartItemRequest newItem = new CartItemRequest();
        newItem.setProductId(itemId); // Assuming CartItemRequest has a setItemId method
        newItem.setQuantity(1);   // Default quantity
        // Add the item to the cart
        cart.add(newItem);

        // Save the updated cart back in the session
        session.setAttribute("cart", cart);

        // Return the updated cart with HTTP 200 OK
        return ResponseEntity.ok(cart);
    }

    private CartItemRequest getItemRequest(Long itemId) {
		
    	
		return null;
	}

	// Remove item from cart
    @DeleteMapping("/{cartId}/items/{itemId}")
    public ResponseEntity<String> removeFromCart(@PathVariable Long cartId, @PathVariable Long itemId) {
        cartServiceImpl.removeFromCart(cartId, itemId);
        return ResponseEntity.ok("Item removed from cart successfully!");
    }

    // View cart details
    @GetMapping("/{cartId}")
    public ResponseEntity<CartResponse> viewCart(@PathVariable Long cartId) {
        Cart cart = cartServiceImpl.viewCart(cartId);
        if (cart == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        CartResponse cartResponse = new CartResponse(cart); // Convert Cart to CartResponse
        return ResponseEntity.ok(cartResponse);
    }

    // List all items in a cart
    @GetMapping("/{cartId}/items")
    public ResponseEntity<List<CartItemResponse>> listCartItems(@PathVariable Long cartId) {
        List<CartItemResponse> cartItems = cartServiceImpl.listCartItems(cartId)
                                                      .stream()
                                                      .map(CartItemResponse::new) // Convert to DTO
                                                      .toList();
        if (cartItems.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(cartItems);
    }
    
    private Long generateNewCartId() {
        return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE; // Generate a positive long
    }
}
