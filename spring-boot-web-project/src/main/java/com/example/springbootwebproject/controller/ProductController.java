package com.example.springbootwebproject.controller;

import com.example.springbootwebproject.model.Product;
import com.example.springbootwebproject.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class ProductController {
    
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public String getProductsForm(Model model) {
        model.addAttribute("product", new Product());
        return "products";
    }

    @PostMapping("/products")
    public String createProduct(@ModelAttribute Product product, Model model) {
        productRepository.save(product);
        model.addAttribute("products", productRepository.findAll());
        return "redirect:/?load=products"; // Redirect to the inventory page after creating a product
    }

    @GetMapping("/products/{id}")
    public String getProductDetails(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + id));
        model.addAttribute("product", product);
        return "product-details";
    }

    @GetMapping("/inventory")
    public String getProducts(Model model) {
        model.addAttribute("products", productRepository.findAll()); // Fetch all products
        return "inventory"; // Render the dashboard.html template
    }

}
