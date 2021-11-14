package com.evil.inc.icresco.web.rest;

import com.evil.inc.icresco.domain.dto.PresentationRecordView;
import com.evil.inc.icresco.domain.dto.CreatePresentationRecordRequest;
import com.evil.inc.icresco.domain.entity.Authority;
import com.evil.inc.icresco.service.PresentationRecordService;
import com.evil.inc.icresco.web.hateoas.assembler.PresentationRecordModelAssembler;
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


@Tag(name = "PresentationRecord", description = "Endpoints for managing presentation records")
@RestController
@RequestMapping("/api/v1/users")
@RolesAllowed(Authority.Fields.POWER_USER)
@RequiredArgsConstructor
public class PresentationRecordController {
    private final PresentationRecordService presentationRecordService;
    private final PresentationRecordModelAssembler presentationRecordModelAssembler;

    @PostMapping("/growth-plans/{growthPlanId}/presentation-records")
    @RolesAllowed({Authority.Fields.SIMPLE_USER, Authority.Fields.POWER_USER})
    public ResponseEntity<EntityModel<PresentationRecordView>> create(@PathVariable("growthPlanId") String growthPlanId,
                                                              @RequestBody @Valid CreatePresentationRecordRequest request) {
        final PresentationRecordView presentationRecord = presentationRecordService.createForGrowthPlan(request, growthPlanId);
        URI location = MvcUriComponentsBuilder.fromMethodCall(
                MvcUriComponentsBuilder.on(getClass()).get(presentationRecord.getId())).build().toUri();
        return ResponseEntity.created(location).body(presentationRecordModelAssembler.toModel(presentationRecord));
    }

    @DeleteMapping("/growth-plans/presentation-records/{id}")
    @RolesAllowed({Authority.Fields.SIMPLE_USER, Authority.Fields.POWER_USER})
    public ResponseEntity<Void> delete(@PathVariable String id) {
        presentationRecordService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/growth-plans/presentation-records/{id}")
    @RolesAllowed({Authority.Fields.SIMPLE_USER, Authority.Fields.POWER_USER})
    public ResponseEntity<EntityModel<PresentationRecordView>> get(@PathVariable String id) {
        return ResponseEntity.ok().body(presentationRecordModelAssembler.toModel(presentationRecordService.findById(id)));
    }

    @GetMapping("/growth-plans/presentation-records")
    @RolesAllowed({Authority.Fields.POWER_USER})
    public ResponseEntity<CollectionModel<EntityModel<PresentationRecordView>>> getAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok().body(presentationRecordModelAssembler.toPagedModel(presentationRecordService.findAll(pageable)));
    }

    @GetMapping("/{userId}/growth-plans/presentation-records")
    @RolesAllowed({Authority.Fields.POWER_USER})
    public ResponseEntity<CollectionModel<EntityModel<PresentationRecordView>>> getAllByUserId(@PathVariable("userId") String userId, @PageableDefault Pageable pageable) {
        return ResponseEntity.ok().body(presentationRecordModelAssembler.toPagedModel(presentationRecordService.findAllByUserId(userId, pageable)));
    }

    @GetMapping("/growth-plans/{growthPlanId}/presentation-records")
    @RolesAllowed({Authority.Fields.POWER_USER})
    public ResponseEntity<CollectionModel<EntityModel<PresentationRecordView>>> getAllByGrowthPlanId(@PathVariable("growthPlanId") String growthPlanId, @PageableDefault Pageable pageable) {
        return ResponseEntity.ok().body(presentationRecordModelAssembler.toPagedModel(presentationRecordService.findAllByGrowthPlanId(growthPlanId, pageable)));
    }
}
