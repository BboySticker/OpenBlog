package com.openblog.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer tagId;

    @Column(name = "name")
    private String tagName;

    @Column(name = "description")
    private String tagDescription;

    @Column(name = "articles")
    @OneToMany(mappedBy = "articleTag", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Article> articles = new ArrayList<Article>();

    /**
     * Number of article.
     */
    @Transient
    private Integer articleCount;

}
