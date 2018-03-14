package com.baldur.services.impl;

import com.baldur.model.dao.impl.OrderDAO;
import com.baldur.model.dao.interfaces.IOrderDAO;
import com.baldur.model.pojo.Order;
import com.baldur.services.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderService implements IOrderService {
    @Autowired
    private static IOrderDAO orderDAO = new OrderDAO();

    @Override
    public List<Order> getOrders(String username) {
        return orderDAO.getOrders(username);
    }

    @Override
    public boolean create(String username, String origin, String destination) {
        return orderDAO.create(username, new Order(origin, destination));
    }

    @Override
    public boolean create(String username, Order order) {
        return orderDAO.create(username, order);
    }
}