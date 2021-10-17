package com.evil.inc.icresco.web.hateoas.assembler;

import com.evil.inc.icresco.domain.dto.UserView;
import com.evil.inc.icresco.domain.entity.User;
import com.evil.inc.icresco.web.rest.UsersController;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor
public class UserModelAssembler implements RepresentationModelAssembler<UserView, EntityModel<UserView>> {

    private final PagedResourcesAssembler<UserView> pagedResourcesAssembler;

    @Override
    public EntityModel<UserView> toModel(final UserView user) {
        return EntityModel.of(user,
                              linkTo(methodOn(UsersController.class).get(user.getId())).withSelfRel());
    }

    public PagedModel<EntityModel<UserView>> toPagedModel(Page<UserView> users) {
        return pagedResourcesAssembler.toModel(users, this);
    }
}
