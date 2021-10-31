package com.evil.inc.icresco.domain.dto;

import com.evil.inc.icresco.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.server.core.Relation;

import static com.evil.inc.icresco.web.hateoas.domain.CollectionRelation.GROWTH_PLANS;
import static com.evil.inc.icresco.web.hateoas.domain.ItemRelation.GROWTH_PLAN;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Relation(itemRelation = GROWTH_PLAN, collectionRelation = GROWTH_PLANS)
public class GrowthPlanView {
    private String id;
    private String title;
    private String description;
    private String userId;
}
