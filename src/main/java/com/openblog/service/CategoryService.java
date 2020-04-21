package com.openblog.service;

import com.openblog.entity.Category;

import java.util.List;

public interface CategoryService {

    Integer countCategory();

    List<Category> listCategory();

    List<Category> listCategoryWithCount();

    void deleteCategory(Integer id);

    Category getCategoryById(Integer id);

    Category insertCategory(Category category);

    void updateCategory(Category category);

    Category getCategoryByName(String name);
}
