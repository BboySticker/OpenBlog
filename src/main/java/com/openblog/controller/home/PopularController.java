package com.openblog.controller.home;

import com.openblog.entity.Article;
import com.openblog.service.ArticleService;
import com.openblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class PopularController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @GetMapping("/popular/{pageIndex}")
    public String listPopular(HttpServletResponse response,
                              Model model,
                              @PathVariable Integer pageIndex) {
        response.setHeader("action", "popular");
        model.addAttribute("action", "popular");
        model.addAttribute("href", "/OpenBlog/popular/");

        List<Article> allArticles = articleService.listArticle();
        int articleCount = allArticles.size();
        List<Article> articleList = articleService.listArticleByViewCount(pageIndex * 10);

        // start doing pagination
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

        // add tag list
        model.addAttribute("tagList", tagService.listTag());

        return "Home/list";
    }

}
