package com.example.vegetableshop.controller;

import com.example.vegetableshop.entity.Product;
import com.example.vegetableshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RequestMapping("/api/products")
@RequiredArgsConstructor

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {
    private final ProductRepository productRepo;

    @GetMapping
    public List<Product> listAllProducts() {
        return productRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productRepo.findById(id);
    }

    @PostMapping // Admin only
    public Product createProduct(@RequestBody Product product) {
        return productRepo.save(product);
    }

    @PutMapping("/{id}") // Admin only
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        updatedProduct.setId(id);
        return productRepo.save(updatedProduct);
    }

    @DeleteMapping("/{id}") // Admin only
    public void deleteProduct(@PathVariable Long id) {
        productRepo.deleteById(id);
    }
}
