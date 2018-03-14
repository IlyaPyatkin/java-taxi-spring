package com.baldur.services.interfaces;

import com.baldur.model.pojo.Order;

import java.util.List;

public interface IOrderService {
    List<Order> getOrders(String username);

    boolean create(String username, String origin, String destination);

    boolean create(String username, Order order);
}
