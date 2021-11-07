package com.evil.inc.icresco.web.hateoas.assembler;

import com.evil.inc.icresco.domain.dto.UserView;
import com.evil.inc.icresco.web.rest.ArticleRecordController;
import com.evil.inc.icresco.web.rest.BookRecordController;
import com.evil.inc.icresco.web.rest.ExerciseRecordController;
import com.evil.inc.icresco.web.rest.GrowthPlanController;
import com.evil.inc.icresco.web.rest.UsersController;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Service;

import static com.evil.inc.icresco.web.hateoas.CollectionRelation.ARTICLE_RECORDS;
import static com.evil.inc.icresco.web.hateoas.CollectionRelation.BOOK_RECORDS;
import static com.evil.inc.icresco.web.hateoas.CollectionRelation.EXERCISE_RECORDS;
import static com.evil.inc.icresco.web.hateoas.CollectionRelation.GROWTH_PLANS;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class UserModelAssembler implements RepresentationModelAssembler<UserView, EntityModel<UserView>> {

    private final PagedResourcesAssembler<UserView> pagedResourcesAssembler;

    @Override
    public EntityModel<UserView> toModel(final UserView user) {
        return EntityModel.of(user,
                              linkTo(methodOn(UsersController.class).get(user.getId())).withSelfRel(),
                              linkTo(methodOn(GrowthPlanController.class)
                                             .getAllByUserId(user.getId(), Pageable.unpaged())).withRel(GROWTH_PLANS),
                              linkTo(methodOn(BookRecordController.class)
                                             .getAllByUserId(user.getId(), Pageable.unpaged())).withRel(BOOK_RECORDS),
                              linkTo(methodOn(ArticleRecordController.class)
                                             .getAllByUserId(user.getId(), Pageable.unpaged())).withRel(ARTICLE_RECORDS),
                              linkTo(methodOn(ExerciseRecordController.class)
                                             .getAllByUserId(user.getId(), Pageable.unpaged())).withRel(EXERCISE_RECORDS));
    }

    public PagedModel<EntityModel<UserView>> toPagedModel(Page<UserView> users) {
        return pagedResourcesAssembler.toModel(users, this);
    }
}
