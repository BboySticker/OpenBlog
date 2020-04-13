package com.openblog.controller.home;

import com.openblog.entity.Article;
import com.openblog.entity.User;
import com.openblog.service.ArticleService;
import com.openblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author bboysticker
 */
@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @PostConstruct
    private void init() {
        String newArticleContent1 = "<p>This blog post shows a few different types of content that’s supported and styled with Bootstrap. Basic typography, images, and code are all supported.</p>\n" +
                "                <hr>\n" +
                "                <p>Cum sociis natoque penatibus et magnis <a href=\"#\">dis parturient montes</a>, nascetur ridiculus mus. Aenean eu leo quam. Pellentesque ornare sem lacinia quam venenatis vestibulum. Sed posuere consectetur est at lobortis. Cras mattis consectetur purus sit amet fermentum.</p>\n" +
                "                <blockquote>\n" +
                "                    <p>Curabitur blandit tempus porttitor. <strong>Nullam quis risus eget urna mollis</strong> ornare vel eu leo. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>\n" +
                "                </blockquote>\n" +
                "                <p>Etiam porta <em>sem malesuada magna</em> mollis euismod. Cras mattis consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla sed consectetur.</p>\n" +
                "                <h2>Heading</h2>\n" +
                "                <p>Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor. Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Morbi leo risus, porta ac consectetur ac, vestibulum at eros.</p>\n" +
                "                <h3>Sub-heading</h3>\n" +
                "                <p>Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</p>\n" +
                "                <pre><code>Example code block</code></pre>\n" +
                "                <p>Aenean lacinia bibendum nulla sed consectetur. Etiam porta sem malesuada magna mollis euismod. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa.</p>\n" +
                "                <h3>Sub-heading</h3>\n" +
                "                <p>Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Aenean lacinia bibendum nulla sed consectetur. Etiam porta sem malesuada magna mollis euismod. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>\n" +
                "                <ul>\n" +
                "                    <li>Praesent commodo cursus magna, vel scelerisque nisl consectetur et.</li>\n" +
                "                    <li>Donec id elit non mi porta gravida at eget metus.</li>\n" +
                "                    <li>Nulla vitae elit libero, a pharetra augue.</li>\n" +
                "                </ul>\n" +
                "                <p>Donec ullamcorper nulla non metus auctor fringilla. Nulla vitae elit libero, a pharetra augue.</p>\n" +
                "                <ol>\n" +
                "                    <li>Vestibulum id ligula porta felis euismod semper.</li>\n" +
                "                    <li>Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</li>\n" +
                "                    <li>Maecenas sed diam eget risus varius blandit sit amet non magna.</li>\n" +
                "                </ol>\n" +
                "                <p>Cras mattis consectetur purus sit amet fermentum. Sed posuere consectetur est at lobortis.</p>";

        String newArticleContent2 = "<p>Cum sociis natoque penatibus et magnis <a href=\"#\">dis parturient montes</a>, nascetur ridiculus mus. Aenean eu leo quam. Pellentesque ornare sem lacinia quam venenatis vestibulum. Sed posuere consectetur est at lobortis. Cras mattis consectetur purus sit amet fermentum.</p>\n" +
                "                <blockquote>\n" +
                "                    <p>Curabitur blandit tempus porttitor. <strong>Nullam quis risus eget urna mollis</strong> ornare vel eu leo. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>\n" +
                "                </blockquote>\n" +
                "                <p>Etiam porta <em>sem malesuada magna</em> mollis euismod. Cras mattis consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla sed consectetur.</p>\n" +
                "                <p>Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor. Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Morbi leo risus, porta ac consectetur ac, vestibulum at eros.</p>";

        String newArticleContent3 = "<p>Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Aenean lacinia bibendum nulla sed consectetur. Etiam porta sem malesuada magna mollis euismod. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>\n" +
                "                <ul>\n" +
                "                    <li>Praesent commodo cursus magna, vel scelerisque nisl consectetur et.</li>\n" +
                "                    <li>Donec id elit non mi porta gravida at eget metus.</li>\n" +
                "                    <li>Nulla vitae elit libero, a pharetra augue.</li>\n" +
                "                </ul>\n" +
                "                <p>Etiam porta <em>sem malesuada magna</em> mollis euismod. Cras mattis consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla sed consectetur.</p>\n" +
                "                <p>Donec ullamcorper nulla non metus auctor fringilla. Nulla vitae elit libero, a pharetra augue.</p>";

        User user = userService.listUser().get(0);

        Article newArticle1 = new Article();
        // in HTML format
        newArticle1.setArticleContent(newArticleContent1);
        newArticle1.setArticleTitle("NEW ARTICLE 1");
        newArticle1.setArticleCreateTime(new Date());
        newArticle1.setArticleUserId(1001);
        newArticle1.setArticleStatus(1);
        newArticle1.setUser(user);

        Article newArticle2 = new Article();
        newArticle2.setArticleContent(newArticleContent2);
        newArticle2.setArticleTitle("NEW ARTICLE 2");
        newArticle2.setArticleCreateTime(new Date());;
        newArticle2.setArticleUserId(1002);
        newArticle2.setArticleStatus(1);
        newArticle2.setUser(user);

        Article newArticle3 = new Article();
        newArticle3.setArticleContent(newArticleContent3);
        newArticle3.setArticleTitle("NEW ARTICLE 3");
        newArticle3.setArticleCreateTime(new Date());;
        newArticle3.setArticleUserId(1003);
        newArticle3.setArticleStatus(1);
        newArticle3.setUser(user);

        articleService.insertArticle(newArticle1);
        articleService.insertArticle(newArticle2);
        articleService.insertArticle(newArticle3);
    }

    @RequestMapping(value = {"/", "/index"})
    public String index(Model model) {
        Article article = new Article();
        article.setArticleContent("Etiam porta sem malesuada magna mollis euismod. Cras mattis consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla sed consectetur.");
        article.setArticleTitle("FIRST ARTICLE");
        article.setArticleSummary("Etiam porta sem malesuada magna mollis euismod. Cras mattis consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla sed consectetur.");
        article.setArticleStatus(1);

        articleService.insertArticle(article);
        model.addAttribute("jumbotron", article);

        List<Article> featuredPosts = new ArrayList<Article>();

        Article article1 = new Article();
        article1.setArticleContent("Etiam porta sem malesuada magna mollis euismod. Cras mattis consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla sed consectetur.");
        article1.setArticleTitle("SECOND ARTICLE");
        article1.setArticleSummary("Etiam porta sem malesuada magna mollis euismod. Cras mattis consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla sed consectetur.");

        Article article2 = new Article();
        article2.setArticleContent("Etiam porta sem malesuada magna mollis euismod. Cras mattis consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla sed consectetur.");
        article2.setArticleTitle("THIRD ARTICLE");
        article2.setArticleSummary("Etiam porta sem malesuada magna mollis euismod. Cras mattis consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla sed consectetur.");

        featuredPosts.add(article1);
        featuredPosts.add(article2);

        model.addAttribute("featuredPosts", featuredPosts);

        List<Article> whatsNewToday = articleService.listRecentArticle(3);

        model.addAttribute("whatsNewToday", whatsNewToday);

        return "Home/index";
    }

    @GetMapping("/body")
    public String body(Model model) {
        return "Home/Public/body";
    }

}
