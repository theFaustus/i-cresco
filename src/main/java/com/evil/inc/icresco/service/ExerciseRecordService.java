package com.evil.inc.icresco.service;

import com.evil.inc.icresco.domain.dto.ExerciseRecordView;
import com.evil.inc.icresco.domain.dto.CreateExerciseRecordRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExerciseRecordService {
    Page<ExerciseRecordView> findAll(Pageable pageable);

    Page<ExerciseRecordView> findAllByUserId(String userId, Pageable pageable);

    Page<ExerciseRecordView> findAllByGrowthPlanId(String growthPlanId, Pageable pageable);

    ExerciseRecordView findById(String id);

    ExerciseRecordView createForGrowthPlan(CreateExerciseRecordRequest createExerciseRecordRequest,
                                           String growthPlanId);

    void delete(String id);
}
