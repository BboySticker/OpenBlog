package com.openblog.dao;

import com.openblog.entity.Article;

import java.util.List;

public interface ArticleDao {

    void insertArticle(Article article);

    List<Article> listRecentArticle(Integer limit);

    Article getArticleByStatusAndId(Integer status, Integer id);

}
