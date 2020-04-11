package com.openblog.controller.home;

import com.openblog.entity.Article;
import com.openblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/article/{articleId}")
    public String getArticleDetailPage(@PathVariable Integer articleId, Model model) {
        Article article = articleService.getArticleByStatusAndId(1, articleId);
        model.addAttribute("article", article);
        if (article == null) {
            return "Home/error";
        } else {
            return "Home/detail";
        }
    }

}
