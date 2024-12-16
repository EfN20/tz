package com.example.kidsstore_tz.controller;

import com.example.kidsstore_tz.domain.Order;
import com.example.kidsstore_tz.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getOrder() {
        return orderService.getOrders();
    }

}
