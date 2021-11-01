package com.evil.inc.icresco.web.hateoas.assembler;

import com.evil.inc.icresco.domain.dto.ArticleRecordView;
import com.evil.inc.icresco.service.UserService;
import com.evil.inc.icresco.web.rest.ArticleRecordController;
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

import static com.evil.inc.icresco.web.hateoas.CollectionRelation.ARTICLE_RECORDS;
import static com.evil.inc.icresco.web.hateoas.ItemRelation.GROWTH_PLAN;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class ArticleRecordModelAssembler implements RepresentationModelAssembler<ArticleRecordView, EntityModel<ArticleRecordView>> {

    private final PagedResourcesAssembler<ArticleRecordView> pagedResourcesAssembler;
    private final UserService userService;

    @Override
    public EntityModel<ArticleRecordView> toModel(final ArticleRecordView articleRecord) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String userId = userService.findByUsername(authentication.getName()).getId();
        return EntityModel.of(articleRecord,
                              linkTo(methodOn(ArticleRecordController.class).get(articleRecord.getId())).withSelfRel(),
                              linkTo(methodOn(ArticleRecordController.class)
                                             .getAllByUserId(userId, Pageable.unpaged())).withRel(ARTICLE_RECORDS),
                              linkTo(methodOn(ArticleRecordController.class)
                                             .getAllByGrowthPlanId(articleRecord.getId(), Pageable.unpaged())).withRel(ARTICLE_RECORDS),
                              linkTo(methodOn(GrowthPlanController.class)
                                             .get(articleRecord.getGrowthPlanId())).withRel(GROWTH_PLAN));
    }

    public PagedModel<EntityModel<ArticleRecordView>> toPagedModel(Page<ArticleRecordView> articleRecords) {
        return pagedResourcesAssembler.toModel(articleRecords, this);
    }
}
