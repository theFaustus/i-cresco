package com.evil.inc.icresco.web.rest;

import com.evil.inc.icresco.domain.dto.CreateBookRecordRequest;
import com.evil.inc.icresco.domain.dto.BookRecordView;
import com.evil.inc.icresco.domain.entity.Authority;
import com.evil.inc.icresco.service.BookRecordService;
import com.evil.inc.icresco.web.hateoas.assembler.BookRecordModelAssembler;
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


@Tag(name = "BookRecord", description = "BookRecord REST API")
@RestController
@RequestMapping("/api/v1/users")
@RolesAllowed(Authority.Fields.POWER_USER)
@RequiredArgsConstructor
public class BookRecordController {
    private final BookRecordService bookRecordService;
    private final BookRecordModelAssembler bookRecordModelAssembler;

    @PostMapping("/growth-plans/{growthPlanId}/book-records")
    @RolesAllowed({Authority.Fields.SIMPLE_USER, Authority.Fields.POWER_USER})
    public ResponseEntity<EntityModel<BookRecordView>> create(@PathVariable("growthPlanId") String growthPlanId,
                                                              @RequestBody @Valid CreateBookRecordRequest request) {
        final BookRecordView bookRecord = bookRecordService.createForGrowthPlan(request, growthPlanId);
        URI location = MvcUriComponentsBuilder.fromMethodCall(
                MvcUriComponentsBuilder.on(getClass()).get(bookRecord.getId())).build().toUri();
        return ResponseEntity.created(location).body(bookRecordModelAssembler.toModel(bookRecord));
    }

    @DeleteMapping("/growth-plans/book-records/{id}")
    @RolesAllowed({Authority.Fields.SIMPLE_USER, Authority.Fields.POWER_USER})
    public ResponseEntity<Void> delete(@PathVariable String id) {
        bookRecordService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/growth-plans/book-records/{id}")
    @RolesAllowed({Authority.Fields.SIMPLE_USER, Authority.Fields.POWER_USER})
    public ResponseEntity<EntityModel<BookRecordView>> get(@PathVariable String id) {
        return ResponseEntity.ok().body(bookRecordModelAssembler.toModel(bookRecordService.findById(id)));
    }

    @GetMapping("/growth-plans/book-records")
    @RolesAllowed({Authority.Fields.POWER_USER})
    public ResponseEntity<CollectionModel<EntityModel<BookRecordView>>> getAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok().body(bookRecordModelAssembler.toPagedModel(bookRecordService.findAll(pageable)));
    }

    @GetMapping("/{userId}/growth-plans/book-records")
    @RolesAllowed({Authority.Fields.POWER_USER})
    public ResponseEntity<CollectionModel<EntityModel<BookRecordView>>> getAllByUserId(@PathVariable("userId") String userId, @PageableDefault Pageable pageable) {
        return ResponseEntity.ok().body(bookRecordModelAssembler.toPagedModel(bookRecordService.findAllByUserId(userId, pageable)));
    }

    @GetMapping("/growth-plans/{growthPlanId}/book-records")
    @RolesAllowed({Authority.Fields.POWER_USER})
    public ResponseEntity<CollectionModel<EntityModel<BookRecordView>>> getAllByGrowthPlanId(@PathVariable("growthPlanId") String growthPlanId, @PageableDefault Pageable pageable) {
        return ResponseEntity.ok().body(bookRecordModelAssembler.toPagedModel(bookRecordService.findAllByGrowthPlanId(growthPlanId, pageable)));
    }
}
