package com.openblog.dao.impl;

import com.openblog.dao.CategoryDao;
import com.openblog.entity.Category;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    SessionFactory sessionFactory;

    public Integer countCategory() {
        return null;
    }

    public List<Category> listCategory() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("FROM Category");
        return query.list();
    }

    public List<Category> listCategoryWithCount() {
        return null;
    }

    public void deleteCategory(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Category category = currentSession.get(Category.class, id);
        currentSession.delete(category);
    }

    public Category getCategoryById(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery(
                "FROM Category WHERE pid=:pid")
                .setInteger("pid", id);
        return (Category) query.uniqueResult();
    }

    public Category insertCategory(Category category) {
        Session currentSession = sessionFactory.getCurrentSession();
        if (category != null) {
            currentSession.save(category);
        }
        return category;
    }

    public void updateCategory(Category category) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(category);
    }

    public Category getCategoryByName(String name) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery(
                "FROM Category WHERE categoryName like :name")
                .setString("name", name);
        return (Category) query.list().get(0);
    }

}
