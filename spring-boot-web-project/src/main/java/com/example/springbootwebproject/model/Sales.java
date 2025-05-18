package com.example.springbootwebproject.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Sales {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany
    @JoinTable(
        name = "sales_product",
        joinColumns = @JoinColumn(name = "sales_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<SaleProduct> products;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleProduct> saleProducts = new ArrayList<>();
    
    private LocalDateTime saleDate;
    private double totalPrice;

    public Sales() {
    }

    public Sales(Customer customer, List<SaleProduct> products, LocalDateTime saleDate, double totalPrice) {
        this.customer = customer;
        this.products = products;
        this.saleDate = saleDate;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public List<SaleProduct> getProducts() {
        return products;
    }
    public void setProducts(List<SaleProduct> products) {
        this.products = products;
    }
    public LocalDateTime getSaleDate() {
        return saleDate;
    }
    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<SaleProduct> getSaleProducts() {
        return saleProducts;
    }

    public void setSaleProducts(List<SaleProduct> saleProducts) {
        this.saleProducts = saleProducts;
    }
}
