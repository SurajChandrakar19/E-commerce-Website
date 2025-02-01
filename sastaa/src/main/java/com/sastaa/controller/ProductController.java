package com.sastaa.controller;

import org.springframework.web.bind.annotation.*;

import com.sastaa.model.Product;
import com.sastaa.service.impl.ProductServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductServiceImpl productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody Product productRequest) {
    	productService.saveProduct(productRequest);
        return ResponseEntity.ok("Product created successfully!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product productRequest) {
    	if(productService.updateProduct(id, productRequest)) {
    		return ResponseEntity.ok("Product updated successfully!");
    	}
    	return ResponseEntity.ok("Product update faild");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
    	productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully!");
    }
}
