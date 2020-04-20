package com.openblog.controller.home;

import com.openblog.entity.Article;
import com.openblog.entity.Tag;
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
public class TagController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @GetMapping("/tag/{tagName}")
    public String redirectListByTag(@PathVariable String tagName) {
        return "redirect:/category/" + tagName + "/1";
    }

    @GetMapping("/tag/{tagName}/{pageIndex}")
    public String listByTag(HttpServletResponse response,
                            Model model,
                            @PathVariable String tagName,
                            @PathVariable Integer pageIndex) {
        response.setHeader("action", "tag");
        model.addAttribute("action", "tag");
        model.addAttribute("href", "/OpenBlog/tag/" + tagName);

        // add tag
        Tag tag = tagService.getTagByName(tagName);
        if (tag == null) {
            // tag not exist
            model.addAttribute("msg", "Page not found");
            return "Home/error";
        }
        model.addAttribute("tag", tag);

        // start doing pagination
        List<Article> articleList = articleService.listArticleByTagId(tag.getTagId(), pageIndex * 10);
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

        // add tag list
        List<Tag> tagList = tagService.listTag();
        model.addAttribute("tagList", tagList);

        return "Home/list";
    }

}
