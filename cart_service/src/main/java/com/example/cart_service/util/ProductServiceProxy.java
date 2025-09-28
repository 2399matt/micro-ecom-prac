package com.example.cart_service.util;

import com.example.cart_service.entity.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "product-service")
public interface ProductServiceProxy {

    @GetMapping("/product/{id}")
    ProductResponse findProductById(@PathVariable int id);

    @GetMapping("/product/name/{name}")
    ProductResponse findProductByName(@PathVariable String name);

    @PutMapping("/product/lower-stock/{name}")
    ResponseEntity<Void> lowerProductStock(@PathVariable String name);

    @PutMapping("/product/restore-stock/{name}")
    ResponseEntity<Void> restoreProductStock(@PathVariable String name);

}
