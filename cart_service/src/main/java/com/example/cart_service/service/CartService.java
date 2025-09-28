package com.example.cart_service.service;

import com.example.cart_service.entity.Cart;
import com.example.cart_service.entity.CartItem;
import com.example.cart_service.repo.CartRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class CartService {

    private final CartRepo cartRepo;
    private final CartItemService cartItemService;

    public CartService(CartRepo cartRepo, CartItemService cartItemService) {
        this.cartRepo = cartRepo;
        this.cartItemService = cartItemService;
    }

    @Transactional
    public Cart save(Cart cart) {
        return cartRepo.save(cart);
    }

    @Transactional
    public Cart findByUserId(int userId) {
        Cart cart = cartRepo.findByUserId(userId);
        if (cart == null) {
            cart = new Cart(userId);
            cart.setCartItems(new ArrayList<>());
            return cartRepo.save(cart);
        }
        return cart;
    }

    public boolean checkOwner(int userId, int cartItemId) {
        Cart cart = findByUserId(userId);
        return cart.getCartItems().stream().anyMatch(cartItem -> cartItem.getId() == cartItemId);
    }

    @Transactional
    public void clearCart(int userId) {
        Cart cart = findByUserId(userId);
        for (CartItem cartItem : cart.getCartItems()) {
            cartItemService.delete(cartItem.getId());
        }
    }
}
