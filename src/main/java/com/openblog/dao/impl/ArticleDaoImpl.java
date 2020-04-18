package com.openblog.dao.impl;

import com.openblog.dao.ArticleDao;
import com.openblog.entity.Article;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ArticleDaoImpl implements ArticleDao {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public void insertArticle(Article article) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(article);
    }

    @Transactional
    public List<Article> listRecentArticle(Integer limit) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery(
                "FROM Article WHERE articleStatus=1 " +
                        "ORDER BY articleCreateTime DESC")
                .setMaxResults(limit);
        List<Article> results = query.list();
        return results;
    }

    @Transactional
    public Article getArticleByStatusAndId(Integer status, Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery(
                "FROM Article WHERE articleStatus=:status AND articleId=:id")
                .setInteger("status", status)
                .setInteger("id", id);
        Article result = (Article) query.uniqueResult();
        return result;
    }

    @Transactional
    public List<Article> listArticleByViewCount(Integer limit) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery(
                "FROM Article WHERE articleStatus=1 " +
                        "ORDER BY articleViewCount DESC, articleId DESC")
                .setMaxResults(limit);
        return query.list();
    }

    @Transactional
    public Integer countArticle(Integer status) {
        return null;
    }

    @Transactional
    public Integer countArticleComment() {
        return null;
    }

    @Transactional
    public Integer countArticleView() {
        return null;
    }

    @Transactional
    public Integer countArticleByCategoryId(Integer categoryId) {
        return null;
    }

    @Transactional
    public Integer countArticleByTagId(Integer tagId) {
        return null;
    }

    @Transactional
    public List<Article> listArticle() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery(
                "FROM Article WHERE articleStatus=1 " +
                        "ORDER BY articleStatus ASC, articleId DESC");
        return query.list();
    }

    @Transactional
    public void updateArticleDetail(Article article) {

    }

    @Transactional
    public void updateArticle(Article article) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(article);
    }

    @Transactional
    public void deleteArticleBatch(List<Integer> ids) {

    }

    @Transactional
    public void deleteArticle(Article article) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(article);
    }

    @Transactional
    public List<Article> pageArticle(Integer pageIndex, Integer pageSize, Map<String, Object> criteria) {
        Session currentSession = sessionFactory.getCurrentSession();
        String hql = "FROM Article WHERE articleStatus=1";
        if (criteria.containsKey("category")) {
            hql = hql + " AND articleCategory=" + criteria.get("categoryId");
        } else if (criteria.containsKey("tag")) {
            hql = hql + " AND articleTag=" + criteria.get("tagId");
        } else if (criteria.containsKey("user")) {
            hql = hql + " AND articleUserId=" + criteria.get("userId");
        }
        Query query = currentSession.createQuery(hql)
                .setFirstResult(pageIndex)
                .setMaxResults(pageSize);
        return query.list();
    }

    @Transactional
    public Article getAfterArticle(Integer id) {
        return null;
    }

    @Transactional
    public Article getPreArticle(Integer id) {
        return null;
    }

    @Transactional
    public List<Article> listRandomArticle(Integer limit) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery(
                "FROM Article WHERE articleStatus=1 ORDER BY RAND()")
                .setMaxResults(limit);
        return query.list();
    }

    @Transactional
    public List<Article> listArticleByCommentCount(Integer limit) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery(
                "FROM Article WHERE articleStatus=1 " +
                        "ORDER BY articleCommentCount DESC, articleId DESC")
                .setMaxResults(limit);
        return query.list();
    }

    @Transactional
    public void updateCommentCount(Integer articleId) {

    }

    @Transactional
    public Article getLastUpdateArticle() {
        return null;
    }

    @Transactional
    public List<Article> listArticleByCategoryId(Integer cateId, Integer limit) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery(
                "FROM Article WHERE articleCategoryId=:cateId and articleStatus=1 " +
                        "ORDER BY articleCreateTime DESC")
                .setInteger("cateId", cateId)
                .setMaxResults(limit);
        return query.list();
    }

    @Transactional
    public List<Article> listArticleByCategoryIds(List<Integer> cateIds, Integer limit) {
        return null;
    }

    @Transactional
    public List<Integer> listCategoryIdByArticleId(Integer articleId) {
        return null;
    }

    @Transactional
    public List<Article> listAllNotWithContent() {
        return null;
    }

    @Transactional
    public List<Article> listArticleByAuthor(String userId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery(
                "FROM Article WHERE userId=:userId and articleStatus=1 " +
                        "ORDER BY articleCreateTime DESC")
                .setString("userId", userId);
        return query.list();
    }
}
