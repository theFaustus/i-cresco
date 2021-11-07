package com.evil.inc.icresco.domain.mapper.impl;

import com.evil.inc.icresco.domain.dto.ExerciseRecordView;
import com.evil.inc.icresco.domain.entity.ExerciseRecord;
import com.evil.inc.icresco.domain.mapper.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;

@Service
class ExerciseRecordViewMapperImpl implements Mapper<ExerciseRecord, ExerciseRecordView> {

    @Override
    @Transactional
    public ExerciseRecordView map(final ExerciseRecord entity) {
        return ExerciseRecordView.builder()
                .id(entity.getId())
                .exerciseType(entity.getExerciseType())
                .durationInMinutes(entity.getDuration().toMinutes())
                .caloriesBurnt(entity.getCaloriesBurnt())
                .growthPlanId(entity.getGrowthPlan().getId())
                .created(entity.getCreatedDate())
                .build();
    }
}
