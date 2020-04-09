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
@Table(name = "tag")
public class Tag {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer tagId;

    @Column(name = "name")
    private String tagName;

    @Column(name = "description")
    private String tagDescription;

    /**
     * Number of article.
     */
    @Transient
    private Integer articleCount;

}
