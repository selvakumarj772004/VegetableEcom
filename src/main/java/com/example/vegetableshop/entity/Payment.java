package com.example.vegetableshop.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Payment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Order order;

    private Double amount;
    private String paymentMethod;
    private String transactionId;
    private String paymentStatus; // SUCCESS/FAILED/PENDING
    private Instant createdAt = Instant.now();
}
