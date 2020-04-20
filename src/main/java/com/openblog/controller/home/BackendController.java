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
    public String getUser(@PathVariable String username) {
        return "redirect:/user/" + username + "/1";
    }

    @GetMapping("/user/{username}/{pageIndex}")
    public String getPageUsers(HttpSession session,
                               Model model,
                               @PathVariable String username,
                               @PathVariable Integer pageIndex) {

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
        } else if (user.getIsAdmin() == 0) {
            // normal user
            List<Article> articleList = articleService.listArticleByAuthor(user.getUserId());
            int articleCount = articleList.size();
            if (articleCount >= pageIndex * 10) {
                // has enough num of articles to fill current page
                model.addAttribute("articleList", articleList.subList((pageIndex - 1) * 10, pageIndex * 10));
            } else if (articleCount < (pageIndex - 1) * 10) {
                // doesn't have this page
                model.addAttribute("msg", "Page not found");
                return "Home/error";
            } else {
                model.addAttribute("articleList", articleList.subList((pageIndex - 1) * 10, articleCount));
            }
            model.addAttribute("pageCount", articleCount % 10 == 0 ? articleCount / 10 : articleCount / 10 + 1);
            model.addAttribute("pageIndex", pageIndex);
            model.addAttribute("user", user);
            return "Backend/user-backend";
        } else {
            // user is admin
            return "redirect:/admin/" + username;
        }
    }

    @GetMapping("/admin")
    public String redirect() {
        return "redirect:/index";
    }

    @GetMapping({"/admin/{username}", "/admin/{username}/"})
    public String redirectAdmin(@PathVariable String username) {
        return "redirect:/admin/" + username + "/articles";
    }

    @GetMapping("/admin/{username}/{action}")
    public String redirectWithUsernameAndAction(@PathVariable String username,
                                                @PathVariable String action) {
        return "redirect:/admin/" + username + "/" + action + "/1";
    }

    @GetMapping("/admin/{username}/{action}/{pageIndex}")
    public String getAdmin(HttpSession session,
                           Model model,
                           @PathVariable String username,
                           @PathVariable String action,
                           @PathVariable Integer pageIndex) {
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
        } else if (user.getIsAdmin() == 0) {
            // normal user
            return "redirect:/user/" + username;
        } else {
            // user is admin
            if (action.equalsIgnoreCase("users")) {
                // admin manage users
                List<User> userList = userService.listUserNotAdmin();
                int userCount = userList.size();
                if (userCount >= pageIndex * 10) {
                    // has enough num of articles to fill current page
                    model.addAttribute("userList", userList.subList((pageIndex - 1) * 10, pageIndex * 10));
                } else if (userCount < (pageIndex - 1) * 10) {
                    // doesn't have this page
                    model.addAttribute("msg", "Page not found");
                    return "Home/error";
                } else {
                    model.addAttribute("userList", userList.subList((pageIndex - 1) * 10, userCount));
                }
                model.addAttribute("pageCount", userCount % 10 == 0 ? userCount / 10 : userCount / 10 + 1);
                model.addAttribute("pageIndex", pageIndex);
                return "Backend/admin-backend-users";
            } else if (action.equalsIgnoreCase("articles")) {
                // admin manage articles
                List<Article> articleList = articleService.listArticle();
                int articleCount = articleList.size();
                if (articleCount >= pageIndex * 10) {
                    // has enough num of articles to fill current page
                    model.addAttribute("articleList", articleList.subList((pageIndex - 1) * 10, pageIndex * 10));
                } else if (articleCount < (pageIndex - 1) * 10) {
                    // doesn't have this page
                    model.addAttribute("msg", "Page not found");
                    return "Home/error";
                } else {
                    model.addAttribute("articleList", articleList.subList((pageIndex - 1) * 10, articleList.size()));
                }
                model.addAttribute("pageCount", articleCount % 10 == 0 ? articleCount / 10 : articleCount / 10 + 1);
                model.addAttribute("pageIndex", pageIndex);
                model.addAttribute("user", user);
                return "Backend/admin-backend";
            } else {
                model.addAttribute("msg", "Page not exist");
                return "Home/error";
            }
        }
    }

}
