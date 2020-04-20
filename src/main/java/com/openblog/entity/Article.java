package com.openblog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "article")
public class Article {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer articleId;

    @Column(name = "userId")
    @NotNull
    private String articleUserId;

    @Column(name = "title", nullable = false)
    @NotNull
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
     *  Article content in html format
     */
    @Column(name = "content", columnDefinition = "LONGTEXT")
    private String articleContent;

    /**
     *  Article content in markdown format
     */
    @Column(name = "contentInMd", columnDefinition = "LONGTEXT")
    private String articleContentInMd;

    /**
     *  Summary is a chunk of article content that in plain text
     */
    @Column(name = "summary", columnDefinition = "LONGTEXT")
    private String articleSummary;

    @ManyToOne
    private User user;

    @ManyToOne
    private Tag articleTag;

    @ManyToOne
    private Category articleCategory;

}
