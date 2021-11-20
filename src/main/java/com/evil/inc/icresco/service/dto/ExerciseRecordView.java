package com.evil.inc.icresco.service.dto;

import com.evil.inc.icresco.domain.entity.ExerciseType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDateTime;

import static com.evil.inc.icresco.web.hateoas.CollectionRelation.EXERCISE_RECORDS;
import static com.evil.inc.icresco.web.hateoas.ItemRelation.EXERCISE_RECORD;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Relation(itemRelation = EXERCISE_RECORD, collectionRelation = EXERCISE_RECORDS)
public class ExerciseRecordView {
    private String id;
    private ExerciseType exerciseType;
    private Long durationInMinutes;
    private Double caloriesBurnt;
    private String growthPlanId;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime created;
}
