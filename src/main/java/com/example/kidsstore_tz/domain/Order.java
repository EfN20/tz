package com.example.kidsstore_tz.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private List<CartItem> cartItems;

    @Column
    private long totalPrice;

    @Column
    private OrderStatus status;

    public Order() {
        this.status = OrderStatus.PENDING;
    }

    public Order(User user, List<CartItem> cartItems) {
        this.user = user;
        this.cartItems = cartItems;
        this.status = OrderStatus.PENDING;
    }

    public Order(User user, List<CartItem> cartItems, long totalPrice) {
        this.user = user;
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
        this.status = OrderStatus.PENDING;
    }

    public Order(int id, User user, List<CartItem> cartItems, long totalPrice) {
        this.id = id;
        this.user = user;
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
        this.status = OrderStatus.PENDING;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}

enum OrderStatus {
    PENDING,
    APPROVED
}
