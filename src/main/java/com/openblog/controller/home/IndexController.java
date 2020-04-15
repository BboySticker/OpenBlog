package com.openblog.controller.home;

import com.openblog.entity.Article;
import com.openblog.service.ArticleService;
import com.openblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author bboysticker
 *
 * Form the index page endpoint.
 */
@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = {"/", "/index"})
    public String index(Model model) {
        // generate the jumbotron
        Article jumbotron = articleService.listArticleByViewCount(1).get(0);
        model.addAttribute("jumbotron", jumbotron);

        // generate the featured posts, num = 2
        List<Article> featuredPosts = articleService.listArticleByCommentCount(2);
        model.addAttribute("featuredPosts", featuredPosts);

        // generate the recent articles, num = 3
        List<Article> whatsNewToday = articleService.listRecentArticle(3);
        model.addAttribute("whatsNewToday", whatsNewToday);

        return "Home/index";
    }

    @GetMapping("/body")
    public String body(Model model) {
        return "Home/Public/body";
    }

}
