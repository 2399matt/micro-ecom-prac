package com.example.product_service.repo;

import com.example.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Integer> {

    Optional<Product> findByName(String name);
}
