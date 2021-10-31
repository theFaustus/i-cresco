package com.evil.inc.icresco.web.rest;

import com.evil.inc.icresco.domain.dto.CreateUserRequest;
import com.evil.inc.icresco.domain.dto.UserView;
import com.evil.inc.icresco.domain.entity.Authority;
import com.evil.inc.icresco.service.UserService;
import com.evil.inc.icresco.web.hateoas.assembler.UserModelAssembler;
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

@Tag(name = "User", description = "Users REST API")
@RestController
@RequestMapping("/api/v1/users")
@RolesAllowed(Authority.Fields.POWER_USER)
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;
    private final UserModelAssembler userModelAssembler;

    @PostMapping
    public ResponseEntity<EntityModel<UserView>> create(@RequestBody @Valid CreateUserRequest request) {
        final UserView userView = userService.create(request);
        URI location = MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(getClass())
                                                                      .get(userView.getId())).build().toUri();
        return ResponseEntity.created(location).body(userModelAssembler.toModel(userView));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<EntityModel<UserView>> get(@PathVariable String id) {
        return ResponseEntity.ok().body(userModelAssembler.toModel(userService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<UserView>>> getAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok().body(userModelAssembler.toCollectionModel(userService.findAll(pageable)));
    }
}
