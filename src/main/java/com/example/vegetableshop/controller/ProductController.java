package com.example.vegetableshop.controller;

import com.example.vegetableshop.model.Product;
import com.example.vegetableshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {
    @Autowired
    ProductService service;

    @GetMapping("api/products")
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @PostMapping("api/products")
    public Product addProduct(@RequestBody Product product) {
        return service.addProduct(product);
    }
}
