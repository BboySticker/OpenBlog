package com.openblog.controller.home;

import com.openblog.entity.Article;
import com.openblog.entity.Tag;
import com.openblog.entity.User;
import com.openblog.service.ArticleService;
import com.openblog.service.TagService;
import com.openblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    @Autowired
    private TagService tagService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        if (articleService.listArticle().size() < 3) {
            User user = userService.getUserByName("user01");
            if (user == null) {
                User newUser = new User();
                newUser.setUserId(UUID.randomUUID().toString());
                newUser.setUserName("user01");
                newUser.setUserPass(passwordEncoder.encode("user01"));
                newUser.setUserEmail("user01@gmail.com");
                newUser.setUserUrl("user/user01");
                newUser.setUserRegisterTime(new Date());
                newUser.setUserLastLoginTime(new Date());
                newUser.setUserStatus(1);
                newUser.setIsAdmin(0);
                userService.addUser(newUser);
                user = newUser;
            }
            for (int i = 1; i <= 10; ++i) {
                Article article = new Article();
                article.setArticleUserId(user.getUserId());
                article.setArticleTitle("Article Test " + i);
                article.setArticleViewCount(0);
                article.setArticleCommentCount(0);
                article.setArticleLikeCount(0);
                article.setArticleCreateTime(new Date());
                article.setArticleUpdateTime(new Date());
                article.setArticleIsComment(0);
                article.setArticleStatus(1);
                article.setArticleOrder(1);
                article.setArticleContent("");
                article.setArticleContentInMd("");
                article.setArticleSummary("");
                article.setUser(user);
                articleService.insertArticle(article);
            }
        }
    }

    @RequestMapping(value = {"/", "/index"})
    public String index(Model model) {
        List<Article> top3 = articleService.listArticleByViewCount(3);

        // generate the jumbotron
        Article jumbotron = top3.get(0);
        model.addAttribute("jumbotron", jumbotron);

        // generate the featured posts, num = 2
        List<Article> featuredPosts = top3.subList(1,3);
        model.addAttribute("featuredPosts", featuredPosts);

        // generate the recent articles, num = 3
        List<Article> whatsNewToday = articleService.listRecentArticle(3);
        model.addAttribute("whatsNewToday", whatsNewToday);

        // add tag list
        List<Tag> tagList = tagService.listTag();
        model.addAttribute("tagList", tagList);

        // get total visit num
        int views = articleService.countArticleView();
        model.addAttribute("views", views);

        return "Home/index";
    }

}
