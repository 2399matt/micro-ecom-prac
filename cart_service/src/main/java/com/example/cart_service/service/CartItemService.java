package com.example.cart_service.service;

import com.example.cart_service.entity.Cart;
import com.example.cart_service.entity.CartItem;
import com.example.cart_service.entity.ProductResponse;
import com.example.cart_service.repo.CartItemRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CartItemService {

    private final CartItemRepo cartItemRepo;

    public CartItemService(CartItemRepo cartItemRepo) {
        this.cartItemRepo = cartItemRepo;
    }

    public CartItem findById(int id) {
        return cartItemRepo.findById(id)
                .orElseThrow(() -> new CartItemNotFoundException("CartItem with id: " + id + " not found!"));
    }

    @Transactional
    public CartItem save(CartItem cartItem) {
        return cartItemRepo.save(cartItem);
    }

    @Transactional
    public void delete(int cartItemId) {
        CartItem cartItem = findById(cartItemId);
        cartItemRepo.delete(cartItem);
    }

    @Transactional
    public void createCartItem(ProductResponse product, Cart cart) {
        CartItem cartItem = new CartItem();
        cartItem.setName(product.name());
        cartItem.setPrice(product.price());
        cartItem.setCart(cart);
        cartItemRepo.save(cartItem);
    }
}
