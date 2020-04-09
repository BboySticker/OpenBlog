package com.openblog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {

    @Id
    @Column(name = "id")
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
