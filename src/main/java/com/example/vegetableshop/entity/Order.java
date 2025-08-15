package com.example.vegetableshop.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name="orders")
@Data @NoArgsConstructor @AllArgsConstructor
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private Double totalAmount;
    private String paymentStatus; // PENDING, PAID, FAILED
    private String orderStatus; // PROCESSING, SHIPPED, DELIVERED
    private Instant createdAt = Instant.now();

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> items;
}
