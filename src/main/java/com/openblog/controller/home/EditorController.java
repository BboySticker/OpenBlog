package com.openblog.controller.home;

import com.openblog.entity.Article;
import com.openblog.entity.User;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public String saveArticle(HttpServletRequest request, Model model) {
        String articleContent = request.getParameter("article");
        Parser parser = Parser.builder().build();
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        Node document = parser.parse(articleContent);
        String html = renderer.render(document);

        logger.info(articleContent);
        logger.info(html);
        System.out.println(articleContent);
        System.out.println(html);

        Article article = new Article();
        article.setArticleContent(html);
        article.setArticleStatus(1);
        article.setArticleCreateTime(new Date());

        model.addAttribute("article", article);

        return "Editor/drafts";
    }

    @GetMapping("/editor/drafts")
    public String viewDrafts(HttpServletRequest request, Model model) {
        return "Editor/drafts";
    }

}
