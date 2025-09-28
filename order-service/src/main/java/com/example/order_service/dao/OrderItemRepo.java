package com.example.order_service.dao;

import com.example.order_service.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemRepo extends JpaRepository<OrderItem, Integer> {

    @Query("SELECT i FROM OrderItem i WHERE i.order.id=:orderId")
    List<OrderItem> findByOrderId(@Param("orderId") String orderId);
}
