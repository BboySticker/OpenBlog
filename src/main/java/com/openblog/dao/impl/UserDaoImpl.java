package com.openblog.dao.impl;

import com.openblog.dao.UserDao;
import com.openblog.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<User> listUser() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("from User");
        return query.list();
    }

    public User getUserById(String id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(User.class, id);
    }

    public User getUserByUsernameOrEmail(String str) {
        return null;
    }

    public User getUserByName(String username) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("from User where userName=:username");
        query.setParameter("username", username);
        return (User) query.uniqueResult();
    }

    public User getUserByEmail(String email) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("from User where userEmail=:email");
        query.setParameter("email", email);
        return (User) query.uniqueResult();
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
