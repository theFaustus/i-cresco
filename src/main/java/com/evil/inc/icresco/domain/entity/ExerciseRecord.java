package com.evil.inc.icresco.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.Duration;

@Entity
@Table(name = "exercise_records")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExerciseRecord extends AbstractEntity{

    @Column(name = "exercise_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ExerciseType exerciseType;

    @Column(name = "duration", nullable = false)
    private Duration duration;

    @Column(name = "calories", nullable = false)
    private Double caloriesBurnt;

    @ManyToOne(fetch = FetchType.LAZY)
    private GrowthPlan growthPlan;

    public ExerciseRecord(final ExerciseType exerciseType, final Duration duration, final Double caloriesBurnt) {
        this.exerciseType = exerciseType;
        this.duration = duration;
        this.caloriesBurnt = caloriesBurnt;
    }
}
