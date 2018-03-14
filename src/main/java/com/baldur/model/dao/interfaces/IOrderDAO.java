package com.baldur.model.dao.interfaces;

import com.baldur.model.pojo.Order;

import java.util.List;

public interface IOrderDAO extends DAO<Order, Long> {
    List<Order> getOrders(String username);

    boolean create(String username, Order order);
}
