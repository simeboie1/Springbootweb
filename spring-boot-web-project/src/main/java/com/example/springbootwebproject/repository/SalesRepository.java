package com.example.springbootwebproject.repository;

import com.example.springbootwebproject.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sales, Long> {
}
