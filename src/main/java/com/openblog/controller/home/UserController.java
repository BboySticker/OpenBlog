package com.openblog.controller.home;

import com.openblog.entity.Article;
import com.openblog.entity.User;
import com.openblog.service.ArticleService;
import com.openblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @GetMapping("/user/delete/{userId}")
    public String deleteUser(HttpSession session, @PathVariable String userId) {
        List<Article> articleList = articleService.listArticleByAuthor(userId);
        for (Article article: articleList) {
            article.setUser(null);
            articleService.updateArticle(article);
        }
        User user = userService.getUserById(userId);
        userService.deleteUser(user);
        User currentUser = (User) session.getAttribute("user");
        return "redirect:/admin/"+ currentUser.getUserName() +"/users";
    }

}
