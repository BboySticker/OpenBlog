package com.openblog.service.impl;

import com.openblog.dao.TagDao;
import com.openblog.entity.Tag;
import com.openblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;

    public Integer countTag() {
        return tagDao.countTag();
    }

    public List<Tag> listTag() {
        return tagDao.listTag();
    }

    public List<Tag> listTagWithCount() {
        return tagDao.listTagWithCount();
    }

    public Tag getTagById(Integer id) {
        return tagDao.getTagById(id);
    }

    public Tag insertTag(Tag tag) {
        return tagDao.insertTag(tag);
    }

    public void updateTag(Tag tag) {
        tagDao.updateTag(tag);
    }

    public void deleteTag(Integer id) {
        tagDao.deleteTag(id);
    }

    public Tag getTagByName(String name) {
        return tagDao.getTagByName(name);
    }

    public List<Tag> listTagByArticleId(Integer articleId) {
        return tagDao.listTagByArticleId(articleId);
    }
}
