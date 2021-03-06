package com.openblog.dao;

import com.openblog.entity.User;

import java.util.List;

public interface UserDao {

    List<User> listUser();

    List<User> listUserNotAdmin();

    User getUserById(String id);

    User getUserByName(String username);

    User getUserByEmail(String email);

    void updateUser(User user);

    void deleteUser(User user);

    void addUser(User user);

}
