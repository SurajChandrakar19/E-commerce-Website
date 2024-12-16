package com.sastaa.service;

import java.util.List;
import java.util.Optional;

import com.sastaa.model.Order;

public interface OrderService {

    // Create or update an order
    Order saveOrder(Order order);

    // Get all orders
    List<Order> getAllOrders();

    // Get orders by user ID
    List<Order> getOrdersByUserId(Long userId);

    // Get order by ID
    Optional<Order> getOrderById(Long id);

    // Delete an order
    void deleteOrder(Long id);
}