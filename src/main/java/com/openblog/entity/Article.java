package com.openblog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "article")
public class Article {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer articleId;

    @Column(name = "userId")
    private Integer articleUserId;

    @Column(name = "title")
    private String articleTitle;

    @Column(name = "viewCount")
    private Integer articleViewCount;

    @Column(name = "commentCount")
    private Integer articleCommentCount;

    @Column(name = "likeCount")
    private Integer articleLikeCount;

    @Column(name = "createTime")
    private Date articleCreateTime;

    @Column(name = "updateTime")
    private Date articleUpdateTime;

    @Column(name = "isComment")
    private Integer articleIsComment;

    @Column(name = "status")
    private Integer articleStatus;

    @Column(name = "articleOrder")
    private Integer articleOrder;

    /**
     *  In markdown format
     */
    @Column(name = "content", columnDefinition = "TEXT")
    private String articleContent;

    @Column(name = "summary", columnDefinition = "TEXT")
    private String articleSummary;

    @OneToOne
    @JoinColumn(name = "user")
    private User user;

    @Column(name = "tags")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Tag> tagList = new ArrayList<Tag>();

    @Column(name = "categories")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Category> categoryList = new ArrayList<Category>();

}
