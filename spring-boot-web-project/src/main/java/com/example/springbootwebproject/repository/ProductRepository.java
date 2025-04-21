package com.example.springbootwebproject.repository;

import com.example.springbootwebproject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
