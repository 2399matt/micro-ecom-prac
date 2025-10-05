package com.example.order_service.controller;

import com.example.order_service.entity.Order;
import com.example.order_service.exceptions.EmptyCartException;
import com.example.order_service.model.CartItemResponse;
import com.example.order_service.model.OrderInfoRequest;
import com.example.order_service.service.CartServiceProxy;
import com.example.order_service.service.OrderService;
import feign.FeignException;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/order")
public class OrderController {

    private final Logger log = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;
    private final CartServiceProxy proxy;
    @Value("${external-url}")
    private String externalUrl;

    public OrderController(OrderService orderService, CartServiceProxy proxy) {
        this.orderService = orderService;
        this.proxy = proxy;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable String id) {
        log.info("Finding order by id {}", id);
        Order order = orderService.findByOrderId(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Order>> findUserOrders(@RequestHeader("X-ID") int userId) {
        List<Order> orders = orderService.findByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    // validation error being handled.
    @Retry(name = "checkout-api", fallbackMethod = "fallBack")
    @Bulkhead(name = "checkout-api", fallbackMethod = "fallBack")
    @PostMapping("/checkout")
    public ResponseEntity<?> createOrder(@RequestHeader("X-ID") int userId, @RequestBody @Valid OrderInfoRequest request) {
        try {
            log.info("Checking out order for user id: {}", userId);
            List<CartItemResponse> cartItems = proxy.getCartItems(userId);
            if(cartItems.isEmpty()){
                log.error("No cartItems found for user id: {}", userId);
                throw new EmptyCartException("Cart cannot be empty!");
            }
            Order order = orderService.save(userId, cartItems, request);
            // TODO message/event to trigger payment (stripe) call... listen for success, send notification
            proxy.clearCart(userId);
            URI location = URI.create(externalUrl + "/order/" + order.getId());
            return ResponseEntity.created(location).build();
        } catch (FeignException.NotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart not found");
        } catch (FeignException ex) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
    }

    public ResponseEntity<String> fallBack(@RequestHeader("X-ID") int userId, @RequestBody @Valid OrderInfoRequest request, Exception ex) {
        log.warn("Fallback method invoked");
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("An error has occured! Please attempt checkout again in a few moments.");
    }

}
