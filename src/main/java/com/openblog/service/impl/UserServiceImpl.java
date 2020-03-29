package com.openblog.service.impl;

import com.openblog.dao.UserDao;
import com.openblog.entity.User;
import com.openblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public List<User> listUser() {
        return userDao.listUser();
    }

    @Transactional
    public User getUserById(String id) {
        return userDao.getUserById(id);
    }

    @Transactional
    public User getUserByUsernameOrEmail(String str) {
        return userDao.getUserByUsernameOrEmail(str);
    }

    @Transactional
    public User getUserByName(String username) {
        return userDao.getUserByName(username);
    }

    @Transactional
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Transactional
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Transactional
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    @Transactional
    public void addUser(User user) {
        userDao.addUser(user);
    }

}
