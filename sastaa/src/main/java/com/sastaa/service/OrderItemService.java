package com.sastaa.service;

import java.util.List;

import com.sastaa.model.OrderItem;

public interface OrderItemService {

    // Create or update an order item
    OrderItem saveOrderItem(OrderItem orderItem);

    // Get all items for a specific order
    List<OrderItem> getOrderItemsByOrderId(Long orderId);

    // Get order item by ID
    OrderItem getOrderItemById(Long id);

    // Delete an order item
    void deleteOrderItem(Long id);
}
