package com.evil.inc.icresco.domain.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@Data
@Builder
@Relation(itemRelation = "user", collectionRelation = "users")
public class UserDto {
    private String id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String gender;
    private List<GrowthPlanDto> growthPlans;
}
