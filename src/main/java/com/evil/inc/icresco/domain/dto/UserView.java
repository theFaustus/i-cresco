package com.evil.inc.icresco.domain.dto;

import com.evil.inc.icresco.web.hateoas.domain.CollectionRelation;
import com.evil.inc.icresco.web.hateoas.domain.ItemRelation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;
import java.util.Set;

import static com.evil.inc.icresco.web.hateoas.domain.CollectionRelation.*;
import static com.evil.inc.icresco.web.hateoas.domain.ItemRelation.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Relation(itemRelation = USER, collectionRelation = USERS)
public class UserView {
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String gender;
    private Set<String> authorities;
    private List<GrowthPlanView> growthPlans;

    private String accessToken;
}
