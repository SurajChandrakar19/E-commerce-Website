package com.sastaa.service;

import java.util.Optional;

import com.sastaa.model.User;

public interface UserService {

    // Create or update a user
    User saveUser(User user);

    // Get user by ID
    Optional<User> getUserById(Long id);

    // Get user by email
    Optional<User> getUserByEmail(String email);

    // Delete a user
    void deleteUser(Long id);
}