package com.openblog.controller.home;

import com.openblog.entity.Article;
import com.openblog.entity.User;
import com.openblog.service.ArticleService;
import com.openblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BackendController {

    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    @GetMapping("/user/{username}")
    public String getUser(HttpSession session, Model model, @PathVariable String username) {
        User currentUser = (User) session.getAttribute("user");
        User user = userService.getUserByName(username);
        // start validations
        if (currentUser == null) {
            // user doesn't logged in
            model.addAttribute("msg", "Please log in first");
            return "Home/error";
        } else if (!currentUser.getUserId().equals(user.getUserId())) {
            // user is not allowed to access others backend
            model.addAttribute("msg", "Access denied");
            return "Home/error";
        } else if (user == null) {
            // user cannot access a non-exist user's backend
            model.addAttribute("msg", "User not exist");
            return "Home/error";
        } else {
            List<Article> articleList = articleService.listArticleByAuthor(user.getUserId());
            model.addAttribute("user", user);
            model.addAttribute("articleList", articleList);
            return "Backend/user-backend";
        }
    }

}
