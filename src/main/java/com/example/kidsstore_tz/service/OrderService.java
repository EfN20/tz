package com.example.kidsstore_tz.service;

import com.example.kidsstore_tz.domain.Order;
import com.example.kidsstore_tz.repository.OrderRepository;
import com.example.kidsstore_tz.service.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;


    @Override
    public List<Order> getOrders() {
        return orderRepository.findAllByUser(userService.getCurrentUser());
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

}
