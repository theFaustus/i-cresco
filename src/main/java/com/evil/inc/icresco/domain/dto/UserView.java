package com.evil.inc.icresco.domain.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;
import java.util.Set;

@Data
@Builder
@Relation(itemRelation = "user", collectionRelation = "users")
public class UserView {
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String gender;
    private Set<String> authorities;
    private List<GrowthPlanView> growthPlans;
}
