package com.sastaa.controller;

import org.springframework.web.bind.annotation.*;

import com.sastaa.model.Order;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody Order orderRequest) {
        // Logic to place an order
        return ResponseEntity.ok("Order placed successfully!");
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<String> getOrderById(@PathVariable Long orderId) {
        // Logic to fetch order details by ID
        return ResponseEntity.ok("Order details fetched!");
    }

    @GetMapping
    public ResponseEntity<String> getAllOrders() {
        // Logic to fetch all orders
        return ResponseEntity.ok("All orders fetched!");
    }
}
