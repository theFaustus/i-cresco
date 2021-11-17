package com.evil.inc.icresco.web.hateoas.assembler;

import com.evil.inc.icresco.web.dto.ExerciseRecordView;
import com.evil.inc.icresco.service.UserService;
import com.evil.inc.icresco.web.rest.ExerciseRecordController;
import com.evil.inc.icresco.web.rest.GrowthPlanController;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static com.evil.inc.icresco.web.hateoas.CollectionRelation.EXERCISE_RECORDS;
import static com.evil.inc.icresco.web.hateoas.ItemRelation.GROWTH_PLAN;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class ExerciseRecordModelAssembler implements RepresentationModelAssembler<ExerciseRecordView, EntityModel<ExerciseRecordView>> {

    private final PagedResourcesAssembler<ExerciseRecordView> pagedResourcesAssembler;
    private final UserService userService;

    @Override
    public EntityModel<ExerciseRecordView> toModel(final ExerciseRecordView exerciseRecord) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String userId = userService.findByUsername(authentication.getName()).getId();
        return EntityModel.of(exerciseRecord,
                              linkTo(methodOn(ExerciseRecordController.class).get(exerciseRecord.getId())).withSelfRel(),
                              linkTo(methodOn(ExerciseRecordController.class)
                                             .getAllByUserId(userId, Pageable.unpaged())).withRel(EXERCISE_RECORDS),
                              linkTo(methodOn(ExerciseRecordController.class)
                                             .getAllByGrowthPlanId(exerciseRecord.getId(), Pageable.unpaged())).withRel(EXERCISE_RECORDS),
                              linkTo(methodOn(GrowthPlanController.class)
                                             .get(exerciseRecord.getGrowthPlanId())).withRel(GROWTH_PLAN));
    }

    public PagedModel<EntityModel<ExerciseRecordView>> toPagedModel(Page<ExerciseRecordView> ExerciseRecords) {
        return pagedResourcesAssembler.toModel(ExerciseRecords, this);
    }
}
