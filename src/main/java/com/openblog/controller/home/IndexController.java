package com.openblog.controller.home;

import com.openblog.entity.Article;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {



    @RequestMapping(value = {"/index","/article"})
    public String index(Model model) {
        Article article = new Article();
        article.setArticleContent("Etiam porta sem malesuada magna mollis euismod. Cras mattis consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla sed consectetur.");
        article.setArticleTitle("FIRST ARTICLE");
        article.setArticleSummary("Etiam porta sem malesuada magna mollis euismod. Cras mattis consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla sed consectetur.");

        model.addAttribute("jumbotron", article);

        List<Article> featuredPosts = new ArrayList<Article>();

        Article article1 = new Article();
        article1.setArticleContent("Etiam porta sem malesuada magna mollis euismod. Cras mattis consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla sed consectetur.");
        article1.setArticleTitle("SECOND ARTICLE");
        article1.setArticleSummary("Etiam porta sem malesuada magna mollis euismod. Cras mattis consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla sed consectetur.");

        Article article2 = new Article();
        article2.setArticleContent("Etiam porta sem malesuada magna mollis euismod. Cras mattis consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla sed consectetur.");
        article2.setArticleTitle("THIRD ARTICLE");
        article2.setArticleSummary("Etiam porta sem malesuada magna mollis euismod. Cras mattis consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla sed consectetur.");

        featuredPosts.add(article1);
        featuredPosts.add(article2);

        model.addAttribute("featuredPosts", featuredPosts);

        return "Home/index";
    }

    @GetMapping("/body")
    public String body(Model model) {
        return "Home/Public/body";
    }

}
