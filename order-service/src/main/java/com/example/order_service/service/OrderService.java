package com.example.order_service.service;

import com.example.order_service.dao.OrderRepo;
import com.example.order_service.entity.Order;
import com.example.order_service.entity.OrderItem;
import com.example.order_service.exceptions.OrderNotFoundException;
import com.example.order_service.model.CartItemResponse;
import com.example.order_service.model.OrderInfoRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepo orderRepo;

    private final OrderItemService orderItemService;

    public OrderService(OrderRepo orderRepo, OrderItemService orderItemService) {
        this.orderRepo = orderRepo;
        this.orderItemService = orderItemService;
    }

    @Transactional
    public Order save(int userId, List<CartItemResponse> cartItems, OrderInfoRequest request) {
        System.out.println("SAVE METHOD INVOKED");
        int total = 0;
        Order order = new Order();
        order.setId(generateOrderNumber());
        order.setUserId(userId);
        order.setAddress(request.address());
        order.setFirstName(request.firstName());
        order.setLastName(request.lastName());
        order.setCreatedAt(LocalDateTime.now());
        System.out.println("ORDER FIELDS SET ENTERING FOR LOOP");
        for (CartItemResponse cartItem : cartItems) {
            System.out.println(cartItem.name());
            System.out.println(cartItem.price());
            OrderItem orderItem = new OrderItem(cartItem.name(), cartItem.price());
            total += orderItem.getPrice();
            order.addOrderItem(orderItem);
        }
        order.setTotal(total);
        return orderRepo.save(order);
    }

    @Transactional
    public void delete(Order order) {
        orderRepo.delete(order);
    }

    public List<Order> findAll() {
        return orderRepo.findAll();
    }

    public List<Order> findByUserId(int userId) {
        return orderRepo.findByUserId(userId);
    }

    public Order findByOrderId(String orderId) {
        return orderRepo.findByOrderId(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order with id: " + orderId + " not found!"));
    }

    private String generateOrderNumber() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "").substring(0, 13);
    }
}
