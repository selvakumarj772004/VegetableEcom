package com.example.vegetableshop.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.example.vegetableshop.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long id);
}
