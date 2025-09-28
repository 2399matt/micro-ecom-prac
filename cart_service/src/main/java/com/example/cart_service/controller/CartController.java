package com.example.cart_service.controller;

import com.example.cart_service.entity.Cart;
import com.example.cart_service.entity.CartItem;
import com.example.cart_service.entity.ProductResponse;
import com.example.cart_service.service.CartItemService;
import com.example.cart_service.service.CartService;
import com.example.cart_service.util.ProductServiceProxy;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    private final CartItemService cartItemService;

    private final ProductServiceProxy proxy;

    public CartController(CartService cartService, CartItemService cartItemService, ProductServiceProxy proxy) {
        this.cartService = cartService;
        this.cartItemService = cartItemService;
        this.proxy = proxy;
    }


    @GetMapping()
    public ResponseEntity<Cart> findCartForUser(@RequestHeader("X-ID") int userId) {
        Cart cart = cartService.findByUserId(userId);
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/add/{id}")
    public ResponseEntity<Void> addItemToCart(@RequestHeader("X-ID") int userId, @PathVariable("id") int productId) {
        Cart cart = cartService.findByUserId(userId);
        try {
            ProductResponse product = proxy.findProductById(productId);
            proxy.lowerProductStock(product.name());
            cartItemService.createCartItem(product, cart);
            return ResponseEntity.ok().build();
        } catch (FeignException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Void> removeItemFromCart(@RequestHeader("X-ID") int userId, @PathVariable("id") int cartItemId) {
        if (cartService.checkOwner(userId, cartItemId)) {
            cartItemService.delete(cartItemId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @DeleteMapping("/clear-cart")
    public ResponseEntity<Void> clearCart(@RequestHeader("X-ID") int userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cart-items")
    public ResponseEntity<List<CartItem>> getCartItems(@RequestHeader("X-ID") int userId) {
        System.out.println("REQUEST FROM ORDER SERVICE ID: " + userId);
        Cart cart = cartService.findByUserId(userId);
        return ResponseEntity.ok(cart.getCartItems());
    }

}
