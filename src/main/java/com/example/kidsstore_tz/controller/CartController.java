package com.example.kidsstore_tz.controller;

import com.example.kidsstore_tz.domain.Cart;
import com.example.kidsstore_tz.exception.NotFoundException;
import com.example.kidsstore_tz.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public ResponseEntity<Cart> getCart() {
        try {
            return ResponseEntity.ok(cartService.getCart());
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Cart> addCart(@RequestBody Cart cart) {
        return ResponseEntity.ok(cartService.addCart(cart));
    }

    @PostMapping("{product_id}")
    public ResponseEntity<Cart> addItemToCart(@PathVariable int product_id) {
        try {
            return ResponseEntity.ok(cartService.addItemToCart(product_id));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{product_id}")
    public ResponseEntity<Cart> removeItemFromCart(@PathVariable int product_id) {
        return ResponseEntity.ok(cartService.removeItemFromCart(product_id));
    }

    @PutMapping("{product_id}")
    public ResponseEntity<Cart> updateCart(@PathVariable int product_id, @RequestParam boolean toIncrease) {
        try {
            return ResponseEntity.ok(cartService.changeCartItemQuantity(product_id, toIncrease));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/checkout")
    public ResponseEntity<Cart> checkout() {
        return ResponseEntity.ok(cartService.checkOut());
    }

}
