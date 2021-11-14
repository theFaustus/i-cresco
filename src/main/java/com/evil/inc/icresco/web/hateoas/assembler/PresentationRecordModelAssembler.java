package com.evil.inc.icresco.web.hateoas.assembler;

import com.evil.inc.icresco.domain.dto.PresentationRecordView;
import com.evil.inc.icresco.service.UserService;
import com.evil.inc.icresco.web.rest.GrowthPlanController;
import com.evil.inc.icresco.web.rest.PresentationRecordController;
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

import static com.evil.inc.icresco.web.hateoas.CollectionRelation.PRESENTATION_RECORDS;
import static com.evil.inc.icresco.web.hateoas.ItemRelation.GROWTH_PLAN;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class PresentationRecordModelAssembler implements RepresentationModelAssembler<PresentationRecordView, EntityModel<PresentationRecordView>> {

    private final PagedResourcesAssembler<PresentationRecordView> pagedResourcesAssembler;
    private final UserService userService;

    @Override
    public EntityModel<PresentationRecordView> toModel(final PresentationRecordView presentationRecord) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String userId = userService.findByUsername(authentication.getName()).getId();
        return EntityModel.of(presentationRecord,
                              linkTo(methodOn(PresentationRecordController.class).get(
                                      presentationRecord.getId())).withSelfRel(),
                              linkTo(methodOn(PresentationRecordController.class)
                                             .getAllByUserId(userId, Pageable.unpaged())).withRel(PRESENTATION_RECORDS),
                              linkTo(methodOn(PresentationRecordController.class)
                                             .getAllByGrowthPlanId(presentationRecord.getId(),
                                                                   Pageable.unpaged())).withRel(PRESENTATION_RECORDS),
                              linkTo(methodOn(GrowthPlanController.class)
                                             .get(presentationRecord.getGrowthPlanId())).withRel(GROWTH_PLAN));
    }

    public PagedModel<EntityModel<PresentationRecordView>> toPagedModel(
            Page<PresentationRecordView> presentationRecords) {
        return pagedResourcesAssembler.toModel(presentationRecords, this);
    }
}
