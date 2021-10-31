package com.evil.inc.icresco.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "book_records")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookRecord extends AbstractEntity{

    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "author", nullable = false)
    private String author;
    @Column(name = "description", nullable = false, length = 3000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private GrowthPlan growthPlan;

    public BookRecord(final String title, final String author, final String description) {
        this.title = title;
        this.author = author;
        this.description = description;
    }
}
