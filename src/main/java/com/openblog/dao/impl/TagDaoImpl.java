package com.openblog.dao.impl;

import com.openblog.dao.TagDao;
import com.openblog.entity.Tag;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class TagDaoImpl implements TagDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Integer countTag() {
        return null;
    }

    public List<Tag> listTag() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("FROM Tag");
        return query.list();
    }

    public List<Tag> listTagWithCount() {
        return null;
    }

    public Tag getTagById(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(Tag.class, id);
    }

    public Tag insertTag(Tag tag) {
        Session currentSession = sessionFactory.getCurrentSession();
        if (tag != null) {
            currentSession.saveOrUpdate(tag);
        }
        return tag;
    }

    public void updateTag(Tag tag) {
        Session currentSession = sessionFactory.getCurrentSession();
        if (tag != null) {
            currentSession.saveOrUpdate(tag);
        }
    }

    public void deleteTag(Integer id) {

    }

    public Tag getTagByName(String name) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery(
                "FROM Tag WHERE tagName=:name")
                .setString("name", name);
        return (Tag) query.uniqueResult();
    }

    public List<Tag> listTagByArticleId(Integer articleId) {
        return null;
    }
}
