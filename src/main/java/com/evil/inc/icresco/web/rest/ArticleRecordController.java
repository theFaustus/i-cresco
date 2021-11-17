package com.evil.inc.icresco.web.rest;

import com.evil.inc.icresco.web.dto.ArticleRecordView;
import com.evil.inc.icresco.web.dto.CreateArticleRecordRequest;
import com.evil.inc.icresco.domain.entity.Authority;
import com.evil.inc.icresco.service.ArticleRecordService;
import com.evil.inc.icresco.web.hateoas.assembler.ArticleRecordModelAssembler;
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


@Tag(name = "ArticleRecord", description = "Endpoints for managing article records")
@RestController
@RequestMapping("/api/v1/users")
@RolesAllowed(Authority.Fields.POWER_USER)
@RequiredArgsConstructor
public class ArticleRecordController {
    private final ArticleRecordService articleRecordService;
    private final ArticleRecordModelAssembler articleRecordModelAssembler;

    @PostMapping("/growth-plans/{growthPlanId}/article-records")
    @RolesAllowed({Authority.Fields.SIMPLE_USER, Authority.Fields.POWER_USER})
    public ResponseEntity<EntityModel<ArticleRecordView>> create(@PathVariable("growthPlanId") String growthPlanId,
                                                              @RequestBody @Valid CreateArticleRecordRequest request) {
        final ArticleRecordView articleRecord = articleRecordService.createForGrowthPlan(request, growthPlanId);
        URI location = MvcUriComponentsBuilder.fromMethodCall(
                MvcUriComponentsBuilder.on(getClass()).get(articleRecord.getId())).build().toUri();
        return ResponseEntity.created(location).body(articleRecordModelAssembler.toModel(articleRecord));
    }

    @DeleteMapping("/growth-plans/article-records/{id}")
    @RolesAllowed({Authority.Fields.SIMPLE_USER, Authority.Fields.POWER_USER})
    public ResponseEntity<Void> delete(@PathVariable String id) {
        articleRecordService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/growth-plans/article-records/{id}")
    @RolesAllowed({Authority.Fields.SIMPLE_USER, Authority.Fields.POWER_USER})
    public ResponseEntity<EntityModel<ArticleRecordView>> get(@PathVariable String id) {
        return ResponseEntity.ok().body(articleRecordModelAssembler.toModel(articleRecordService.findById(id)));
    }

    @GetMapping("/growth-plans/article-records")
    @RolesAllowed({Authority.Fields.POWER_USER})
    public ResponseEntity<CollectionModel<EntityModel<ArticleRecordView>>> getAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok().body(articleRecordModelAssembler.toPagedModel(articleRecordService.findAll(pageable)));
    }

    @GetMapping("/{userId}/growth-plans/article-records")
    @RolesAllowed({Authority.Fields.POWER_USER})
    public ResponseEntity<CollectionModel<EntityModel<ArticleRecordView>>> getAllByUserId(@PathVariable("userId") String userId, @PageableDefault Pageable pageable) {
        return ResponseEntity.ok().body(articleRecordModelAssembler.toPagedModel(articleRecordService.findAllByUserId(userId, pageable)));
    }

    @GetMapping("/growth-plans/{growthPlanId}/article-records")
    @RolesAllowed({Authority.Fields.POWER_USER})
    public ResponseEntity<CollectionModel<EntityModel<ArticleRecordView>>> getAllByGrowthPlanId(@PathVariable("growthPlanId") String growthPlanId, @PageableDefault Pageable pageable) {
        return ResponseEntity.ok().body(articleRecordModelAssembler.toPagedModel(articleRecordService.findAllByGrowthPlanId(growthPlanId, pageable)));
    }
}
