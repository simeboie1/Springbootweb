package com.example.springbootwebproject.controller;

import com.example.springbootwebproject.model.Customer;
import com.example.springbootwebproject.model.Product;
import com.example.springbootwebproject.model.SaleProduct;
import com.example.springbootwebproject.model.Sales;
import com.example.springbootwebproject.repository.CustomerRepository;
import com.example.springbootwebproject.repository.ProductRepository;
import com.example.springbootwebproject.repository.SalesRepository;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SalesController {
    
    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/createSale")
    public String getCreateSalesForm(Model model) {
        model.addAttribute("sales", new Sales());
        model.addAttribute("customers", customerRepository.findAll());
        model.addAttribute("products", productRepository.findAll());
        return "createSale";
    }

    @PostMapping("/createSale")
    public String createSale(@RequestParam Long customer, @RequestParam List<Long> productIds, @RequestParam List<Integer> quantities, Model model) {
        Customer foundCustomer = customerRepository.findById(customer).orElse(null);
        if( foundCustomer == null){
            
        }
        
        Sales sale = new Sales();
        sale.setCustomer(foundCustomer);
        sale.setSaleDate(LocalDateTime.now());
        
        List<SaleProduct> saleProducts = new ArrayList<>();
        double totalPrice = 0.0;
        for(int i = 0; i < productIds.size(); i++){
            int qty = quantities.get(i);
            if(qty > 0){
                Product product = productRepository.findById(productIds.get(i)).orElse(null);
                if(product == null || product.getStockQuantity() < qty){

                } else {
                    SaleProduct sp = new SaleProduct();
                    sp.setSale(sale);
                    sp.setProduct(product);
                    sp.setQuantity(qty);
                    saleProducts.add(sp);
                    totalPrice += product.getPrice() * qty;
                    product.setStockQuantity(product.getStockQuantity() - qty);
                    productRepository.save(product);
                }
            }
        }

        sale.setSaleProducts(saleProducts);
        sale.setTotalPrice(totalPrice);
        salesRepository.save(sale);



        return "redirect:/?load=sales";
    }

    @GetMapping("/sales/{id}")
    public String getCustomerDetails(@PathVariable Long id, Model model) {
        Sales sales = salesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Sales ID: " + id));
        model.addAttribute("sales", sales);
        return null; //"sales-details";
    }

    
    @GetMapping("/sales")
    public String getSales(Model model) {
        model.addAttribute("sales", salesRepository.findAll());
        return "sales"; 
    }
    

}
