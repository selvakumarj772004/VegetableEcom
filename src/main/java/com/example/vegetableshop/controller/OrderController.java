package com.example.vegetableshop.controller;

import com.example.vegetableshop.entity.*;
import com.example.vegetableshop.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor

public class OrderController {
    private final UserRepository userRepo;
    private final CartItemRepository cartRepo;
    private final OrderRepository orderRepo;
    private final ProductRepository productRepo;
    private final CartItemRepository cartItemRepo;

    @PostMapping("/place")
    public Order placeOrder(@RequestBody(required = false) String address, Principal principal) {
        var user = userRepo.findByEmail(principal.getName()).orElseThrow();
        List<CartItem> cart = cartRepo.findByUserId(user.getId());

        List<OrderItem> items = cart.stream().map(ci -> {
            OrderItem oi = new OrderItem();
            oi.setProduct(ci.getProduct());
            oi.setQuantity(ci.getQuantity());
            oi.setPrice(ci.getProduct().getPrice());
            return oi;
        }).collect(Collectors.toList());

        double total = items.stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity()).sum();

        Order order = new Order();
        order.setUser(user);
        order.setItems(items);
        order.setTotalAmount(total);
        order.setPaymentStatus("PENDING");
        order.setOrderStatus("PROCESSING");
        Order saved = orderRepo.save(order);

        // optionally clear cart
        cartItemRepo.deleteByUserId(user.getId());
        return saved;
    }

    @GetMapping
    public List<Order> myOrders(Principal principal) {
        var user = userRepo.findByEmail(principal.getName()).orElseThrow();
        return orderRepo.findByUserId(user.getId());
    }
}
