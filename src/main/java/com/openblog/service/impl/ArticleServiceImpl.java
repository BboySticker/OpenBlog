package com.openblog.service.impl;

import com.openblog.dao.ArticleDao;
import com.openblog.entity.Article;
import com.openblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleDao articleDao;

    public Integer countArticle(Integer status) {
        return articleDao.countArticle(status);
    }

    public Integer countArticleComment() {
        return articleDao.countArticleComment();
    }

    public Integer countArticleView() {
        return articleDao.countArticleView();
    }

    public Integer countArticleByCategoryId(Integer categoryId) {
        return articleDao.countArticleByCategoryId(categoryId);
    }

    public Integer countArticleByTagId(Integer tagId) {
        return articleDao.countArticleByTagId(tagId);
    }

    public List<Article> listArticle() {
        return articleDao.listArticle();
    }

    public List<Article> listRecentArticle(Integer limit) {
        return articleDao.listRecentArticle(limit);
    }

    public void updateArticleDetail(Article article) {
        articleDao.updateArticleDetail(article);
    }

    public void updateArticle(Article article) {
        articleDao.updateArticle(article);
    }

    public void deleteArticleBatch(List<Integer> ids) {
        articleDao.deleteArticleBatch(ids);
    }

    public void deleteArticle(Article article) {
        articleDao.deleteArticle(article);
    }

    public List<Article> pageArticle(Integer pageIndex, Integer pageSize, Map<String, Object> criteria) {
        return articleDao.pageArticle(pageIndex, pageSize, criteria);
    }

    public Article getArticleByStatusAndId(Integer status, Integer id) {
        return articleDao.getArticleByStatusAndId(status, id);
    }

    public List<Article> listArticleByViewCount(Integer limit) {
        return articleDao.listArticleByViewCount(limit);
    }

    public Article getAfterArticle(Integer id) {
        return articleDao.getAfterArticle(id);
    }

    public Article getPreArticle(Integer id) {
        return articleDao.getPreArticle(id);
    }

    public List<Article> listRandomArticle(Integer limit) {
        return articleDao.listRandomArticle(limit);
    }

    public List<Article> listArticleByCommentCount(Integer limit) {
        return articleDao.listArticleByCommentCount(limit);
    }

    public void insertArticle(Article article) {
        articleDao.insertArticle(article);
    }

    public void updateCommentCount(Integer articleId) {
        articleDao.updateCommentCount(articleId);
    }

    public Article getLastUpdateArticle() {
        return articleDao.getLastUpdateArticle();
    }

    public List<Article> listArticleByCategoryId(Integer cateId, Integer limit) {
        return articleDao.listArticleByCategoryId(cateId, limit);
    }

    public List<Article> listArticleByCategoryIds(List<Integer> cateIds, Integer limit) {
        return articleDao.listArticleByCategoryIds(cateIds, limit);
    }

    public List<Integer> listCategoryIdByArticleId(Integer articleId) {
        return articleDao.listCategoryIdByArticleId(articleId);
    }

    public List<Article> listAllNotWithContent() {
        return articleDao.listAllNotWithContent();
    }

    public List<Article> listArticleByAuthor(String userId) {
        return articleDao.listArticleByAuthor(userId);
    }
}
