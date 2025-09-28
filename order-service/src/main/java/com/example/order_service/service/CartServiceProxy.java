package com.example.order_service.service;

import com.example.order_service.model.CartItemResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "cart-service")
public interface CartServiceProxy {

    @GetMapping("/cart/cart-items")
    List<CartItemResponse> getCartItems(@RequestHeader("X-ID") int userId);

    @DeleteMapping("/cart/clear-cart")
    ResponseEntity<Void> clearCart(@RequestHeader("X-ID") int userId);
}
