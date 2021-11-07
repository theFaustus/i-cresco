package com.evil.inc.icresco.domain.dto;

import com.evil.inc.icresco.domain.entity.ExerciseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateExerciseRecordRequest {
    private ExerciseType exerciseType;
    private Long durationInMinutes;
    private Double caloriesBurnt;
}
