package com.openblog.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "page")
public class Page {

    @Id
    @Column(name = "id")
    private Integer pageId;

    @Column(name = "pageKey")
    private String pageKey;

    @Column(name = "title")
    private String pageTitle;

    @Column(name = "content")
    private String pageContent;

    @Column(name = "createTime")
    private Date pageCreateTime;

    @Column(name = "updateTime")
    private Date pageUpdateTime;

    @Column(name = "viewCount")
    private Integer pageViewCount;

    @Column(name = "commentCount")
    private Integer pageCommentCount;

    @Column(name = "status")
    private Integer pageStatus;

}
