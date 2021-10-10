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
@Table(name = "weight_records")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WeightRecord extends AbstractEntity{

    @Column(name = "value", nullable = false)
    private Double value;

    @ManyToOne(fetch = FetchType.LAZY)
    private GrowthPlan growthPlan;

    public WeightRecord(final Double value) {
        this.value = value;
    }
}
