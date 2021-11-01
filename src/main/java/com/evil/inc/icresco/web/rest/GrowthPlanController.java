package com.evil.inc.icresco.web.rest;

import com.evil.inc.icresco.domain.dto.CreateGrowthPlanRequest;
import com.evil.inc.icresco.domain.dto.GrowthPlanView;
import com.evil.inc.icresco.domain.entity.Authority;
import com.evil.inc.icresco.service.GrowthPlanService;
import com.evil.inc.icresco.web.hateoas.assembler.GrowthPlanModelAssembler;
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

@Tag(name = "GrowthPlan", description = "GrowthPlan REST API")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class GrowthPlanController {
    private final GrowthPlanService growthPlanService;
    private final GrowthPlanModelAssembler growthPlanModelAssembler;

    @RolesAllowed({Authority.Fields.SIMPLE_USER, Authority.Fields.POWER_USER})
    @PostMapping("/{userId}/growth-plans")
    public ResponseEntity<EntityModel<GrowthPlanView>> create(@PathVariable String userId, @RequestBody @Valid CreateGrowthPlanRequest request) {
        final GrowthPlanView growthPlanView = growthPlanService.createFor(request, userId);
        URI location = MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(getClass())
                                                                      .get(growthPlanView.getId())).build().toUri();
        return ResponseEntity.created(location).body(growthPlanModelAssembler.toModel(growthPlanView));
    }

    @RolesAllowed({Authority.Fields.SIMPLE_USER, Authority.Fields.POWER_USER})
    @GetMapping("/{userId}/growth-plans")
    public ResponseEntity<CollectionModel<EntityModel<GrowthPlanView>>> getAllByUserId(@PathVariable String userId, @PageableDefault Pageable pageable) {
        return ResponseEntity.ok().body(growthPlanModelAssembler.toPagedModel(growthPlanService.findByUserId(userId, pageable)));
    }

    @RolesAllowed({Authority.Fields.SIMPLE_USER, Authority.Fields.POWER_USER})
    @DeleteMapping("/growth-plans/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        growthPlanService.delete(id);
        return ResponseEntity.ok().build();
    }

    @RolesAllowed({Authority.Fields.SIMPLE_USER, Authority.Fields.POWER_USER})
    @GetMapping("/growth-plans/{id}")
    public ResponseEntity<EntityModel<GrowthPlanView>> get(@PathVariable String id) {
        return ResponseEntity.ok().body(growthPlanModelAssembler.toModel(growthPlanService.findById(id)));
    }

    @RolesAllowed({Authority.Fields.POWER_USER})
    @GetMapping("/growth-plans")
    public ResponseEntity<CollectionModel<EntityModel<GrowthPlanView>>> getAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok().body(growthPlanModelAssembler.toPagedModel(growthPlanService.findAll(pageable)));
    }

}
