package com.example.product_service.controller;

import com.example.product_service.entity.Product;
import com.example.product_service.repo.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> findAllProducts() {
        List<Product> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable int id) {
        log.info("Finding product by id: {}", id);
        Product product = productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Product> findProductByName(@PathVariable String name) {
        log.info("Finding product by name: {}", name);
        Product product = productService.findByName(name);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/lower-stock/{name}")
    public ResponseEntity<Void> lowerProductStock(@PathVariable String name) {
        log.info("Lowering product stock: {}", name);
        Product product = productService.findByName(name);
        if (productService.decrementStock(product)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/restore-stock/{name}")
    public ResponseEntity<Void> restoreProductStock(@PathVariable String name) {
        log.info("Restoring product stock: {}", name);
        Product product = productService.findByName(name);
        productService.restoreStock(product);
        return ResponseEntity.ok().build();
    }

}
