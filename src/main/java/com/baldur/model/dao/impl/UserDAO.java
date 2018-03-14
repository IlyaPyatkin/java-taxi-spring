package com.baldur.model.dao.impl;

import com.baldur.model.dao.interfaces.IUserDAO;
import com.baldur.model.pojo.User;
import com.baldur.model.utils.DataSourceFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class UserDAO implements IUserDAO {
    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());

    @Override
    public boolean userExists(String username) {
        boolean result = false;
        String sql = "SELECT count(1) FROM users WHERE username = ?;";

        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            result = resultSet.getInt(1) == 1;
            connection.close();
        } catch (SQLException e) {
            LOGGER.warning("Error while checking if user exists in db: " + e);
        }

        return result;
    }

    @Override
    public User findUser(String username, String password) {
        User user = null;
        String sql = "SELECT username, password FROM users WHERE username = ? AND password = ?;";

        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            user = new User(resultSet.getString("username"), resultSet.getString("password"));
            connection.close();
        } catch (SQLException e) {
            LOGGER.warning("Error while fetching user from db: " + e);
        }

        return user;
    }

    @Override
    public User getById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public User save(User user) {
        String sql = "INSERT INTO users VALUES (?, ?);";
        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
            connection.close();
            return user;
        } catch (SQLException e) {
            LOGGER.warning("Error while inserting a new user into db: " + e);
            return null;
        }
    }

    @Override
    public Long insert(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int update(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int delete(User user) {
        throw new UnsupportedOperationException();
    }
}
