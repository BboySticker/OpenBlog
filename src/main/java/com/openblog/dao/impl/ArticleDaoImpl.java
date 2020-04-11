package com.openblog.dao.impl;

import com.openblog.dao.ArticleDao;
import com.openblog.entity.Article;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        String hqlQuery = "FROM Article WHERE articleStatus=1 ORDER BY articleCreateTime DESC";
        Query query = currentSession.createQuery(hqlQuery);
        List<Article> results = query.list();
        return results.subList(0, limit);
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

}
