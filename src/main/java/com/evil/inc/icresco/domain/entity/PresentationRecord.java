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
@Table(name = "presentation_records")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PresentationRecord extends AbstractEntity{

    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", nullable = false, length = 3000)
    private String description;
    @Column(name = "url", nullable = false)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    private GrowthPlan growthPlan;

}
