package com.sastaa.service;

import java.util.List;
import java.util.Optional;

import com.sastaa.model.Product;

public interface ProductService {

    // Create or update a product
    Product saveProduct(Product product);

    // Get all products
    List<Product> getAllProducts();

    // Get product by ID
    Optional<Product> getProductById(Long id);

    // Get products by category
    List<Product> getProductsByCategory(Long categoryId);

    // Delete a product
    void deleteProduct(Long id);
}