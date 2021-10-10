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
import java.time.Duration;

@Entity
@Table(name = "sleep_records")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SleepRecord extends AbstractEntity{

    @Column(name = "amount_of_sleep", nullable = false)
    private Duration amountOfSleep;

    @ManyToOne(fetch = FetchType.LAZY)
    private GrowthPlan growthPlan;

    public SleepRecord(final Duration amountOfSleep) {
        this.amountOfSleep = amountOfSleep;
    }
}
