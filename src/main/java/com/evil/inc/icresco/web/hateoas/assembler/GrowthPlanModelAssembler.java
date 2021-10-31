package com.evil.inc.icresco.web.hateoas.assembler;

import com.evil.inc.icresco.domain.dto.GrowthPlanView;
import com.evil.inc.icresco.service.UserService;
import com.evil.inc.icresco.web.hateoas.domain.CollectionRelation;
import com.evil.inc.icresco.web.hateoas.domain.ItemRelation;
import com.evil.inc.icresco.web.rest.BookRecordController;
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

import static com.evil.inc.icresco.web.hateoas.domain.CollectionRelation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class GrowthPlanModelAssembler implements RepresentationModelAssembler<GrowthPlanView, EntityModel<GrowthPlanView>> {

    private final PagedResourcesAssembler<GrowthPlanView> pagedResourcesAssembler;
    private final UserService userService;

    @Override
    public EntityModel<GrowthPlanView> toModel(final GrowthPlanView growthPlan) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String userId = userService.findByUsername(authentication.getName()).getId();
        return EntityModel.of(growthPlan,
                              linkTo(methodOn(GrowthPlanController.class).get(growthPlan.getId())).withSelfRel(),
                              linkTo(methodOn(GrowthPlanController.class)
                                             .getAllByUserId(userId, Pageable.unpaged())).withRel(GROWTH_PLANS),
                              linkTo(methodOn(BookRecordController.class)
                                             .getAllForUserId(userId, Pageable.unpaged())).withRel(
                                      CollectionRelation.BOOK_RECORDS));
    }

    public PagedModel<EntityModel<GrowthPlanView>> toPagedModel(Page<GrowthPlanView> growthPlans) {
        return pagedResourcesAssembler.toModel(growthPlans, this);
    }
}
