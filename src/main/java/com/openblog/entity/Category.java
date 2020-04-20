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
@Table(name = "category")
public class Category {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer categoryId;

    @Column(name = "pid")
    private Integer categoryPid;

    @Column(name = "name")
    private String categoryName;

    @Column(name = "description")
    private String categoryDescription;

    @Column(name = "categoryOrder")
    private Integer categoryOrder;

    @Column(name = "icon")
    private String categoryIcon;

    @Column(name = "articles")
    @OneToMany(mappedBy = "articleCategory", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Article> articles = new ArrayList<Article>();

    /**
     * 文章数量(非数据库字段)
     */
    @Transient
    private Integer articleCount;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(Integer categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public static Category Default() {
        return new Category(100000, "Uncategorized");
    }

}
