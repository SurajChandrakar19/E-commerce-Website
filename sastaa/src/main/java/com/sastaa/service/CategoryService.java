package com.sastaa.service;

import java.util.List;

import com.sastaa.model.Category;

public interface CategoryService {

    // Create or update a category
    Category saveCategory(Category category);

    // Get all categories
    List<Category> getAllCategories();

    // Get category by ID
    Category getCategoryById(Long id);

    // Delete category
    void deleteCategory(Long id);
}
