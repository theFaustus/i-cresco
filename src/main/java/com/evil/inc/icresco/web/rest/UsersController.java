package com.evil.inc.icresco.web.rest;

import com.evil.inc.icresco.domain.dto.CreateUserRequest;
import com.evil.inc.icresco.domain.dto.UserView;
import com.evil.inc.icresco.domain.entity.Authority;
import com.evil.inc.icresco.service.UserService;
import com.evil.inc.icresco.web.hateoas.assembler.UserModelAssembler;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

@Tag(name = "User", description = "Endpoints for managing users records")
@RestController
@RequestMapping("/api/v1/users")
@RolesAllowed(Authority.Fields.POWER_USER)
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;
    private final UserModelAssembler userModelAssembler;

    @Operation(
            tags = {"CREATE", "User"},
            summary = "Creates a user",
            description = "Creates a user by the provided body",
            security = @SecurityRequirement(name = "Role", scopes = {"POWER_USER"}))
    @PostMapping
    public ResponseEntity<EntityModel<UserView>> create(@RequestBody @Valid CreateUserRequest request) {
        final UserView userView = userService.create(request);
        URI location = MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(getClass())
                                                                      .get(userView.getId())).build().toUri();
        return ResponseEntity.created(location).body(userModelAssembler.toModel(userView));
    }

    @Operation(
            tags = {"DELETE", "User"},
            summary = "Deletes a user",
            description = "Deletes a user by its id",
            security = @SecurityRequirement(name = "Role", scopes = {"POWER_USER"}))
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable @Parameter(description = "The id of the user") String id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Operation(
            tags = {"READ", "User"},
            summary = "Gets a user",
            description = "Gets a user by its id",
            security = @SecurityRequirement(name = "Role", scopes = {"POWER_USER"}))
    @GetMapping("{id}")
    public ResponseEntity<EntityModel<UserView>> get(@PathVariable @Parameter(description = "The id of the user") String id) {
        return ResponseEntity.ok().body(userModelAssembler.toModel(userService.findById(id)));
    }

    @Operation(
            tags = {"READ", "User"},
            summary = "Gets all users",
            description = "Gets all users",
            security = @SecurityRequirement(name = "Role", scopes = {"POWER_USER"}))
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<UserView>>> getAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok().body(userModelAssembler.toPagedModel(userService.findAll(pageable)));
    }
}
