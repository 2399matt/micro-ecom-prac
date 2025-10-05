package com.example.cart_service.controller;

import com.example.cart_service.entity.Cart;
import com.example.cart_service.entity.CartItem;
import com.example.cart_service.entity.ProductResponse;
import com.example.cart_service.service.CartItemService;
import com.example.cart_service.service.CartService;
import com.example.cart_service.util.ProductServiceProxy;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(CartController.class);

    public CartController(CartService cartService, CartItemService cartItemService, ProductServiceProxy proxy) {
        this.cartService = cartService;
        this.cartItemService = cartItemService;
        this.proxy = proxy;
    }


    @GetMapping
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
            logger.info("Added product with id: {} to cart for user: {}", productId, userId);
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
        logger.info("Clearing cart for user: {}", userId);
        cartService.clearCart(userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cart-items")
    public ResponseEntity<List<CartItem>> getCartItems(@RequestHeader("X-ID") int userId) {
        logger.info("Loading user's cart with id: {}", userId);
        Cart cart = cartService.findByUserId(userId);
        return ResponseEntity.ok(cart.getCartItems());
    }

}
