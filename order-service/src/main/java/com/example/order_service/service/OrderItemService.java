package com.example.order_service.service;

import com.example.order_service.dao.OrderItemRepo;
import com.example.order_service.entity.OrderItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemRepo orderItemRepo;

    public OrderItemService(OrderItemRepo orderItemRepo) {
        this.orderItemRepo = orderItemRepo;
    }

    @Transactional
    public void save(OrderItem orderItem) {
        orderItemRepo.save(orderItem);
    }

    @Transactional
    public void delete(OrderItem orderItem) {
        orderItemRepo.delete(orderItem);
    }

    public List<OrderItem> findByOrderId(String orderId) {
        return orderItemRepo.findByOrderId(orderId);
    }
}
