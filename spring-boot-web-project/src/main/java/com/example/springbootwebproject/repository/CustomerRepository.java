package com.example.springbootwebproject.repository;

import com.example.springbootwebproject.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
