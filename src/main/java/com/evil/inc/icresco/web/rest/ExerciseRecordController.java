package com.evil.inc.icresco.web.rest;


import com.evil.inc.icresco.domain.entity.Authority;
import com.evil.inc.icresco.service.ExerciseRecordService;
import com.evil.inc.icresco.service.dto.CreateExerciseRecordRequest;
import com.evil.inc.icresco.service.dto.ExerciseRecordView;
import com.evil.inc.icresco.web.hateoas.assembler.ExerciseRecordModelAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.net.URI;


@Tag(name = "ExerciseRecord", description = "Endpoints for managing exercise records")
@RestController
@RequestMapping("/api/v1/users")
@RolesAllowed(Authority.Fields.POWER_USER)
@RequiredArgsConstructor
public class ExerciseRecordController {
    private final ExerciseRecordService exerciseRecordService;
    private final ExerciseRecordModelAssembler exerciseRecordModelAssembler;

    @PostMapping("/growth-plans/{growthPlanId}/exercise-records")
    @RolesAllowed({Authority.Fields.SIMPLE_USER, Authority.Fields.POWER_USER})
    public ResponseEntity<EntityModel<ExerciseRecordView>> create(@PathVariable("growthPlanId") String growthPlanId,
                                                                  @RequestBody @Valid CreateExerciseRecordRequest request) {
        final ExerciseRecordView exerciseRecord = exerciseRecordService.createForGrowthPlan(request, growthPlanId);
        URI location = MvcUriComponentsBuilder.fromMethodCall(
                MvcUriComponentsBuilder.on(getClass()).get(exerciseRecord.getId())).build().toUri();
        return ResponseEntity.created(location).body(exerciseRecordModelAssembler.toModel(exerciseRecord));
    }

    @DeleteMapping("/growth-plans/exercise-records/{id}")
    @RolesAllowed({Authority.Fields.SIMPLE_USER, Authority.Fields.POWER_USER})
    public ResponseEntity<Void> delete(@PathVariable String id) {
        exerciseRecordService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/growth-plans/exercise-records/{id}")
    @RolesAllowed({Authority.Fields.SIMPLE_USER, Authority.Fields.POWER_USER})
    public ResponseEntity<EntityModel<ExerciseRecordView>> get(@PathVariable String id) {
        return ResponseEntity.ok().body(exerciseRecordModelAssembler.toModel(exerciseRecordService.findById(id)));
    }

    @GetMapping("/growth-plans/exercise-records")
    @RolesAllowed({Authority.Fields.POWER_USER})
    public ResponseEntity<CollectionModel<EntityModel<ExerciseRecordView>>> getAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok().body(exerciseRecordModelAssembler.toPagedModel(exerciseRecordService.findAll(pageable)));
    }

    @GetMapping("/{userId}/growth-plans/exercise-records")
    @RolesAllowed({Authority.Fields.POWER_USER})
    public ResponseEntity<CollectionModel<EntityModel<ExerciseRecordView>>> getAllByUserId(@PathVariable("userId") String userId, @PageableDefault Pageable pageable) {
        return ResponseEntity.ok().body(exerciseRecordModelAssembler.toPagedModel(exerciseRecordService.findAllByUserId(userId, pageable)));
    }

    @GetMapping("/growth-plans/{growthPlanId}/exercise-records")
    @RolesAllowed({Authority.Fields.POWER_USER})
    public ResponseEntity<CollectionModel<EntityModel<ExerciseRecordView>>> getAllByGrowthPlanId(@PathVariable("growthPlanId") String growthPlanId, @PageableDefault Pageable pageable) {
        return ResponseEntity.ok().body(exerciseRecordModelAssembler.toPagedModel(exerciseRecordService.findAllByGrowthPlanId(growthPlanId, pageable)));
    }
}
