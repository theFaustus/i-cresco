package com.evil.inc.icresco.web.dto;

import com.evil.inc.icresco.domain.entity.ExerciseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateExerciseRecordRequest {
    private ExerciseType exerciseType;
    private Long durationInMinutes;
    private Double caloriesBurnt;
}
