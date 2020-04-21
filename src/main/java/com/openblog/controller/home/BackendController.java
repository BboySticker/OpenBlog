package com.openblog.controller.home;

import com.openblog.entity.Article;
import com.openblog.entity.User;
import com.openblog.service.ArticleService;
import com.openblog.service.TagService;
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
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

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
            model.addAttribute("msg", "Please Log In First");
            return "Home/error";
        } else if (user == null) {
            // user cannot access a non-exist user's backend
            model.addAttribute("msg", "User Not Exist");
            return "Home/error";
        } else if (user.getIsAdmin() == 0) {
            // normal user
            List<Article> articleList = articleService.listArticleByAuthor(user.getUserId());
            int articleCount = articleList.size();
            // start doing pagination
            if (articleCount >= pageIndex * 10) {
                // has enough num of articles to fill current page
                model.addAttribute("articleList", articleList.subList((pageIndex - 1) * 10, pageIndex * 10));
            } else if (articleCount < (pageIndex - 1) * 10) {
                // doesn't have this page
                model.addAttribute("msg", "Page Not Found");
                return "Home/error";
            } else {
                model.addAttribute("articleList", articleList.subList((pageIndex - 1) * 10, articleCount));
            }
            model.addAttribute("pageCount", articleCount % 10 == 0 ? articleCount / 10 : articleCount / 10 + 1);
            model.addAttribute("pageIndex", pageIndex);

            // add tag list
            model.addAttribute("tagList", tagService.listTag());

            model.addAttribute("currentUser", currentUser);
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
            model.addAttribute("msg", "Please Log In First");
            return "Home/error";
        } else if (user == null) {
            // user cannot access a non-exist user's backend
            model.addAttribute("msg", "User Not Exist");
            return "Home/error";
        } else if (user.getIsAdmin() == 0) {
            // normal user
            return "redirect:/user/" + username;
        } else {
            if (action.equalsIgnoreCase("users")) {
                // user is admin
                if (!user.getUserEmail().equals(currentUser.getUserEmail())) {
                    // normal user can not access admin's user management page
                    model.addAttribute("msg", "Access Denied");
                    return "Home/error";
                }
                // admin manage users
                List<User> userList = userService.listUserNotAdmin();
                int userCount = userList.size();
                // start doing pagination
                if (userCount >= pageIndex * 10) {
                    // has enough num of articles to fill current page
                    model.addAttribute("userList", userList.subList((pageIndex - 1) * 10, pageIndex * 10));
                } else if (userCount < (pageIndex - 1) * 10) {
                    // doesn't have this page
                    model.addAttribute("msg", "Page Not Found");
                    return "Home/error";
                } else {
                    model.addAttribute("userList", userList.subList((pageIndex - 1) * 10, userCount));
                }
                model.addAttribute("pageCount", userCount % 10 == 0 ? userCount / 10 : userCount / 10 + 1);
                model.addAttribute("pageIndex", pageIndex);
                // add tag list
                model.addAttribute("tagList", tagService.listTag());
                model.addAttribute("user", user);
                return "Backend/admin-backend-users";
            }
            else if (action.equalsIgnoreCase("articles")) {
                // admin manage articles
                List<Article> articleList = articleService.listArticle();
                if (currentUser.getIsAdmin() == 0) {
                    articleList = articleService.listArticleByAuthor(user.getUserId());
                }
                int articleCount = articleList.size();
                if (articleCount >= pageIndex * 10) {
                    // has enough num of articles to fill current page
                    model.addAttribute("articleList", articleList.subList((pageIndex - 1) * 10, pageIndex * 10));
                } else if (articleCount < (pageIndex - 1) * 10) {
                    // doesn't have this page
                    model.addAttribute("msg", "Page Not Found");
                    return "Home/error";
                } else {
                    model.addAttribute("articleList", articleList.subList((pageIndex - 1) * 10, articleList.size()));
                }
                model.addAttribute("pageCount", articleCount % 10 == 0 ? articleCount / 10 : articleCount / 10 + 1);
                model.addAttribute("pageIndex", pageIndex);

                // add tag list
                model.addAttribute("tagList", tagService.listTag());

                model.addAttribute("currentUser", currentUser);
                model.addAttribute("user", user);
                return "Backend/admin-backend";
            } else {
                model.addAttribute("msg", "Page Not Exist");
                return "Home/error";
            }
        }
    }

}
