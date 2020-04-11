package com.openblog.service.impl;

import com.openblog.dao.ArticleDao;
import com.openblog.entity.Article;
import com.openblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleDao articleDao;

    public Integer countArticle(Integer status) {
        return null;
    }

    public Integer countArticleComment() {
        return null;
    }

    public Integer countArticleView() {
        return null;
    }

    public Integer countArticleByCategoryId(Integer categoryId) {
        return null;
    }

    public Integer countArticleByTagId(Integer tagId) {
        return null;
    }

    public List<Article> listArticle() {
        return null;
    }

    public List<Article> listRecentArticle(Integer limit) {
        return articleDao.listRecentArticle(limit);
    }

    public void updateArticleDetail(Article article) {

    }

    public void updateArticle(Article article) {

    }

    public void deleteArticleBatch(List<Integer> ids) {

    }

    public void deleteArticle(Integer id) {

    }

    public Article getArticleByStatusAndId(Integer status, Integer id) {
        return articleDao.getArticleByStatusAndId(status, id);
    }

    public List<Article> listArticleByViewCount(Integer limit) {
        return null;
    }

    public Article getAfterArticle(Integer id) {
        return null;
    }

    public Article getPreArticle(Integer id) {
        return null;
    }

    public List<Article> listRandomArticle(Integer limit) {
        return null;
    }

    public List<Article> listArticleByCommentCount(Integer limit) {
        return null;
    }

    public void insertArticle(Article article) {
        articleDao.insertArticle(article);
    }

    public void updateCommentCount(Integer articleId) {

    }

    public Article getLastUpdateArticle() {
        return null;
    }

    public List<Article> listArticleByCategoryId(Integer cateId, Integer limit) {
        return null;
    }

    public List<Article> listArticleByCategoryIds(List<Integer> cateIds, Integer limit) {
        return null;
    }

    public List<Integer> listCategoryIdByArticleId(Integer articleId) {
        return null;
    }

    public List<Article> listAllNotWithContent() {
        return null;
    }
}
