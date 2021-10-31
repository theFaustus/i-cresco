package com.evil.inc.icresco.web.hateoas.assembler;

import com.evil.inc.icresco.domain.dto.BookRecordView;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class BookRecordModelAssembler implements RepresentationModelAssembler<BookRecordView, EntityModel<BookRecordView>> {

    private final PagedResourcesAssembler<BookRecordView> pagedResourcesAssembler;
    private final UserService userService;

    @Override
    public EntityModel<BookRecordView> toModel(final BookRecordView bookRecord) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String userId = userService.findByUsername(authentication.getName()).getId();
        return EntityModel.of(bookRecord,
                              linkTo(methodOn(BookRecordController.class).get(bookRecord.getId())).withSelfRel(),
                              linkTo(methodOn(BookRecordController.class)
                                             .getAllForUserId(userId, Pageable.unpaged())).withRel(
                                      CollectionRelation.BOOK_RECORDS),
                              linkTo(methodOn(BookRecordController.class)
                                             .getAllForGrowthPlanId(bookRecord.getId(), Pageable.unpaged())).withRel(
                                      CollectionRelation.BOOK_RECORDS),
                              linkTo(methodOn(GrowthPlanController.class)
                                             .get(bookRecord.getGrowthPlanId())).withRel(ItemRelation.GROWTH_PLAN));
    }

    public PagedModel<EntityModel<BookRecordView>> toPagedModel(Page<BookRecordView> bookRecords) {
        return pagedResourcesAssembler.toModel(bookRecords, this);
    }
}
