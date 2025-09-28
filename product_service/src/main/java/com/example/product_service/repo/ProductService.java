package com.example.product_service.repo;

import com.example.product_service.entity.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Transactional
    public Product save(Product product) {
        return productRepo.save(product);
    }

    public Product findByName(String name) {
        return productRepo.findByName(name)
                .orElseThrow(() -> new ProductNotFoundException(name + " not found!"));
    }

    public List<Product> findAll() {
        return productRepo.findAll();
    }

    public Product findById(int id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id + " not found!"));
    }

    @Transactional
    public boolean decrementStock(Product product) {
        if (product.getStock() > 0) {
            product.setStock(product.getStock() - 1);
            productRepo.save(product);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public void restoreStock(Product product) {
        product.setStock(product.getStock() + 1);
        productRepo.save(product);
    }
}
