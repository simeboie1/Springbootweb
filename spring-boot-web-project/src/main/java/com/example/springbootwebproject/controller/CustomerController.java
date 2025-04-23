package com.example.springbootwebproject.controller;

import com.example.springbootwebproject.model.Customer;
import com.example.springbootwebproject.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class CustomerController {
    
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/createCustomer")
    public String getCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "createCustomer";
    }

    @PostMapping("/createCustomer")
    public String createCustomer(@ModelAttribute Customer customer) {
        customerRepository.save(customer);
        return "redirect:/?load=customer";
    }

    @GetMapping("/customer/{id}")
    public String getCustomerDetails(@PathVariable Long id, Model model) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Customer ID: " + id));
        model.addAttribute("customer", customer);
        return "customer-details";
    }

    
    @GetMapping("/customer")
    public String getDashboard(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "customer"; 
    }
    

}
