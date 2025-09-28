package com.example.product_service;

import com.example.product_service.entity.Product;
import com.example.product_service.repo.ProductRepo;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner clr(ProductRepo productRepo) {
        return args -> {
            Random rand = new Random();
            Faker faker = new Faker();
            for (int i = 0; i < 30; i++) {
                String name = faker.commerce().productName();
                int stock = rand.nextInt(25);
                int price = rand.nextInt(120);
                Product product = new Product(name, stock, price);
                productRepo.save(product);
            }
        };
    }

}
