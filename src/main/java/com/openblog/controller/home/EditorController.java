package com.openblog.controller.home;

import com.openblog.entity.Article;
import com.openblog.entity.User;
import com.openblog.service.ArticleService;
import com.openblog.util.HtmlToPlainText;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author bboysticker
 */
@Controller
public class EditorController {

    @Autowired
    private ArticleService articleService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/editor/drafts/new")
    public String newArticle(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "Home/index";
        }
        return "Editor/editor";
    }

    @PostMapping("/editor/drafts/save")
    public String saveArticle(HttpServletRequest request, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "Home/index";
        }
        String articleContent = request.getParameter("article");
        if (articleContent == null) {
            return "Home/index";
        }
        // transfer markdown to html
        Parser parser = Parser.builder().build();
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        Node document = parser.parse(articleContent);
        String html = renderer.render(document);

        // transfer html to plain text
        HtmlToPlainText formatter = new HtmlToPlainText();
        String converted = formatter.getPlainText(Jsoup.parse(html));

        Article article = new Article();
        article.setArticleUserId(currentUser.getUserId());
        article.setArticleTitle(request.getParameter("title"));
        article.setArticleViewCount(0);
        article.setArticleCommentCount(0);
        article.setArticleLikeCount(0);
        article.setArticleCreateTime(new Date());
        article.setArticleUpdateTime(new Date());
        article.setArticleIsComment(0);
        article.setArticleStatus(1);
        article.setArticleOrder(0);
        article.setArticleContent(html);
        article.setArticleContentInMd(articleContent);
        article.setArticleSummary(converted.substring(0, 300));
        article.setUser(currentUser);
//        article.setTagList();
//        article.setCategoryList();

        articleService.insertArticle(article);
        model.addAttribute("article", article);
        return "redirect:/article/" + article.getArticleId();
    }

    @GetMapping("/editor/drafts")
    public String viewDrafts(HttpServletRequest request, Model model) {
        return "Editor/drafts";
    }

}
