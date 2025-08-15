package com.example.vegetableshop.controller;

import com.example.vegetableshop.entity.*;
import com.example.vegetableshop.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class CartController {

    private final CartItemRepository cartRepo;
    private final UserRepository userRepo;
    private final ProductRepository productRepo;

    @PostMapping("/add")
    public CartItem addToCart(@RequestBody CartItem item) {
        var user = userRepo.findById(item.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        var product = productRepo.findById(item.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        item.setUser(user);
        item.setProduct(product);
        return cartRepo.save(item);
    }

    @GetMapping
    public List<CartItem> getCart(@RequestParam Long userId) {
        return cartRepo.findByUserId(userId);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        cartRepo.deleteById(id);
    }

    @DeleteMapping("/clear/{userId}")
    public void clearCart(@PathVariable Long userId) {
        cartRepo.deleteByUserId(userId);
    }

}
