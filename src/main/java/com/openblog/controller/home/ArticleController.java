package com.openblog.controller.home;

import com.openblog.entity.Article;
import com.openblog.entity.Tag;
import com.openblog.entity.User;
import com.openblog.service.ArticleService;
import com.openblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @GetMapping("/article/{articleId}")
    public String getArticleDetailPage(Model model, @PathVariable Integer articleId) {
        Article article = articleService.getArticleByStatusAndId(1, articleId);
        if (article == null) {
            model.addAttribute("msg", "Article " + articleId + " not found");
            return "Home/error";
        } else {
            // increase the view count
            article.setArticleViewCount(article.getArticleViewCount() + 1);
            articleService.updateArticle(article);
            model.addAttribute("article", article);
            return "Home/detail";
        }
    }

    @GetMapping("/article/list")
    public String getArticles(HttpServletRequest request,
                              HttpServletResponse response,
                              Model model) {
        return "redirect:Home/list/1";
    }

    @GetMapping("/article/list/{pageIndex}")
    public String getPageArticles(HttpServletRequest request,
                                  HttpServletResponse response,
                                  Model model,
                                  @PathVariable Integer pageIndex) {
        // add action into response to indicate the action type
        // results displayed page will act according to 'action'
        response.setHeader("action", "search");

        String keyword = request.getParameter("keyword");
        model.addAttribute("keyword", keyword);
        model.addAttribute("action", "search");
        model.addAttribute("href", "/OpenBlog/article/list");

        // search articles according to the keyword
        List<Article> articleList = articleService.listArticle();
        List<Article> result = new ArrayList<Article>();
        for (Article article : articleList) {
            boolean flag = Pattern.compile(Pattern.quote(keyword), Pattern.CASE_INSENSITIVE)
                    .matcher(article.getArticleTitle())
                    .find();
            if (flag) {
                result.add(article);
            }
        }
        // doing pagination
        if (result.size() >= pageIndex * 10) {
            // has enough num of articles to fill current page
            model.addAttribute("articleList", result.subList((pageIndex - 1) * 10, pageIndex * 10));
            model.addAttribute("pageCount", result.size() % 10 == 0 ? result.size() / 10 : result.size() / 10 + 1);
        } else if (result.size() < (pageIndex - 1) * 10) {
            // doesn't have this page
            model.addAttribute("msg", "Page not found");
            return "Home/error";
        } else {
            model.addAttribute("articleList", result.subList((pageIndex - 1) * 10, result.size()));
            model.addAttribute("pageCount", result.size() % 10 == 0 ? result.size() / 10 : result.size() / 10 + 1);
        }
        model.addAttribute("pageIndex", pageIndex);

        // add tag list
        List<Tag> tagList = tagService.listTag();
        model.addAttribute("tagList", tagList);

        return "Home/list";
    }

    @GetMapping("/article/edit/{articleId}")
    public String editArticle(Model model, @PathVariable Integer articleId) {
        try {
            Article article = articleService.getArticleByStatusAndId(1, articleId);
            model.addAttribute("article", article);
        } catch (Exception ex) {
            model.addAttribute("msg", "Article " + articleId + " not exist");
            return "Home/error";
        }
        return "Editor/editor";
    }

    @GetMapping("/article/delete/{articleId}")
    public String deleteArticle(HttpSession session, Model model, @PathVariable Integer articleId) {
        try {
            articleService.deleteArticle(articleService.getArticleByStatusAndId(1, articleId));
        } catch (Exception ex) {
            model.addAttribute("msg", "Delete Failed");
            return "Home/error";
        }
        User user = (User) session.getAttribute("user");
        return "redirect:/user/" + user.getUserName();
    }

}
