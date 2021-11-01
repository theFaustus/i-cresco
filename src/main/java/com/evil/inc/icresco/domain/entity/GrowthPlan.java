package com.evil.inc.icresco.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "growth_plans")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GrowthPlan extends AbstractEntity{

    @Column(name = "title", nullable = false, unique = true)
    private String title;
    @Column(name = "description", nullable = false, length = 3000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "growthPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WeightRecord> weightRecords = new ArrayList<>();

    @OneToMany(mappedBy = "growthPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WaterIntakeRecord> waterIntakeRecords = new ArrayList<>();

    @OneToMany(mappedBy = "growthPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SleepRecord> sleepRecords = new ArrayList<>();

    @OneToMany(mappedBy = "growthPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExerciseRecord> exerciseRecords = new ArrayList<>();

    @OneToMany(mappedBy = "growthPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookRecord> bookRecords = new ArrayList<>();

    @OneToMany(mappedBy = "growthPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseRecord> courseRecords = new ArrayList<>();

    @OneToMany(mappedBy = "growthPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticleRecord> articleRecords = new ArrayList<>();

    @OneToMany(mappedBy = "growthPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PresentationRecord> presentationRecords = new ArrayList<>();

    public GrowthPlan(final String title, final String description) {
        this.title = title;
        this.description = description;
    }

    public void addWeightRecord(WeightRecord weightRecord) {
        weightRecords.add(weightRecord);
        weightRecord.setGrowthPlan(this);
    }

    public void removeWeightRecord(WeightRecord weightRecord) {
        weightRecords.remove(weightRecord);
        weightRecord.setGrowthPlan(null);
    }

    public void addWaterIntakeRecord(WaterIntakeRecord waterIntakeRecord) {
        waterIntakeRecords.add(waterIntakeRecord);
        waterIntakeRecord.setGrowthPlan(this);
    }

    public void removeWaterIntakeRecord(WaterIntakeRecord waterIntakeRecord) {
        waterIntakeRecords.remove(waterIntakeRecord);
        waterIntakeRecord.setGrowthPlan(null);
    }

    public void addSleepRecord(SleepRecord sleepRecord) {
        sleepRecords.add(sleepRecord);
        sleepRecord.setGrowthPlan(this);
    }

    public void removeSleepRecord(SleepRecord sleepRecord) {
        sleepRecords.remove(sleepRecord);
        sleepRecord.setGrowthPlan(null);
    }

    public void addExerciseRecord(ExerciseRecord exerciseRecord) {
        exerciseRecords.add(exerciseRecord);
        exerciseRecord.setGrowthPlan(this);
    }

    public void removeExerciseRecord(ExerciseRecord exerciseRecord) {
        exerciseRecords.remove(exerciseRecord);
        exerciseRecord.setGrowthPlan(null);
    }

    public void addBookRecord(BookRecord bookRecord) {
        bookRecords.add(bookRecord);
        bookRecord.setGrowthPlan(this);
    }

    public void removeBookRecord(BookRecord bookRecord) {
        bookRecords.remove(bookRecord);
        bookRecord.setGrowthPlan(null);
    }

    public void addCourseRecord(CourseRecord courseRecord) {
        courseRecords.add(courseRecord);
        courseRecord.setGrowthPlan(this);
    }

    public void removeCourseRecord(CourseRecord courseRecord) {
        courseRecords.remove(courseRecord);
        courseRecord.setGrowthPlan(null);
    }

    public void addArticleRecord(ArticleRecord articleRecord) {
        articleRecords.add(articleRecord);
        articleRecord.setGrowthPlan(this);
    }

    public void removeArticleRecord(ArticleRecord articleRecord) {
        articleRecords.remove(articleRecord);
        articleRecord.setGrowthPlan(null);
    }

    public void addPresentationRecord(PresentationRecord presentationRecord) {
        presentationRecords.add(presentationRecord);
        presentationRecord.setGrowthPlan(this);
    }

    public void removePresentationRecord(PresentationRecord presentationRecord) {
        presentationRecords.remove(presentationRecord);
        presentationRecord.setGrowthPlan(null);
    }
}
