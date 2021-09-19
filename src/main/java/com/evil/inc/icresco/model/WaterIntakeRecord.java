package com.evil.inc.icresco.model;

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
@Table(name = "water_intake_records")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WaterIntakeRecord extends AbstractEntity{

    @Column(name = "number_of_glasses", nullable = false)
    private Double numberOfGlasses;

    @ManyToOne(fetch = FetchType.LAZY)
    private GrowthPlan growthPlan;

    public WaterIntakeRecord(final Double numberOfGlasses) {
        this.numberOfGlasses = numberOfGlasses;
    }
}
