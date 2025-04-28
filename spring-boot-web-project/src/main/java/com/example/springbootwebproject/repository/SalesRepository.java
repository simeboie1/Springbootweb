package com.example.springbootwebproject.repository;

import com.example.springbootwebproject.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Customer, Long> {
}
