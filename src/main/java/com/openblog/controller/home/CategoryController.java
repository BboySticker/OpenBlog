package com.openblog.controller.home;

import com.openblog.entity.Article;
import com.openblog.entity.Category;
import com.openblog.entity.Tag;
import com.openblog.service.ArticleService;
import com.openblog.service.CategoryService;
import com.openblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CategoryController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    private Map<Integer, String> categoryMap = new HashMap();

    @PostConstruct
    public void init() {
        List<Category> categoryList = categoryService.listCategory();
        if (categoryList == null || categoryList.size() == 0) {
            categoryMap.put(1, "Technology");
            categoryMap.put(2, "Design");
            categoryMap.put(3, "Culture");
            categoryMap.put(4, "Business");
            categoryMap.put(5, "Politics");
            categoryMap.put(6, "Opinion");
            categoryMap.put(7, "Science");
            categoryMap.put(8, "Health");
            categoryMap.put(9, "Style");
            categoryMap.put(10, "Travel");
            for (int i = 1; i <= 10; ++i) {
                Category category = new Category();
                category.setCategoryPid(i);
                category.setCategoryName(categoryMap.get(i));
                category.setCategoryOrder(i);
                categoryService.insertCategory(category);
            }
        }
    }

    @GetMapping("/category/{categoryName}/{pageIndex}")
    public String listByCategory(HttpServletRequest request,
                                 HttpServletResponse response,
                                 Model model,
                                 @PathVariable String categoryName,
                                 @PathVariable Integer pageIndex) {
        response.setHeader("keyword", "category");

        // category info
        Category category = categoryService.getCategoryByName(categoryName);
        if (category == null) {
            model.addAttribute("msg", "Category not found");
            return "redirect:Home/error";
        }
        model.addAttribute("category", category);

        // article list
        List<Article> articleList = articleService.listArticleByCategoryId(category.getCategoryId(), pageIndex * 10);
        if (articleList.size() > pageIndex * 10) {
            model.addAttribute("articleList", articleList.subList((pageIndex - 1) * 10, pageIndex * 10));
        } else if (articleList.size() < (pageIndex - 1) * 10) {
            model.addAttribute("msg", "Page not found");
            return "Home/error";
        } else {
            model.addAttribute("articleList", articleList.subList((pageIndex - 1) * 10, articleList.size()));
        }

        // tag list
        List<Tag> tagList = tagService.listTag();
        model.addAttribute("tagList", tagList);

        int articleCount = articleService.countArticleByCategoryId(category.getCategoryId());
        model.addAttribute("pageCount", articleCount % 10 == 0 ? articleCount / 10 : articleCount / 10 + 1);

        return "Home/list";
    }

}
