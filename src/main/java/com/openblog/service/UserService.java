package com.openblog.service;

import com.openblog.entity.User;

import java.util.List;

public interface UserService {

    /**
     * Get all users
     *
     * @return a list of User instances
     */
    List<User> listUser();

    /**
     * Get all users except admins
     *
     * @return a list of User instances
     */
    List<User> listUserNotAdmin();

    /**
     * Retrieve User by Id
     *
     * @param id
     * @return User
     */
    User getUserById(String id);

    /**
     * Retrieve User by username
     *
     * @param username
     * @return User
     */
    User getUserByName(String username);

    /**
     * Retrieve User by email
     *
     * @param email
     * @return User
     */
    User getUserByEmail(String email);

    /**
     * Modify user info
     *
     * @param user
     */
    void updateUser(User user);

    /**
     * Delete a user
     *
     * @param user
     */
    void deleteUser(User user);

    /**
     * Add a user
     *
     * @param user
     * @return User
     */
    void addUser(User user);

}
