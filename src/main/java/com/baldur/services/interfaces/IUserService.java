package com.baldur.services.interfaces;

import com.baldur.model.pojo.User;

public interface IUserService {
    boolean userExists(String username);

    User auth(String username, String password);

    boolean register(String name, String password);
}
