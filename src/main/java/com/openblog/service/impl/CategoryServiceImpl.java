package com.openblog.service.impl;

import com.openblog.dao.CategoryDao;
import com.openblog.entity.Category;
import com.openblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    public Integer countCategory() {
        return categoryDao.countCategory();
    }

    public List<Category> listCategory() {
        return categoryDao.listCategory();
    }

    public List<Category> listCategoryWithCount() {
        return categoryDao.listCategoryWithCount();
    }

    public void deleteCategory(Integer id) {
        categoryDao.deleteCategory(id);
    }

    public Category getCategoryById(Integer id) {
        return categoryDao.getCategoryById(id);
    }

    public Category insertCategory(Category category) {
        return categoryDao.insertCategory(category);
    }

    public void updateCategory(Category category) {
        categoryDao.updateCategory(category);
    }

    public Category getCategoryByName(String name) {
        return categoryDao.getCategoryByName(name);
    }
}
