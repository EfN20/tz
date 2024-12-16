package com.example.kidsstore_tz.service;

import com.example.kidsstore_tz.domain.Cart;
import com.example.kidsstore_tz.domain.CartItem;
import com.example.kidsstore_tz.domain.Order;
import com.example.kidsstore_tz.domain.Product;
import com.example.kidsstore_tz.domain.User;
import com.example.kidsstore_tz.exception.NotFoundException;
import com.example.kidsstore_tz.repository.CartRepository;
import com.example.kidsstore_tz.service.interfaces.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService implements ICartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Override
    public Cart getCart() {
        User user = userService.getCurrentUser();
        return cartRepository.getCartByUser(user).orElseThrow(() -> new NotFoundException("Cart not found"));
    }

    @Override
    public Cart addCart(Cart cart) {
        User user = userService.getCurrentUser();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public Cart updateCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public boolean removeCart(Cart cart) {
        if (cartRepository.existsById(cart.getId())) {
            cartRepository.deleteById(cart.getId());
            return true;
        } throw new NotFoundException("Cart not found");
    }

    @Override
    public Cart addItemToCart(int productId) {
        User user = userService.getCurrentUser();
        Cart cart = cartRepository.getCartByUser(user).orElse(new Cart(user, new ArrayList<>()));
        Product product = productService.getProductById(productId);
        if (product.getQuantity() < 1) {
            throw new NotFoundException("Product is not available");
        }
        CartItem cartItem = new CartItem(productService.getProductById(productId), 1);
        cart.addCartItem(cartItem);
        return updateCart(cart);
    }

    @Override
    public Cart removeItemFromCart(int productId) {
        Cart cart = getCart();
        CartItem cartItem = cart.getCartItems().stream().filter(item -> item.getProduct().getId() == productId).findFirst().orElse(null);
        if (cartItem != null) {
            cart.removeCartItem(cartItem);
            return updateCart(cart);
        } else throw new NotFoundException("Item not found");
    }

    @Override
    public Cart changeCartItemQuantity(int productId, boolean toIncrease) {
        Cart cart = getCart();
        Product product = productService.getProductById(productId);
        if (product.getQuantity() < 1 && toIncrease) {
            throw new NotFoundException("Product is not available");
        }
        CartItem cartItem = cart.getCartItems().stream().filter(item -> item.getProduct().getId() == productId).findFirst().orElse(null);
        if (cartItem != null) {
            cartItem.setQuantity(toIncrease ? cartItem.getQuantity() + 1 : cartItem.getQuantity() - 1);
            return updateCart(cart);
        } else throw new NotFoundException("Item not found");
    }

    @Override
    @Transactional
    public Cart checkOut() {
        Cart cart = getCart();
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setCartItems(new ArrayList<>());
        order.setTotalPrice(cart.getTotalPrice());
        for (CartItem cartItem : cart.getCartItems()) {
            cartItem.setCart(null);
            cartItem.setOrder(order);
            order.getCartItems().add(cartItem);
        }
        orderService.createOrder(order);
        removeCart(cart);
        return cart;
    }
}
