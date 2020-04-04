package com.openblog.dao.impl;

import com.openblog.controller.home.LoginController;
import com.openblog.dao.UserDao;
import com.openblog.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    public List<User> listUser() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("from User");
        return query.list();
    }

    public User getUserById(String id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(User.class, id);
    }

    public User getUserByName(String username) {
        logger.info("Processing query of [{}] with username: [{}]", "getUserByName", username);
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("from User where userName=:username");
        query.setParameter("username", username);
        List<User> userList = query.list();
        if (userList.size() == 0) {
            return null;
        }
        return userList.get(0);
    }

    public User getUserByEmail(String email) {
        logger.info("Processing query of [{}] with email: [{}]", "getUserByEmail", email);
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("from User where userEmail=:email");
        query.setParameter("email", email);
        List<User> userList = query.list();
        if (userList.size() == 0) {
            return null;
        }
        return userList.get(0);
    }

    public void updateUser(User user) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(user);
    }

    public void deleteUser(User user) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(user);
    }

    public void addUser(User user) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(user);
    }

}
