package com.sastaa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sastaa.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
