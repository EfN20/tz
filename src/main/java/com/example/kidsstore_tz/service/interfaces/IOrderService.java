package com.example.kidsstore_tz.service.interfaces;

import com.example.kidsstore_tz.domain.Order;

import java.util.List;

public interface IOrderService {

    List<Order> getOrders();

    Order createOrder(Order order);

}
