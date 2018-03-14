package com.baldur.services.impl;

import com.baldur.model.dao.impl.UserDAO;
import com.baldur.model.dao.interfaces.IUserDAO;
import com.baldur.model.pojo.User;
import com.baldur.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService implements IUserService {
    @Autowired
    private static IUserDAO userDAO = new UserDAO();

    @Override
    public boolean userExists(String username) {
        return userDAO.userExists(username);
    }

    @Override
    public User auth(String username, String password) {
        return userDAO.findUser(username, password);
    }

    @Override
    public boolean register(String username, String password) {
        return userDAO.save(new User(username, password)) != null;
    }
}