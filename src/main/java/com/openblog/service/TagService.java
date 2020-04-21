package com.openblog.service;

import com.openblog.entity.Tag;

import java.util.List;

public interface TagService {

    Integer countTag() ;

    List<Tag> listTag() ;

    List<Tag> listTagWithCount() ;

    Tag getTagById(Integer id) ;

    Tag insertTag(Tag tag) ;

    void updateTag(Tag tag) ;

    void deleteTag(Integer id) ;

    Tag getTagByName(String name) ;

    List<Tag> listTagByArticleId(Integer articleId);

}
