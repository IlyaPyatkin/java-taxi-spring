package com.baldur.model.dao.impl;

import com.baldur.model.dao.interfaces.IOrderDAO;
import com.baldur.model.pojo.Order;
import com.baldur.model.utils.DataSourceFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class OrderDAO implements IOrderDAO {
    private static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());

    @Override
    public List<Order> getOrders(String username) {
        ArrayList<Order> orders = new ArrayList<>();
        String sql = "SELECT origin, destination, created FROM orders WHERE username = ?;";

        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(new Order(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDate(3)));
            }
            connection.close();
        } catch (SQLException e) {
            LOGGER.warning("Error while fetching orders from db: " + e);
        }

        return orders;
    }

    public boolean create(String username, Order order) {
        String sql = "INSERT INTO orders VALUES (?, ?, ?, ?);";
        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, order.getOrigin());
            statement.setString(3, order.getDestination());
            ZoneId zoneId = ZoneId.of("Europe/Moscow");
            ZonedDateTime zdt = ZonedDateTime.ofInstant(order.getTime().toInstant(), zoneId);
            LocalDate localDate = zdt.toLocalDate();
            statement.setDate(4, java.sql.Date.valueOf(localDate));
            statement.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException e) {
            LOGGER.warning("Error while inserting a new order into db: " + e);
            return false;
        }
    }

    @Override
    public Order getById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Order> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Order save(Order entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Long insert(Order order) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int update(Order order) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int delete(Order order) {
        throw new UnsupportedOperationException();
    }
}
