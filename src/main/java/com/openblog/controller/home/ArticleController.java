package com.openblog.controller.home;

import com.openblog.entity.Article;
import com.openblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/article/{articleId}")
    public String getArticleDetailPage(@PathVariable Integer articleId, Model model) {
        Article article = articleService.getArticleByStatusAndId(1, articleId);
        if (article == null) {
            model.addAttribute("articleId", articleId);
            return "Home/error";
        } else {
            model.addAttribute("article", article);
            return "Home/detail";
        }
    }

    @GetMapping("/article/list")
    public String getArticles(HttpServletRequest request, HttpServletResponse response, Model model) {
        String action = request.getParameter("action");
        String keyword = request.getParameter("keyword");

        // add action into response to indicate the action type
        // results displayed page will act according to 'action'
        response.setHeader("action", action);
        response.setHeader("keyword", keyword);

        if (action.equalsIgnoreCase("search")) {
            // search articles according to the keyword
            List<Article> articleList = articleService.listArticle();
            List<Article> result = new ArrayList<Article>();
            for (Article article: articleList) {
                boolean flag = Pattern.compile(Pattern.quote(keyword), Pattern.CASE_INSENSITIVE)
                        .matcher(article.getArticleTitle())
                        .find();
                if (flag) {
                    result.add(article);
                }
            }
            model.addAttribute("articleList", result);
        } else if (action.equalsIgnoreCase("category")) {
            // list articles by category
            List<Article> articleList = articleService.listArticleByCategoryId(Integer.parseInt(keyword), 10);
            model.addAttribute("articleList", articleList);
        } else if (action.equalsIgnoreCase("tag")) {
            // list articles by tag
//            List<Article> articleList = articleService.listArticleByTagId(Integer.parseInt(keyword), 10);
//            model.addAttribute("articleList", articleList);
        }
        return "Home/list";
    }

}
