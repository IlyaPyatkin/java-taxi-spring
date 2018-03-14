package com.baldur.model.dao.interfaces;

import com.baldur.model.pojo.User;

public interface IUserDAO extends DAO<User, Long> {
    User findUser(String username, String password);

    boolean userExists(String username);
}
